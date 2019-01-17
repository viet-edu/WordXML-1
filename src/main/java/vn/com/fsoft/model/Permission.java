package vn.com.fsoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}

