package vn.com.fsoft.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "HocSinh")
@Table(name = "HocSinh")
public class HocSinh {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer maHocSinh;

	@Column(name = "Ten_Hoc_Sinh", length = 30)
	private String tenHocSinh;

	@Column(name = "Ma_Lop", length = 5)
	private Integer maLop;

	@Column(name = "Username", length = 50)
	private String username;

	@Column(name = "Password", length = 20)
    private String password;

	@Column(name = "Role_Id", length = 5)
    private Integer roleId;

	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "Role_Id", insertable = false, updatable = false)
    private Role role;

	@Transient
	private List<Permission> permissionList;
}
