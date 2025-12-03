package com.example.Lab9.Controller;

import com.example.Lab9.Dto.ItemDto;
import com.example.Lab9.Entity.Item;
import com.example.Lab9.Service.ServiceImpl.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ItemServiceImpl itemService;

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        List<ItemDto> itemDtoList = itemService.getItems();
        if(Objects.isNull(itemDtoList)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(itemDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        ItemDto itemDto = itemService.getItem(id);
        if(Objects.isNull(itemDto)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(itemDto);
    }

    @PostMapping("/")
    public ResponseEntity<?> addItem(@RequestBody ItemDto itemDto){
        ItemDto itemDto1= itemService.addItem(itemDto);
        return ResponseEntity.ok(itemDto1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody ItemDto itemDto){

        ItemDto updated=  itemService.updateItem(id,itemDto);
        if(Objects.isNull(updated)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id){
        boolean result = itemService.deleteItem(id);
        if(!result){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
