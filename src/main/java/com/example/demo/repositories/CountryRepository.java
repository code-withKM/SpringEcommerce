package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {

   @Query("SELECT c FROM Country c WHERE c.is_deleted = ?1")
    List<Country> findByIsDeleted(int isDeleted);

    @Transactional
   @Modifying
@Query(value = "update country u set u.is_deleted = 2 where u.id = ?", 
  nativeQuery = true)
   void deletebycond(int id);

    
 
 
  

}
