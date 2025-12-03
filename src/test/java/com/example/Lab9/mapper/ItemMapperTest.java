package com.example.Lab9.mapper;


import com.example.Lab9.Dto.CountryDto;
import com.example.Lab9.Dto.ItemDto;
import com.example.Lab9.Entity.Country;
import com.example.Lab9.Entity.Item;
import com.example.Lab9.Mapper.ItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ItemMapperTest {

    @Autowired
    private ItemMapper itemMapper;

    @Test
    void convertEntityToDto(){

        Country country = new Country();
        country.setId(1L);
        country.setName("China");
        country.setCode("Ch");


        Item item = new Item();
        item.setId(1L);
        item.setName("Car");
        item.setPrice(100);
        item.setQuantity(1000);
        item.setManufacturer(country);

        ItemDto itemDto = itemMapper.toDto(item);

        Assertions.assertNotNull(itemDto);

        Assertions.assertNotNull(itemDto.getId());
        Assertions.assertNotNull(itemDto.getName());
        Assertions.assertNotNull(itemDto.getPrice());
        Assertions.assertNotNull(itemDto.getCountryId());
        Assertions.assertNotNull(itemDto.getQuantity());

        Assertions.assertEquals(itemDto.getId(),item.getId());
        Assertions.assertEquals(itemDto.getName(),item.getName());
        Assertions.assertEquals(itemDto.getPrice(),item.getPrice());
        Assertions.assertEquals(itemDto.getQuantity(),item.getQuantity());
        Assertions.assertEquals(itemDto.getCountryId(),item.getManufacturer().getId());

    }

    @Test
    void convertDtoToEntity(){


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



        Item item = itemMapper.toEntity(itemDto);


        Assertions.assertNotNull(item);

        Assertions.assertNotNull(item.getId());
        Assertions.assertNotNull(item.getName());
        Assertions.assertNotNull(item.getPrice());
        Assertions.assertNotNull(item.getManufacturer());
        Assertions.assertNotNull(item.getQuantity());

        Assertions.assertEquals(itemDto.getId(),item.getId());
        Assertions.assertEquals(itemDto.getName(),item.getName());
        Assertions.assertEquals(itemDto.getPrice(),item.getPrice());
        Assertions.assertEquals(itemDto.getQuantity(),item.getQuantity());
        Assertions.assertEquals(itemDto.getCountryId(),item.getManufacturer().getId());
    }


    @Test
    void convertEntityListToDtoList(){
        Country country = new Country();
        country.setId(1L);
        country.setName("China");
        country.setCode("Ch");

        List<Item> items = new ArrayList<>();
        items.add(new Item(1L, "Iphone 16", 600000, 100,country));
        items.add(new Item(2L, "Iphone 16S", 700000, 80,country));
        items.add(new Item(3L, "Iphone 16 Pro", 900000, 50,country));
        items.add(new Item(4L, "Iphone 16 Pro Max", 1000000, 20,country));

        List<ItemDto> itemDtoList = itemMapper.toDtoList(items);

        Assertions.assertNotNull(itemDtoList);

        Assertions.assertNotEquals(0,itemDtoList.size());

        Assertions.assertEquals(itemDtoList.size(),items.size());


        for(int i=0;i<itemDtoList.size();i++){

            ItemDto itemDto = itemDtoList.get(i);
            Item item = items.get(i);

            Assertions.assertNotNull(itemDto);

            Assertions.assertNotNull(itemDto.getId());
            Assertions.assertNotNull(itemDto.getName());
            Assertions.assertNotNull(itemDto.getQuantity());
            Assertions.assertNotNull(itemDto.getPrice());
            Assertions.assertNotNull(itemDto.getCountryId());


            Assertions.assertEquals(itemDto.getId(),item.getId());
            Assertions.assertEquals(itemDto.getName(),item.getName());
            Assertions.assertEquals(itemDto.getQuantity(),item.getQuantity());
            Assertions.assertEquals(itemDto.getPrice(),item.getPrice());
            Assertions.assertEquals(itemDto.getCountryId(),item.getManufacturer().getId());

        }

    }
}
