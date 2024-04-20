package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Category;
import com.example.demo.models.Country;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    @Transactional
    @Modifying
    @Query(value = "update categories u set u.is_deleted = true where u.category_id = ?", 
    nativeQuery = true)
    void deletebycond(int id);

    @Transactional
    @Query(
        value = "SELECT * FROM categories u WHERE u.is_deleted = false", 
        nativeQuery = true)
    List<Category> findactive();
    
}
