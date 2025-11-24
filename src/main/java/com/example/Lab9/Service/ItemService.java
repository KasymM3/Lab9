package com.example.Lab9.Service;

import com.example.Lab9.Dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> getItems();
    ItemDto getItem(Long id);
    ItemDto addItem(ItemDto itemDto);
    ItemDto updateItem(ItemDto itemDto);
    boolean deleteItem(Long id);
}
