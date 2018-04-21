package ma.fgs.product.service;

import ma.fgs.product.domain.Category;
import ma.fgs.product.repository.CategoryRepository;
import ma.fgs.product.service.api.ICategoryService;
import ma.fgs.product.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

  @Autowired
  private CategoryRepository repo;

  @Override
  public Category createCategory(Category category) {
    return repo.save(category);
  }

  @Override
  public Category updateCategory(Category category) throws NotFoundException {
    if (!repo.exists(category.getId()))
      throw new NotFoundException("CATEGORY.UPDATE.ERROR", "Category not found : " + category.getId());
    return repo.save(category);
  }

  @Override
  public void deleteCategory(Long id) throws NotFoundException {
    if (!repo.exists(id))
      throw new NotFoundException("CATEGORY.UPDATE.ERROR", "Category not found : " + id);
    repo.delete(id);
  }

  @Override
  public Category findCategory(Long id) throws NotFoundException {
    if (!repo.exists(id))
      throw new NotFoundException("CATEGORY.UPDATE.ERROR", "Category not found : " + id);
    return repo.findOne(id);
  }

  @Override
  public List<Category> findCategories() {
    return repo.findAll();
  }
}
