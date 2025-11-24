package com.example.Lab9.Service;


import com.example.Lab9.Dto.CountryDto;

import java.util.List;

public interface CountryService {

    List<CountryDto> getCountries();
    CountryDto getCountry(Long id);
    CountryDto addCountry(CountryDto countryDto);
    CountryDto updateCountry(CountryDto countryDto);
    boolean deleteCountry(Long id);
}
