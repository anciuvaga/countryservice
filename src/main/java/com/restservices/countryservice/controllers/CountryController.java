package com.restservices.countryservice.controllers;

import com.restservices.countryservice.beans.Country;
import com.restservices.countryservice.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("/getcountries")
    public List<Country> getCountries() {
       return countryService.getAllCountries();
    }

    @GetMapping("/getcountries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable(value = "id") int id) {
        try{
            Country country = countryService.getCountryById(id);
            return new ResponseEntity<>(country, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcountries/countryname")
    public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String countryName) {
        try{
           Country country = countryService.getCountryByName(countryName);
           return new ResponseEntity<>(country, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addcountry")
    public Country addCountry(@RequestBody Country country) {
        return countryService.addcountry(country);
    }

    @PutMapping("/updatecountry/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") int id, @RequestBody Country country) {
        try {
            Country existCountry = countryService.getCountryById(id);

            existCountry.setCountryName(country.getCountryName());
            existCountry.setCountryCapital(country.getCountryCapital());

            Country updatedCountry = countryService.updateCountry(existCountry);
            return new ResponseEntity<>(updatedCountry, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deletecountry/{id}")
    public ResponseEntity<AddResponse> deleteCountry(@PathVariable(value = "id") int id) {
        try{
            AddResponse response = countryService.deleteCountry(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
