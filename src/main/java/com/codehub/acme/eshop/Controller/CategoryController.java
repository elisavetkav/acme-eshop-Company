package com.codehub.acme.eshop.Controller;

import com.codehub.acme.eshop.domain.Category;
import com.codehub.acme.eshop.domain.Product;
import com.codehub.acme.eshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    /**
     * {@link CategoryService}
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * This Controller returns a List of Categories from the DB
     * @return {@link Category}
     */
    @GetMapping("/categories")
    public List<Category> categoryList() {
        return categoryService.getAllCategories();
    }

    /**
     * This controllers searches and returns a Category {@link Category} from the DB regarding a given Id
     * @return {@link Category}
     */
    @GetMapping( value ="/categories/{categoryId}")
    public Category getCategoryById(@PathVariable Long categoryId){
        return categoryService.getCategoryById(categoryId);
    }
    /**
     * This Controller adds/creates a new Category {@link Category} to the DB
     * @param category
     */
    @PostMapping(value = "/categories/new")
     public void addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        }
    /**
     * This Controller searches and returns a Category{@link Category} from the DB regarding a given name
     * @param categoryName
     * return {@link Category}
      */
    @GetMapping(value ="/categories/search/{categoryName}")
    public Category findByName(@PathVariable String categoryName){
        return categoryService.findByName(categoryName);
    }

    /**
     * This Controller removes a Category from the DB regarding a given name
     */
    @DeleteMapping(value = "/categories/remove/{categoryName}")
    public void deleteCategory(@PathVariable String categoryName){
        categoryService.removeCategory(categoryName);

        /**
         * This Controller removes a Category from the DB regarding a given Id
         */
    }
    @DeleteMapping(value ="/categories/removeById/{categoryId}")
    public void deleteCategorybyId(@PathVariable Long categoryId){
        categoryService.removeCategorybyId(categoryId);
    }






}