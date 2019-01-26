package vn.com.fsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.fsoft.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}
