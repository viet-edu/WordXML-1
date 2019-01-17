package vn.com.fsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.fsoft.model.HocSinh;

public interface HocSinhRepository extends JpaRepository<HocSinh, Integer>{
    HocSinh findByUsername(String username);
}
