package ma.fgs.product.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Brand {

	@Id @GeneratedValue
	private Long id;
	
	private String code;
	
	private String label;
	
	private String country;
	
	private byte[] avatar;
 
	
	public Brand() {
		super();
	}
	
	public Brand(Long id, String code, String label, String country, byte[] avatar) {
		super();
		this.id = id;
		this.code = code;
		this.label = label;
		this.country = country;
		this.avatar = avatar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
}
