package vn.com.fsoft.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePermissionPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer roleId;
    private Integer permissionId;
}
