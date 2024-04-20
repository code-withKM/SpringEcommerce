package com.example.demo.repositories;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.ShoppingCart;

@Repository
public interface CartRepository extends JpaRepository<ShoppingCart,Integer>  {
	
	@Transactional
  
    @Query(
        value="select * from carts where carts.user_id = ?1 and carts.p_id=?2",
        nativeQuery =true
    )
	ShoppingCart findByUserAndProduct(int user_id, int pid);

    @Transactional
  
    @Query(
        value="select * from carts where carts.user_id = ?",
        nativeQuery =true
    )
     List<ShoppingCart> findByuserId(int user_id);

  
    
}
