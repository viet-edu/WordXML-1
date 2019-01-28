package vn.com.fsoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.com.fsoft.model.FileTag;
import vn.com.fsoft.model.FileTagPK;

public interface FileTagRepository extends JpaRepository<FileTag, FileTagPK> {

}
