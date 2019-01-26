package vn.com.fsoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.fsoft.model.Permission;
import vn.com.fsoft.repository.PermissionRepository;
import vn.com.fsoft.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> getPermissionList() {
        return permissionRepository.findAll();
    }

}
