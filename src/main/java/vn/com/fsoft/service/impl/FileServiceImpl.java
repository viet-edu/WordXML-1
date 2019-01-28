package vn.com.fsoft.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import vn.com.fsoft.model.FileConverted;
import vn.com.fsoft.repository.FileRepository;
import vn.com.fsoft.service.FileService;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Value("${storage.uploadPath}")
    private String uploadPath;

    @Override
    public List<FileConverted> getAllFile() {
        return fileRepository.findAll();
    }

    @Override
    public FileConverted createFile(FileConverted file) {
        List<String> fileIdList = fileRepository.findFileIdByType(file.getType());

        Integer max;
        if (fileIdList == null || fileIdList.isEmpty()) {
            max = 0;
        } else {
            max =   fileIdList.stream()
                    .map(id -> id.replaceAll("([a-zA-Z]+)(0*)", ""))
                    .mapToInt(Integer::parseInt)
                    .max()
                    .orElse(0);
        }
        file.setFileId(file.getType() + (max + 1));
        file.setFileDate(new Date());
        return fileRepository.save(file);
    }

    @Override
    public List<FileConverted> fileByType(String type) {
        return fileRepository.findByType(type);
    }

    @Override
    public void deleteFileById(String fileId) throws Exception {
        FileConverted fileConverted = fileRepository.findOne(fileId);
        if (fileConverted == null) {
            throw new Exception("File not found with id: " + fileId);
        }

        String tomcatBase = System.getProperty("catalina.base");
        String webApp = org.springframework.util.StringUtils.cleanPath(tomcatBase + uploadPath + fileConverted.getFilePath());
        File file = new File(webApp);

        if (file.exists()) {
            file.delete();
            fileRepository.delete(fileId);
        } else {
            throw new Exception("File not found with path: " + fileConverted.getFilePath());
        }
    }

    @Override
    public HttpEntity<byte[]> donwloadFileById(String fileId) throws Exception {
        FileConverted fileConverted = fileRepository.findOne(fileId);
        if (fileConverted == null) {
            throw new Exception("File not found with id: " + fileId);
        }

        String tomcatBase = System.getProperty("catalina.base");
        String webApp = org.springframework.util.StringUtils.cleanPath(tomcatBase + uploadPath + fileConverted.getFilePath());
        File file = new File(webApp);

        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.APPLICATION_);
        header.set(HttpHeaders.CONTENT_DISPOSITION,
                       "attachment; filename=" + fileConverted.getFileName());
        header.setContentLength(file.length());

        return null;
    }

    @Override
    public FileConverted findFileById(String fileId) {
        return fileRepository.findOne(fileId);
    }

    @Override
    public FileConverted saveFile(FileConverted file) {
        return fileRepository.save(file);
    }
}
