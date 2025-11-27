package com.example.Lab9.Service.ServiceImpl;

import com.example.Lab9.Dto.ItemDto;
import com.example.Lab9.Entity.Item;
import com.example.Lab9.Mapper.ItemMapper;
import com.example.Lab9.Repository.ItemRepository;
import com.example.Lab9.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public List<ItemDto> getItems() {
        List<Item> items = itemRepository.findAll();
        return itemMapper.toDtoList(items);
    }

    @Override
    public ItemDto getItem(Long id) {
        Item item = itemRepository.findById(id).orElse(null);

        return itemMapper.toDto(item);
    }

    @Override
    public ItemDto addItem(ItemDto itemDto) {
        Item item = itemMapper.toEntity(itemDto);
        Item item1= itemRepository.save(item);
        ItemDto itemDto1 = itemMapper.toDto(item1);
        return itemDto1;
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto) {
        Item item = itemMapper.toEntity(itemDto);
        Item item1= itemRepository.save(item);
        ItemDto itemDto1 = itemMapper.toDto(item1);
        return itemDto1;
    }

    @Override
    public boolean deleteItem(Long id) {
        ItemDto itemDto = getItem(id);
        if(Objects.isNull(itemDto)){
            return false;
        }
        itemRepository.deleteById(id);
        return true;
    }
}
