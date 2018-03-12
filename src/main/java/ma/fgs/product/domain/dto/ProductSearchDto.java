package ma.fgs.product.domain.dto;

import java.io.Serializable;
import java.util.List;

import ma.fgs.product.domain.Brand;

public class ProductSearchDto implements Serializable {

	private static final long serialVersionUID = -4717383402608661083L;
	
	private String productLabel;
	
	private Double minPrice;
	
	private Double maxPrice;
	
	private List<Brand> brands;
	
	public ProductSearchDto() {
		
	}
	
	public ProductSearchDto(String productLabel, Double minPrice, Double maxPrice, List<Brand> brands) {
		super();
		this.productLabel = productLabel;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.brands = brands;
	}

	public String getProductLabel() {
		return productLabel;
	}

	public void setProductLabel(String productLabel) {
		this.productLabel = productLabel;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
