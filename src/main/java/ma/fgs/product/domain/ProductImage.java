package ma.fgs.product.domain;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_IMAGE")
public class ProductImage {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String type;

  private boolean main;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  private byte[] content;

  @Column(nullable = false, name = "PRODUCT_ID")
  private String productId;

  public ProductImage() {

  }

  public ProductImage(Long id, String name, String type, byte[] content, String productId, boolean main) {
    super();
    this.id = id;
    this.name = name;
    this.type = type;
    this.content = content;
    this.productId = productId;
    this.main = main;
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

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }


  public boolean isMain() {
    return main;
  }

  public void setMain(boolean main) {
    this.main = main;
  }
}
