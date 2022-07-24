package com.restservices.countryservice.repositories;

import com.restservices.countryservice.beans.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
