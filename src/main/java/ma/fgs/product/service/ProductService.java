package ma.fgs.product.service;

import ma.fgs.product.domain.Product;
import ma.fgs.product.domain.ProductImage;
import ma.fgs.product.domain.dto.ProductSearchDto;
import ma.fgs.product.repository.ProductImageRepository;
import ma.fgs.product.repository.ProductRepository;
import ma.fgs.product.service.api.IProductService;
import ma.fgs.product.service.exception.NotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repo;

    // TODO: Some service for product image
    @Autowired
    private ProductImageRepository productImageRepo;

    @Override
    public Product addProduct(Product product) {
        product.setAddedDate(new Date());
        Product createdProduct = repo.save(product);
        this.updateProductIdForPhoto(product.getUuid(), product.getId());
        return createdProduct;
    }

    @Override
    public Product findProduct(String id) throws NotFoundException {
        if (!repo.exists(id))
            throw new NotFoundException("PRODUCT.FIND.ERROR", "Product does not exist :" + id);
        Set<ProductImage> images = productImageRepo.findByProductId(id);
        Product product = repo.findOne(id);
        product.setImages(images);
        return product;
    }

    @Override
    public Page<Product> findAllProducts(int page, int size) {
        Page<Product> products = repo.findAll(new PageRequest(page, size));
        List<ProductImage> mainImages = productImageRepo.findMainImages();
        products.getContent().stream().forEach(product -> {
            product.setImages(getMainImageForProduct(mainImages, product));
        });
        return products;
    }

    @Override
    public void deleteProduct(String id) throws NotFoundException {
        if (!repo.exists(id))
            throw new NotFoundException("PRODUCT.DELETE.ERROR", "Product does not exist :" + id);
        repo.delete(id);
    }

    @Override
    public Page<Product> searchProducts(ProductSearchDto dto, int page, int size) {
        Page<Product> products = repo.findAll((root, cq, cb) -> {
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
                return cb.and(new Predicate[]{andPredicate, orPredicate});
            return cb.and(new Predicate[]{andPredicate});
        }, new PageRequest(page, size));

        if (products.getContent() != null) {
            List<ProductImage> mainImages = productImageRepo.findMainImages();
            products.getContent().stream().forEach(product -> {
                product.setImages(this.getMainImageForProduct(mainImages, product));
            });
        }
        return products;
    }

    @Override
    public void uploadProductPhotos(MultipartFile[] photos, String uuid) throws IOException {
        if (photos != null) {
            List<ProductImage> images = new ArrayList<>();
            for (MultipartFile photo : photos) {
                ProductImage image = new ProductImage();
                image.setContent(photo.getBytes());
                image.setName(photo.getOriginalFilename());
                image.setType(photo.getContentType());
                image.setProductId(uuid);
                images.add(image);
            }
            // TODO: must be the user's choice!
            images.stream().findFirst().get().setMain(true);
            images.stream().forEach(image -> productImageRepo.save(image));
        }
    }

    @Override
    public void updateProductIdForPhoto(String uuid, String newProductId) {
        productImageRepo.updateProductId(uuid, newProductId);
    }


    private Set<ProductImage> getMainImageForProduct(List<ProductImage> mainImages, Product product) {
        Set<ProductImage> images = new HashSet<>();
        mainImages.stream().forEach(productImage -> {
            if (product.getId().equals(productImage.getProductId())) {
                images.add(productImage);
            }
        });
        return images;
    }

}
