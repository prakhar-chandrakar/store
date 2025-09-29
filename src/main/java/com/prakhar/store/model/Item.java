package com.prakhar.store.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "items") // @Document annotation to specify the collection name
public class Item {
    @Id
    private String id;
    private String name;
    private Double price;
    private Integer quantity;
}
