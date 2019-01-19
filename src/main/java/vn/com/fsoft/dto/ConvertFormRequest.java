package vn.com.fsoft.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ConvertFormRequest {
    private MultipartFile file;
    private Integer convertType;
}
