package ma.fgs.product.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {

	@Id @GeneratedValue
	private Long id;
	
	private String roleCode;
	
	private String roleLabel;

	public Role() {
		
	}
	
	public Role(String roleCode, String roleLabel) {
		super();
		this.roleCode = roleCode;
		this.roleLabel = roleLabel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleLabel() {
		return roleLabel;
	}

	public void setRoleLabel(String roleLabel) {
		this.roleLabel = roleLabel;
	}
	
}
