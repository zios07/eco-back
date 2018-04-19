package ma.fgs.product.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class  Brand {

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Brand brand = (Brand) o;

		if (id != null ? !id.equals(brand.id) : brand.id != null) return false;
		if (code != null ? !code.equals(brand.code) : brand.code != null) return false;
		if (label != null ? !label.equals(brand.label) : brand.label != null) return false;
		if (country != null ? !country.equals(brand.country) : brand.country != null) return false;
		return Arrays.equals(avatar, brand.avatar);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (code != null ? code.hashCode() : 0);
		result = 31 * result + (label != null ? label.hashCode() : 0);
		result = 31 * result + (country != null ? country.hashCode() : 0);
		result = 31 * result + Arrays.hashCode(avatar);
		return result;
	}
}
