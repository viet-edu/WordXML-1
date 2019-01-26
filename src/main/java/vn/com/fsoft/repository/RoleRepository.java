package vn.com.fsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.fsoft.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
