package com.example.rent.controller;

import com.example.rent.model.Category;
import com.example.rent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add")
    public String newCategory(Model model){
        model.addAttribute("category",new Category());
        return "/pages/add_category";
    }

    @PostMapping("/add")
    public String addNewCategory(@ModelAttribute Category category){
        categoryService.addCategory(category);
        return "/pages/add_category";
    }
}
