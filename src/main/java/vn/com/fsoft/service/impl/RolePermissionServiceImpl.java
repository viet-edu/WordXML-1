package vn.com.fsoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.fsoft.model.HocSinh;
import vn.com.fsoft.model.Permission;
import vn.com.fsoft.model.Role;
import vn.com.fsoft.model.RolePermission;
import vn.com.fsoft.repository.RolePermissionRepository;
import vn.com.fsoft.service.HocSinhService;
import vn.com.fsoft.service.RolePermissionService;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private HocSinhService hocSinhService;

    @Override
    public void saveRolePermission(HocSinh hocSinh) throws Exception {
        Role roleFetch = hocSinhService.findByMaHocSinh(hocSinh.getMaHocSinh()).getRole();
        RolePermission rolePermission;
        if (hocSinh.getPermissionList() != null) {
            rolePermissionRepository.deleteRolePermissionByRoleId(roleFetch.getRoleId());
            for (Permission permission : hocSinh.getPermissionList()) {
                if (permission.getPermissionId() != null) {
                    rolePermission = new RolePermission();
                    rolePermission.setRoleId(roleFetch.getRoleId());
                    rolePermission.setPermissionId(permission.getPermissionId());
                    rolePermissionRepository.save(rolePermission);
                }
            }
        }
    }
}
