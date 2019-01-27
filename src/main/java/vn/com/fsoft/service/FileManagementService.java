package vn.com.fsoft.service;

import java.util.List;

import vn.com.fsoft.model.FileConverted;

public interface FileManagementService {
    List<FileConverted> getFileConverted(String type);
    void deleteFile(String filePath);
}
