package vn.com.fsoft.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Permission_Id", length = 5)
    private Integer permissionId;

    @Column(name = "Permission_Name", length = 50)
    private String name;

    @Transient
    private boolean isCheck;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "permissionList", cascade = CascadeType.ALL)
    private List<Role> roleList = new ArrayList<>();
}

