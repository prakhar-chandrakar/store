package com.prakhar.store.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDTO {

    @NotBlank(message = "UserId cannot be blank")
    private String userId;

    @NotBlank(message = "ItemId cannot be blank")
    private String itemId;

    @NotNull(message = "TotalPrice cannot be null")
    @Min(value = 0, message = "TotalPrice cannot be negative")
    private Double totalPrice;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be atleast 1")
    private Integer quantity;

}
