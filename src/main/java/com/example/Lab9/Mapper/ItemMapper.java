package com.example.Lab9.Mapper;


import com.example.Lab9.Dto.ItemDto;
import com.example.Lab9.Entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ItemMapper {


    @Mapping(source = "manufacturer.id" ,target = "countryId")
    ItemDto toDto(Item item);

    @Mapping(source = "countryId", target = "manufacturer.id")
    Item toEntity(ItemDto itemDto);

    List<ItemDto> toDtoList(List<Item> itemList);
}
