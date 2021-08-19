package com.example.rent.service;

import com.example.rent.Exception.BadRequest;
import com.example.rent.Exception.EntityAlreadyExsist;
import com.example.rent.Exception.IdNotFoundException;
import com.example.rent.model.Category;
import com.example.rent.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    public String addCategory(Category category){
        Optional<Category> categories=findCategoryByName(category.getName());
        if(categories.isPresent() ||isExistsCategory(category.getId())){
            throw  new EntityAlreadyExsist();
        }
        categoryRepository.save(category);
        return "category is added successfully";
    }

    public String deleteCategory(int id) {
        Optional<Category> categories=findCategoryById(id);
       if(!categories.isPresent()){
           throw  new IdNotFoundException();
       }
       if(!canDeleteCategory(categories.get())) {
           throw  new BadRequest();
       }
        categoryRepository.deleteById(id);
        return "category is deleted successfully";
    }

    public String updateCategory(Category category){
        Optional<Category> categories=findCategoryById(category.getId());
        if(!categories.isPresent()) throw  new IdNotFoundException();
        if(categoryRepository.existsByName(category.getName())) throw new EntityAlreadyExsist();

        categoryRepository.save(category);
        return "category is updated successfully";
    }

    public List<Category> getAllCategories(){
       return categoryRepository.findAll();
    }

   public Optional<Category> findCategoryById(int id){
        return categoryRepository.findById(id);
    }

    public Optional<Category> findCategoryByName(String name){
        return categoryRepository.getCategoriesByName(name);
    }
    public boolean isExistsCategory(int id){
        return categoryRepository.existsById(id);
    }
    public boolean canDeleteCategory(Category category){
        return productService.getAllProductsByCategory(category).isEmpty();

    }

}
