package com.example.Lab9.service;

import com.example.Lab9.Dto.ItemDto;
import com.example.Lab9.Entity.Country;
import com.example.Lab9.Entity.Item;
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

        Country country = new Country();
        country.setId(1L);
        country.setName("China");
        country.setCode("Ch");


        ItemDto itemDto = new ItemDto();
        itemDto.setId(1L);
        itemDto.setName("Car");
        itemDto.setPrice(100);
        itemDto.setQuantity(1000);
        itemDto.setCountryId(country.getId());


        ItemDto created = itemService.addItem(itemDto);

        Assertions.assertNull(created);

        Assertions.assertEquals(0,created.getId());

        Assertions.assertNotNull(itemDto.getId());
        Assertions.assertNotNull(itemDto.getName());
        Assertions.assertNotNull(itemDto.getQuantity());
        Assertions.assertNotNull(itemDto.getPrice());
        Assertions.assertNotNull(itemDto.getCountryId());


        Assertions.assertEquals(created.getId(),itemDto.getId());
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


        Assertions.assertEquals(getted.getId(),itemDto.getId());
        Assertions.assertEquals(getted.getName(),itemDto.getName());
        Assertions.assertEquals(getted.getQuantity(),itemDto.getQuantity());
        Assertions.assertEquals(getted.getPrice(),itemDto.getPrice());
        Assertions.assertEquals(getted.getCountryId(),itemDto.getCountryId());



    }


    @Test
    void updateItemTest(){

        Random random = new Random();
        int num = random.nextInt(itemService.getItems().size());

        Long id = itemService.getItems().get(num).getId();

        Country country = new Country();
        country.setId(1L);
        country.setName("China");
        country.setCode("Ch");


        ItemDto itemDto = new ItemDto();
        itemDto.setId(id);
        itemDto.setName("Car");
        itemDto.setPrice(100);
        itemDto.setQuantity(1000);
        itemDto.setCountryId(country.getId());



        ItemDto updated = itemService.updateItem(id,itemDto);
    }
}
