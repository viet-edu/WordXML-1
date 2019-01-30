package vn.com.fsoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.com.fsoft.model.FileConverted;

public interface FileRepository extends JpaRepository<FileConverted, String> {

    @Query("select f.fileId from Files f where f.type like CONCAT('%',?1,'%')")
    List<String> findFileIdByType(String type);


    @Query("select ft.fileId from FileTag ft where ft.tagName in ?1")
    String[] findFileIdByTags(String[] tags);

    List<FileConverted> findByType(String type);

    @Query("select f from Files f where f.fileId in ?1")
    List<FileConverted> getFileByIdList(List<String> ids);
}
