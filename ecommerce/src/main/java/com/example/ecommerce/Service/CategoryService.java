package com.example.ecommerce.Service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.Categoryrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private Categoryrepository categoryrepository;

    public List<Category> listCatogories (){
        return categoryrepository.findAll();
    }

    public void createCategory(Category category){
        categoryrepository.save(category);
    }

    public Category readCategory(String categoryName){
        return categoryrepository.findByCategoryName(categoryName);
    }

    public Optional<Category> readCategory(Integer categoryId){
        return  categoryrepository.findById(categoryId);
    }

    public void updateCategory(Integer categoryId, Category newCategory){
        Category category = categoryrepository.findById(categoryId).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setImageUrl(newCategory.getImageUrl());
        categoryrepository.save(category);
    }
}
