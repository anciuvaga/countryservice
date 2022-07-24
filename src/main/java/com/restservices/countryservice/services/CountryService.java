package com.restservices.countryservice.services;

import com.restservices.countryservice.beans.Country;
import com.restservices.countryservice.controllers.AddResponse;
import com.restservices.countryservice.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return  countryRepository.findAll();
    }

    public Country getCountryById(int id) {
        return countryRepository.findById(id).get();
    }

    public Country getCountryByName(String countryName){
       List<Country> countries =  countryRepository.findAll();
       Country country = null;
       for(Country element : countries ){
           if(element.getCountryName().equalsIgnoreCase(countryName))
               country = element;
       }
       return country;
    }

    public Country addcountry(Country country) {
        country.setId(getMaxId());
        countryRepository.save(country);
        return country;
    }

    public  int getMaxId() {
        return countryRepository.findAll().size() + 1;
    }

    public Country updateCountry(Country country) {
        countryRepository.save(country);
        return country;
    }

    public AddResponse deleteCountry(int id) {
        countryRepository.deleteById(id);
        AddResponse response = new AddResponse();
        response.setMsg("Country deleted...");
        response.setId(id);
        return response;
    }

}
