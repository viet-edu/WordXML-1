package vn.com.fsoft.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity(name = "Files")
@Table(name = "Files")
public class FileConverted {

    @Id
    @Column(name = "File_Id", length = 10)
    private String fileId;

    @Column(name = "File_Name", length = 255)
    private String fileName;

    @Column(name = "FPath", length = 255)
    private String filePath;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Temporal(TemporalType.DATE)
    @Column(name = "File_Date", length = 255)
    private Date fileDate;

    @Column(name = "Type", length = 255)
    private String type;

    @Column(name = "Status", length = 255)
    private String status;
}
