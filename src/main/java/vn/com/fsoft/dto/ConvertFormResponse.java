package vn.com.fsoft.dto;

import java.util.List;
import lombok.Data;

@Data
public class ConvertFormResponse {
    private String fileId;
    private String type;
    private String message;
    private String fileName;
    private String filePath;
    private List<String> errorList;
}
