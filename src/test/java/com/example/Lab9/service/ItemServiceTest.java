package com.example.Lab9.service;

import com.example.Lab9.Dto.ItemDto;
import com.example.Lab9.Entity.Country;
import com.example.Lab9.Entity.Item;
import com.example.Lab9.Service.ServiceImpl.CountryServiceImpl;
import com.example.Lab9.Service.ServiceImpl.ItemServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;


@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemServiceImpl itemService;

    @Autowired
    private CountryServiceImpl countryService;

    @Test
    void getAllItemTest(){
        List<ItemDto> itemDtoList = itemService.getItems();

        Assertions.assertNotNull(itemDtoList);

        Assertions.assertNotEquals(0,itemDtoList.size());

        for(ItemDto itemDto: itemDtoList){

            Assertions.assertNotNull(itemDto.getId());
            Assertions.assertNotNull(itemDto.getName());
            Assertions.assertNotNull(itemDto.getQuantity());
            Assertions.assertNotNull(itemDto.getPrice());
            Assertions.assertNotNull(itemDto.getCountryId());
        }


    }


    @Test
    void getItemByIdTest(){
        Random random = new Random();

        int num = random.nextInt(itemService.getItems().size());

        Long id = itemService.getItems().get(num).getId();

        ItemDto itemDto = itemService.getItem(id);

        Assertions.assertNotNull(itemDto);

        Assertions.assertNotNull(itemDto.getId());
        Assertions.assertNotNull(itemDto.getName());
        Assertions.assertNotNull(itemDto.getQuantity());
        Assertions.assertNotNull(itemDto.getPrice());
        Assertions.assertNotNull(itemDto.getCountryId());


        ItemDto mockTest = itemService.getItem(-1L);
        Assertions.assertNull(mockTest);
    }

    @Test
    void addItemTest(){
        Random random = new Random();

        int countryNum = random.nextInt(countryService.getCountries().size());

        Long countryId = countryService.getCountries().get(countryNum).getId();


        ItemDto itemDto = new ItemDto();
        itemDto.setName("CCC");
        itemDto.setPrice(100);
        itemDto.setQuantity(1000);
        itemDto.setCountryId(countryId);


        ItemDto created = itemService.addItem(itemDto);



        Assertions.assertNotNull(created);

        Assertions.assertNotEquals(0,created.getId());

        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getName());
        Assertions.assertNotNull(created.getQuantity());
        Assertions.assertNotNull(created.getPrice());
        Assertions.assertNotNull(created.getCountryId());



        Assertions.assertEquals(created.getName(),itemDto.getName());
        Assertions.assertEquals(created.getQuantity(),itemDto.getQuantity());
        Assertions.assertEquals(created.getPrice(),itemDto.getPrice());
        Assertions.assertEquals(created.getCountryId(),itemDto.getCountryId());


        ItemDto getted = itemService.getItem(created.getId());

        Assertions.assertNotNull(getted.getId());
        Assertions.assertNotNull(getted.getName());
        Assertions.assertNotNull(getted.getQuantity());
        Assertions.assertNotNull(getted.getPrice());
        Assertions.assertNotNull(getted.getCountryId());


        Assertions.assertEquals(getted.getId(),created.getId());
        Assertions.assertEquals(getted.getName(),created.getName());
        Assertions.assertEquals(getted.getQuantity(),created.getQuantity());
        Assertions.assertEquals(getted.getPrice(),created.getPrice());
        Assertions.assertEquals(getted.getCountryId(),created.getCountryId());



    }


    @Test
    void updateItemTest(){

        Random random = new Random();
        int num = random.nextInt(itemService.getItems().size());

        int countryNum = random.nextInt(countryService.getCountries().size());

        Long id = itemService.getItems().get(num).getId();

        Long countryId = countryService.getCountries().get(countryNum).getId();


        ItemDto itemDto = new ItemDto();
        itemDto.setId(id);
        itemDto.setName("Car");
        itemDto.setPrice(100);
        itemDto.setQuantity(1000);
        itemDto.setCountryId(countryId);



        ItemDto updated = itemService.updateItem(itemDto.getId(),itemDto);

        Assertions.assertNotNull(updated);

        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getName());
        Assertions.assertNotNull(updated.getCountryId());
        Assertions.assertNotNull(updated.getPrice());
        Assertions.assertNotNull(updated.getQuantity());


        Assertions.assertEquals(updated.getId(),itemDto.getId());
        Assertions.assertEquals(updated.getName(),itemDto.getName());
        Assertions.assertEquals(updated.getCountryId(),itemDto.getCountryId());
        Assertions.assertEquals(updated.getPrice(),itemDto.getPrice());
        Assertions.assertEquals(updated.getQuantity(),itemDto.getQuantity());



        ItemDto get = itemService.getItem(itemDto.getId());

        Assertions.assertNotNull(get.getId());
        Assertions.assertNotNull(get.getName());
        Assertions.assertNotNull(get.getCountryId());
        Assertions.assertNotNull(get.getPrice());
        Assertions.assertNotNull(get.getQuantity());


        Assertions.assertEquals(get.getId(),itemDto.getId());
        Assertions.assertEquals(get.getName(),itemDto.getName());
        Assertions.assertEquals(get.getCountryId(),itemDto.getCountryId());
        Assertions.assertEquals(get.getPrice(),itemDto.getPrice());
        Assertions.assertEquals(get.getQuantity(),itemDto.getQuantity());

        ItemDto mockTest= itemService.updateItem(-1L,updated);

        Assertions.assertNull(mockTest);



    }


    @Test
    void deleteItemTest(){
        Random random = new Random();
        int num = random.nextInt(itemService.getItems().size());

        Long id = itemService.getItems().get(num).getId();

        boolean deleted = itemService.deleteItem(id);

        Assertions.assertTrue(deleted);

        ItemDto get = itemService.getItem(id);

        Assertions.assertNull(get);

        boolean deleted1 = itemService.deleteItem(id);
        Assertions.assertFalse(deleted1);
    }
}
