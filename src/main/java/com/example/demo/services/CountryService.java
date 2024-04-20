package com.example.demo.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Country;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.CustomRepository; 

@Service
public class CountryService {

    @Autowired
    public CountryRepository countryrepository;

    @Autowired
    public CustomRepository customRepository;

    // return list of countries
    public List<Country> getcountries() {
        List<Country> c= countryrepository.findByIsDeleted(0);
        System.out.println(c);
        return(c);
        
    }

    public List<Country> getcountries1() {
        List<Country> c= customRepository.findByCondition(3);
        System.out.println(c);
        return(c);
        
    }

    public void savecountry(Country country){
        countryrepository.save(country);
    }

	public Optional<Country> findbyid(int id) {
       
		return countryrepository.findById(id);
	}

    public void deletecountry(int id) {
        // Optional<Country> countryOptional = countryrepository.findById(id);
        // countryOptional.ifPresent(country -> {
            
        //     country.setIs_deleted(3); 
        //     countryrepository.save(country); 
        // });
        countryrepository.deletebycond(id);
    }
    
}
