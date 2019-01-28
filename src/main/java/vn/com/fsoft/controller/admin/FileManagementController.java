package vn.com.fsoft.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.com.fsoft.model.FileConverted;
import vn.com.fsoft.service.FileManagementService;
import vn.com.fsoft.service.FileService;

@Controller
@RequestMapping("admin/QuanLyFile")
public class FileManagementController {

    @Autowired
    private FileManagementService fileManagementService;

    @Autowired
    private FileService fileService;

    @Value("${storage.uploadPath}")
    private String uploadPath;

    @GetMapping("Converted/{type}")
    public ModelAndView showWordConvertedPage(Model model, @PathVariable("type") String type) {
        model.addAttribute("title", "File Converted");
        model.addAttribute("fileConvertedList", fileService.fileByType(type));
        return new ModelAndView("admin/file-converted");
    }

    @GetMapping("delete/{fileId}")
    public String deleteFile(
            @PathVariable("fileId") String fileId,
            @RequestParam("type") String type,
            RedirectAttributes redirectAttributes) {
        try {
            fileService.deleteFileById(fileId);
            redirectAttributes.addFlashAttribute("success", "Delete file "+fileId+" success!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/admin/QuanLyFile/Converted/"+type;
    }

    @GetMapping("download/{fileId}")
    public void downloadFile(
            @PathVariable("fileId") String fileId,
            HttpServletResponse response) {
        try {
            FileConverted fileConverted = fileService.findFileById(fileId);
            if (fileConverted != null) {
                fileConverted.setStatus("đã download");
                fileService.saveFile(fileConverted);
                String tomcatBase = System.getProperty("catalina.base");
                String webApp = org.springframework.util.StringUtils.cleanPath(tomcatBase + uploadPath + fileConverted.getFilePath());
                File fileToDownload = new File(webApp);
                InputStream inputStream = new FileInputStream(fileToDownload);
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment; filename="+fileConverted.getFileName());
                IOUtils.copy(inputStream, response.getOutputStream());
                response.flushBuffer();
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
