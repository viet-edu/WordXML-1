package vn.com.fsoft.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    @Column(name = "User_Id", length = 10)
    private Integer userId;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "fileList", cascade=CascadeType.REMOVE)
    private List<FTag> tagFileList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "User_Id", insertable = false, updatable = false)
    private HocSinh hocSinh;

    @Transient
    private String strTags;

    public String getStrTags() {
        if (tagFileList != null && !tagFileList.isEmpty()) {
            return this.tagFileList.stream()
                                   .map(FTag::getTagName)
                                   .collect(Collectors.joining(","));
        } else {
            return this.strTags;
        }
    }
}
