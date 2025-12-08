package com.example.Lab9.service;

import com.example.Lab9.Dto.CountryDto;
import com.example.Lab9.Service.ServiceImpl.CountryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class CountryServiceTest {

    @Autowired
    private CountryServiceImpl countryService;

    @Test
    void getAllCountriesTest(){

        List<CountryDto> countryDtoList = countryService.getCountries();

        Assertions.assertNotNull(countryDtoList);

        Assertions.assertNotEquals(0,countryDtoList.size());

        for(CountryDto countryDto: countryDtoList){


            Assertions.assertNotNull(countryDto);

            Assertions.assertNotNull(countryDto.getCode());
            Assertions.assertNotNull(countryDto.getId());
            Assertions.assertNotNull(countryDto.getName());
        }
    }

    @Test
    void getCountryByIdTest(){
        Random random =new Random();
        int num = random.nextInt(countryService.getCountries().size());

        Long id = countryService.getCountries().get(num).getId();

        CountryDto countryDto = countryService.getCountry(id);


        Assertions.assertNotNull(countryDto);

        Assertions.assertNotNull(countryDto.getName());
        Assertions.assertNotNull(countryDto.getId());
        Assertions.assertNotNull(countryDto.getCode());

        CountryDto mockTest = countryService.getCountry(-1L);
        Assertions.assertNull(mockTest);
    }

    @Test
    void addCountryTest(){

        CountryDto countryDto = new CountryDto();
        countryDto.setName("My");
        countryDto.setCode("my");


        CountryDto created = countryService.addCountry(countryDto);

        Assertions.assertNotNull(created);

        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getCode());
        Assertions.assertNotNull(created.getName());


        Assertions.assertEquals(created.getName(),countryDto.getName());
        Assertions.assertEquals(created.getCode(),countryDto.getCode());


        CountryDto get = countryService.getCountry(created.getId());

        Assertions.assertNotNull(get);

        Assertions.assertNotNull(get.getId());
        Assertions.assertNotNull(get.getCode());
        Assertions.assertNotNull(get.getName());

        Assertions.assertEquals(created.getId(),get.getId());
        Assertions.assertEquals(created.getName(),get.getName());
        Assertions.assertEquals(created.getCode(),get.getCode());


    }

    @Test
    void updateCountryTest(){

        Random random = new Random();
        int num = random.nextInt(countryService.getCountries().size());

        Long id = countryService.getCountries().get(num).getId();

        CountryDto countryDto = new CountryDto();
        countryDto.setId(id);
        countryDto.setName("Test");
        countryDto.setCode("ts");

        CountryDto updated =  countryService.updateCountry(id,countryDto);

        Assertions.assertNotNull(updated);

        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getName());
        Assertions.assertNotNull(updated.getCode());

        Assertions.assertEquals(updated.getId(),countryDto.getId());
        Assertions.assertEquals(updated.getName(),countryDto.getName());
        Assertions.assertEquals(updated.getCode(),countryDto.getCode());


        CountryDto get = countryService.getCountry(updated.getId());

        Assertions.assertNotNull(get);

        Assertions.assertNotNull(get.getId());
        Assertions.assertNotNull(get.getName());
        Assertions.assertNotNull(get.getCode());

        Assertions.assertEquals(updated.getId(),get.getId());
        Assertions.assertEquals(updated.getName(),get.getName());
        Assertions.assertEquals(updated.getCode(),get.getCode());


        CountryDto mockTest = countryService.updateCountry(-1L,updated);
        Assertions.assertNull(mockTest);

    }

    @Test
    void deleteCountryTest(){

        Random random = new Random();
        int num = random.nextInt(countryService.getCountries().size());

        Long id = countryService.getCountries().get(num).getId();

        boolean deleted = countryService.deleteCountry(id);

        Assertions.assertTrue(deleted);

        CountryDto get = countryService.getCountry(id);
        Assertions.assertNull(get);

        boolean deleted1 = countryService.deleteCountry(id);
        Assertions.assertFalse(deleted1);




    }
}
