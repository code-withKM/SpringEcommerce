package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;
@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryrepository;

    public void savecategory(Category category) {
       categoryrepository.save(category);
    }

    public List<Category> getcategory() {
      return categoryrepository.findactive();
    }

    public Optional<Category> findbyid(int id) {
      return  categoryrepository.findById(id);
    }

    public void delete(int id) {
     categoryrepository.deletebycond(id);
    }
}
