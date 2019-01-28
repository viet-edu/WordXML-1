package vn.com.fsoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.com.fsoft.model.FileConverted;

public interface FileRepository extends JpaRepository<FileConverted, String> {

    @Query("select f.fileId from Files f where f.type like CONCAT('%',?1,'%')")
    List<String> findFileIdByType(String type);

    List<FileConverted> findByType(String type);
}
