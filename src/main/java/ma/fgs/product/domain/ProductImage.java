package ma.fgs.product.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_IMAGE")
public class ProductImage {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String type;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] content;

	@ManyToOne(optional = false)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	public ProductImage() {

	}

	public ProductImage(Long id, String name, String type, byte[] content, Product product) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.content = content;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
