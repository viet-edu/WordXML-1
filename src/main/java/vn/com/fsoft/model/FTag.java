package vn.com.fsoft.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Tag")
@Table(name = "Tag")

public class FTag {

    public FTag(String tagName) {
        this.tagName = tagName;
    }

    @Id
    @Column(name = "Tag_Name", length = 50)
    private String tagName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "File_Tag",
            joinColumns = { @JoinColumn(name = "Tag_Name") },
            inverseJoinColumns = { @JoinColumn(name = "File_Id") })
    private List<FileConverted> fileList = new ArrayList<FileConverted>();
}
