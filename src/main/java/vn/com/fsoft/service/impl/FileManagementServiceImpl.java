package vn.com.fsoft.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import vn.com.fsoft.model.FileConverted;
import vn.com.fsoft.service.FileManagementService;

@Service
public class FileManagementServiceImpl implements FileManagementService {

    @Value("${storage.uploadPath}")
    private String uploadPath;

    @Override
    public List<FileConverted> getFileConverted(String type) {
        List<FileConverted> result = new ArrayList<>();
        FileConverted fileConverted;
        File folder = new File(uploadPath + "\\" + type);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                fileConverted = new FileConverted();
                fileConverted.setFileName(listOfFiles[i].getName());
                fileConverted.setFilePath("/"+type+"/"+listOfFiles[i].getName());
                result.add(fileConverted);
            }
        }

        return result;
    }
}
