package com.example.Lab9.Mapper;

import com.example.Lab9.Dto.CountryDto;
import com.example.Lab9.Entity.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CountryMapper {


    CountryDto toDto(Country country);

    Country toEntity(CountryDto countryDto);

    List<CountryDto> toDtoList(List<Country> countryList);
}
