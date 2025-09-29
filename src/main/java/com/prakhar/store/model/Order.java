package com.prakhar.store.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private String userId;
    private String itemId;
    private Double totalPrice;
    private Integer quantity;
    private Date createdAt;
}
