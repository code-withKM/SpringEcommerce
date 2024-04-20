package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.models.Country;

@Repository
public class CustomRepository {

    public List<Country> findByCondition(int isDeleted) {
        List<Country> result = new ArrayList<>();
        
        Country country = new Country();
        country.setIs_deleted(isDeleted);
        
        result.add(country);
        
        return result;
    }
}