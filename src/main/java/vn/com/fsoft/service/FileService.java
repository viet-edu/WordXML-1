package vn.com.fsoft.service;

import java.util.List;

import org.springframework.http.HttpEntity;

import vn.com.fsoft.model.FileConverted;

public interface FileService {

    List<FileConverted> getAllFile();

    List<FileConverted> fileByType(String type);

    FileConverted createFile(FileConverted file);

    FileConverted saveFile(FileConverted file);

    void deleteFileById(String fileId) throws Exception;

    HttpEntity<byte[]> donwloadFileById(String fileId) throws Exception;

    FileConverted findFileById(String fileId);

    void createTags(FileConverted file, String tags);

    List<FileConverted> getFileByTag(String tag);
}
