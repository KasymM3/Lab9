package com.example.Lab9.mapper;

import com.example.Lab9.Dto.CountryDto;
import com.example.Lab9.Entity.Country;
import com.example.Lab9.Entity.Item;
import com.example.Lab9.Mapper.CountryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CountryMapperTest {

    @Autowired
    private CountryMapper countryMapper;

    @Test
    void convertEntityToDto(){

        Item item1 = new Item();
        item1.setId(1L);
        item1.setName("Car");
        item1.setPrice(100);
        item1.setQuantity(1000);

        Item item2 = new Item();
        item2.setId(2L);
        item2.setName("Vam");
        item2.setPrice(100);
        item2.setQuantity(1000);
        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        Country country = new Country(1L,"China","CH", items);

        CountryDto countryDto = countryMapper.toDto(country);

        Assertions.assertNotNull(countryDto);

        Assertions.assertNotNull(countryDto.getId());
        Assertions.assertNotNull(countryDto.getName());
        Assertions.assertNotNull(countryDto.getCode());

        Assertions.assertEquals(countryDto.getId(),country.getId());
        Assertions.assertEquals(countryDto.getName(),country.getName());
        Assertions.assertEquals(countryDto.getCode(),country.getCode());


    }

    @Test
    void convertDtoToEntity(){

        CountryDto countryDto = new CountryDto(1L,"China","CH");

        Country country = countryMapper.toEntity(countryDto);

        Assertions.assertNotNull(country);


        Assertions.assertNotNull(country.getId());
        Assertions.assertNotNull(country.getName());
        Assertions.assertNotNull(country.getCode());

        Assertions.assertEquals(countryDto.getId(),country.getId());
        Assertions.assertEquals(countryDto.getName(),country.getName());
        Assertions.assertEquals(countryDto.getCode(),country.getCode());

    }

    @Test
    void convertEntityListToDtoList() {
        List<Country> countries = new ArrayList<>();

        Country c1 = new Country();
        c1.setId(1L);
        c1.setName("China");
        c1.setCode("CH");

        Country c2 = new Country();
        c2.setId(2L);
        c2.setName("Kazakhstan");
        c2.setCode("KZ");

        Country c3 = new Country();
        c3.setId(3L);
        c3.setName("USA");
        c3.setCode("US");

        countries.add(c1);
        countries.add(c2);
        countries.add(c3);

        List<CountryDto> countryDtoList = countryMapper.toDtoList(countries);

        Assertions.assertNotNull(countryDtoList);
        Assertions.assertNotEquals(0, countryDtoList.size());
        Assertions.assertEquals(countries.size(), countryDtoList.size());

        for (int i = 0; i < countryDtoList.size(); i++) {
            CountryDto dto = countryDtoList.get(i);
            Country entity = countries.get(i);

            Assertions.assertNotNull(dto);

            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
            Assertions.assertNotNull(dto.getCode());

            Assertions.assertEquals(entity.getId(), dto.getId());
            Assertions.assertEquals(entity.getName(), dto.getName());
            Assertions.assertEquals(entity.getCode(), dto.getCode());
        }
    }

}
