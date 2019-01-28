package vn.com.fsoft.dto;

import lombok.Data;

@Data
public class ConvertFormResponse {
    private String fileId;
    private String type;
    private String message;
    private String fileName;
    private String filePath;
}
