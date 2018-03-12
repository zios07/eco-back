package ma.fgs.product.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import ma.fgs.product.domain.Product;

public class CustomJpaRepository extends SimpleJpaRepository<Product, Long> {

	public CustomJpaRepository(Class<Product> domainClass, EntityManager em) {
		super(domainClass, em);
	}
		
}