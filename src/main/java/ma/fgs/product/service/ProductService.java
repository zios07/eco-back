package ma.fgs.product.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ma.fgs.product.domain.Product;
import ma.fgs.product.domain.ProductImage;
import ma.fgs.product.domain.dto.ProductSearchDto;
import ma.fgs.product.repository.ProductImageRepository;
import ma.fgs.product.repository.ProductRepository;
import ma.fgs.product.service.api.IProductService;
import ma.fgs.product.service.exception.NotFoundException;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository repo;

	@Autowired
	private ProductImageRepository productImageRepo;

	@Override
	public Product addProduct(Product product) {
		return repo.save(product);
	}

	@Override
	public Product findProduct(String id) throws NotFoundException {
		if (!repo.exists(id))
			throw new NotFoundException("code", "message");
		return repo.findOne(id);
	}

	@Override
	public Page<Product> findAllProducts(int page, int size) {
		return repo.findAll(new PageRequest(page, size));
	}

	@Override
	public void deleteProduct(String id) throws NotFoundException {
		if (!repo.exists(id))
			throw new NotFoundException("code", "message");
		repo.delete(id);
	}

	@Override
	public Page<Product> searchProducts(ProductSearchDto dto, int page, int size) throws NotFoundException {
		Page<Product> products = repo.findAll(new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				final Collection<Predicate> predicates = new ArrayList<>();
				Collection<Predicate> brandPredicates = new ArrayList<>();

				if (!StringUtils.isEmpty(dto.getProductLabel())) {
					Predicate predicate = cb.like(root.get("label"), "%" + dto.getProductLabel() + "%");
					predicates.add(predicate);
				}

				if (dto.getMaxPrice() == null && dto.getMinPrice() != null) {
					Predicate predicate = cb.greaterThan(root.get("price"), dto.getMaxPrice());
					predicates.add(predicate);
				}

				if (dto.getMaxPrice() != null && dto.getMinPrice() == null) {
					Predicate predicate = cb.lessThan(root.get("price"), dto.getMaxPrice());
					predicates.add(predicate);
				}

				if (dto.getMaxPrice() != null && dto.getMinPrice() != null) {
					Predicate predicate = cb.between(root.get("price"), dto.getMinPrice(), dto.getMaxPrice());
					predicates.add(predicate);
				}

				if (dto.getBrands() != null && !dto.getBrands().isEmpty()) {
					brandPredicates = dto.getBrands().stream().map(brand -> {
						Predicate predicate = cb.equal(root.get("brand").get("id"), brand.getId());
						return predicate;
					}).collect(Collectors.toList());
				}

				Predicate orPredicate = cb.or(brandPredicates.toArray(new Predicate[brandPredicates.size()]));
				Predicate andPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));

				if (!brandPredicates.isEmpty())
					return cb.and(new Predicate[] { andPredicate, orPredicate });
				return cb.and(new Predicate[] { andPredicate });
			}
		}, new PageRequest(page, size));
		return products;
	}

	@Override
	public void uploadProductPhotos(MultipartFile[] photos, String uuid) throws IOException {
		if (photos != null) {
			for (MultipartFile photo : photos) {
				ProductImage image = new ProductImage();
				image.setContent(photo.getBytes());
				image.setName(photo.getOriginalFilename());
				image.setType(photo.getContentType());
				Product product = new Product();
				product.setId(uuid);
				image.setProduct(product);
				productImageRepo.save(image);
			}
		}
	}

}
