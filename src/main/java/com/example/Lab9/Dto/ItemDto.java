package com.example.Lab9.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ItemDto {

    private Long id;
    private String name;
    private int price;
    private int quantity;

    private Long countryId;
}
