package ma.fgs.product.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Category {

  @Id
  @GeneratedValue
  private Long id;

  private String code;

  private String label;

  public Category() {
  }

  public Category(String code, String label) {
    this.code = code;
    this.label = label;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Category category = (Category) o;
    return Objects.equals(id, category.id) &&
      Objects.equals(code, category.code) &&
      Objects.equals(label, category.label);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, code, label);
  }
}
