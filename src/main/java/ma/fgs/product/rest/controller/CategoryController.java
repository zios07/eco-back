package ma.fgs.product.rest.controller;

import ma.fgs.product.domain.Category;
import ma.fgs.product.service.api.ICategoryService;
import ma.fgs.product.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryController {

  @Autowired
  private ICategoryService service;

  @GetMapping
  public List<Category> loadCategorys() {
    return service.findCategories();
  }

  @GetMapping(value = "{id}")
  public Category getCategory(@PathVariable Long id) throws NotFoundException {
    return service.findCategory(id);
  }

  @PostMapping
  public Category saveCategory(@RequestBody Category category) {
    return service.createCategory(category);
  }

  @DeleteMapping(value = "{id}")
  public void deleteCategory(@PathVariable Long id) throws NotFoundException {
    service.deleteCategory(id);
  }
}
