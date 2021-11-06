package com.example.ecommerce.controller;

import com.example.ecommerce.Service.CategoryService;
import com.example.ecommerce.common.ApiResponse;
import com.example.ecommerce.model.Category;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategory(){
        List<Category> body = categoryService.listCatogories();
        return new ResponseEntity<>(body , HttpStatus.OK);
    }

    @GetMapping("/create")
    public ResponseEntity<ApiResponse> createcategory(@Valid @RequestBody Category category){
        if (Objects.nonNull(categoryService.readCategory(category.getCategoryName()))){
            return new ResponseEntity<>(new ApiResponse(false,"Category is already exists"),HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true,"Category created"),HttpStatus.CREATED);
    }

    @PostMapping("/update/{categoryId}")
    public  ResponseEntity<ApiResponse> updatecategory(@PathVariable("categoryId") Integer categoryId , @Valid @RequestBody Category category){
        if (Objects.nonNull(categoryService.readCategory(categoryId))){
            categoryService.updateCategory(categoryId,category);
            return new ResponseEntity<>(new ApiResponse(true,"update the category"),HttpStatus.OK);
        }

        return new ResponseEntity<>(new ApiResponse(false,"category is not existes"),HttpStatus.NOT_FOUND);
    }
}
