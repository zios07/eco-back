package ma.fgs.product.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Product {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private String code;

  private String label;

  private String description;

  private Double price;

  private int qteStock;

  private String uuid;

  @Transient
  private Set<ProductImage> images;

  @ManyToOne
  private Brand brand;

  @ManyToOne
  private Category category;

  @ManyToOne
  private User owner;

  private Date addedDate;

  public Product() {
  }

  public Product(String id, String code, String label, double price, Brand brand,
                 String description, int qteStock, User owner, String uuid, Set<ProductImage> images,
                 Category category, Date addedDate) {
    super();
    this.id = id;
    this.code = code;
    this.label = label;
    this.description = description;
    this.price = price;
    this.brand = brand;
    this.qteStock = qteStock;
    this.owner = owner;
    this.uuid = uuid;
    this.images = images;
    this.category = category;
    this.addedDate = addedDate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getPrice() {
    return price;
  }

  public int getQteStock() {
    return qteStock;
  }

  public void setQteStock(int qteStock) {
    this.qteStock = qteStock;
  }

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(Brand brand) {
    this.brand = brand;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }


  public Set<ProductImage> getImages() {
    return images;
  }

  public void setImages(Set<ProductImage> images) {
    this.images = images;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Date getAddedDate() {
    return addedDate;
  }

  public void setAddedDate(Date addedDate) {
    this.addedDate = addedDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return qteStock == product.qteStock &&
      Objects.equals(id, product.id) &&
      Objects.equals(code, product.code) &&
      Objects.equals(label, product.label) &&
      Objects.equals(description, product.description) &&
      Objects.equals(price, product.price) &&
      Objects.equals(uuid, product.uuid) &&
      Objects.equals(brand, product.brand) &&
      Objects.equals(category, product.category) &&
      Objects.equals(owner, product.owner);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, code, label, description, price, qteStock, uuid, images, brand, category, owner);
  }

  @Override
  public String toString() {
    return "Product{" +
      "id='" + id + '\'' +
      ", code='" + code + '\'' +
      ", label='" + label + '\'' +
      ", description='" + description + '\'' +
      ", price=" + price +
      ", qteStock=" + qteStock +
      ", uuid='" + uuid + '\'' +
      ", images=" + images +
      ", brand=" + brand +
      ", category=" + category +
      ", owner=" + owner +
      '}';
  }
}
