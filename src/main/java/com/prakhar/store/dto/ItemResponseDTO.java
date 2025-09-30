package com.prakhar.store.dto;

import lombok.Data;

@Data
public class ItemResponseDTO {

    private String id;
    private String name;
    private Double price;
    private Integer quantity;
}
