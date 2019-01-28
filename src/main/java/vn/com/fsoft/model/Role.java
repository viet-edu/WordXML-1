package vn.com.fsoft.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Role_Id", length = 10)
    private Integer roleId;

    @Column(name = "Role_Name", length = 50)
    private String name;

    @Column(name = "Role_Level", length = 1)
    private Integer roleLevel;

    @Column(name = "Description", length = 50)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Role_Permission", joinColumns = @JoinColumn(name = "Role_Id"),
        inverseJoinColumns = @JoinColumn(name = "Permission_Id")
    )
    private List<Permission> permissionList = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private Set<HocSinh> userList = new HashSet<>();
}
