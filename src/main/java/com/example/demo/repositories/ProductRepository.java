package com.example.demo.repositories;

 import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Product;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Transactional
    @Query(
        value = "SELECT p.*, c.name AS category_name \r\n" + //
                        "FROM products p\r\n" + //
                        "INNER JOIN categories c ON p.category_id = c.category_id\r\n" + //
                        "WHERE p.is_deleted = false;",
        nativeQuery = true
    )
    List<Product> getallproduct();

    @Transactional
    @Modifying
    @Query(
        value="update products u set u.is_deleted = true where u.p_id = ?",
        nativeQuery =true
    )
    void deletebycond(int id);

    @Transactional
    @Modifying
    @Query(
        value="update products u set u.is_deleted = true where u.category_id = ?",
        nativeQuery =true
    )
    void deleteproductbycat(int id);

    @Transactional
  
    @Query(
        value="select * from products where products.p_id = ?",
        nativeQuery =true
    )
    Product findById(Long id);

    @Transactional
    @Query(
        value="select * from products where products.p_id = ?",
        nativeQuery =true
    )
    Product getbyid(int pid);

    
    
}
 
    

