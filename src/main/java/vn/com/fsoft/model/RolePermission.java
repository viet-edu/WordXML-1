package vn.com.fsoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Role_Permission")
@Table(name = "Role_Permission")
@IdClass(RolePermissionPK.class)
public class RolePermission {

    @Id
    @Column(name = "Role_Id", length = 10)
    private Integer roleId;

    @Id
    @Column(name = "Permission_Id", length = 10)
    private Integer permissionId;
}
