package vn.com.fsoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "FileTag")
@Table(name = "File_Tag")
@IdClass(FileTagPK.class)
public class FileTag {

    @Id
    @Column(name = "Tag_Name", length = 50)
    private String tagName;

    @Id
    @Column(name = "File_Id", length = 10)
    private String fileId;
}
