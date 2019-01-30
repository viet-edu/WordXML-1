package vn.com.fsoft.service;

import java.util.List;

import vn.com.fsoft.model.HocSinh;

public interface HocSinhService {

    HocSinh findByUsername(String username);

    HocSinh saveHocSinh(HocSinh hocSinh) throws Exception;

    List<HocSinh> getAllHocSinh();

    void deleteHocSinh(Integer maHocSinh);

    HocSinh findByMaHocSinh(Integer maHocSinh);

    HocSinh updateHocSinh(HocSinh hocSinh) throws Exception;

    HocSinh findCurrentUser();
}
