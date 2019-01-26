package vn.com.fsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import vn.com.fsoft.model.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Role_Permission p WHERE p.roleId = :roleId")
    void deleteRolePermissionByRoleId(@Param("roleId") Integer roleId);
}
