package vn.com.fsoft.controller.admin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.com.fsoft.service.FileManagementService;

@Controller
@RequestMapping("admin/QuanLyFile")
public class FileManagementController {

    @Autowired
    private FileManagementService fileManagementService;

    @GetMapping("Converted/{type}")
    public ModelAndView showWordConvertedPage(Model model, @PathVariable("type") String type) {
        model.addAttribute("title", "File Converted");
        model.addAttribute("fileConvertedList", fileManagementService.getFileConverted(type));
        return new ModelAndView("admin/file-converted");
    }

    @GetMapping("delete")
    public String deleteFile(@RequestParam("path") String filePath) {
        fileManagementService.deleteFile(filePath);
        if (StringUtils.contains(filePath, "xml")) {
            return "redirect:/admin/QuanLyFile/Converted/xml";
        } else {
            return "redirect:/admin/QuanLyFile/Converted/word";
        }
    }
}
