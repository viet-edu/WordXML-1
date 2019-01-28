package vn.com.fsoft.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileTagPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private String tagName;
    private String fileId;
}
