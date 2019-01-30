package vn.com.fsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import vn.com.fsoft.model.FileTag;
import vn.com.fsoft.model.FileTagPK;

public interface FileTagRepository extends JpaRepository<FileTag, FileTagPK> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM FileTag ft WHERE ft.fileId = ?1")
    void deleteFileTagByFileId(String fileId);
}
