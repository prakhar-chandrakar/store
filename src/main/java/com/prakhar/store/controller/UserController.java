package com.prakhar.store.controller;

import com.prakhar.store.dto.ItemRequestDTO;
import com.prakhar.store.dto.OrderRequestDTO;
import com.prakhar.store.dto.OrderResponseDTO;
import com.prakhar.store.model.Order;
import com.prakhar.store.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

//    private final OrderService orderService;
//
//    public UserController(OrderService orderService) {
//        this.orderService=orderService;
//    }

//    @GetMapping("/")
//    public ResponseEntity<List<OrderResponseDTO>> buyItem(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
//        Order order = new Order();
//        order.setItemId(orderRequestDTO.getItemId());
//        order.setUserId(orderRequestDTO.getUserId());
//        order.setQuantity(orderRequestDTO.getQuantity());
//        order.setTotalPrice(orderRequestDTO.getTotalPrice());
//        return ResponseEntity.ok(buyItem(orderService.buyItem(orderRequestDTO.getItemId(), orderRequestDTO.getQuantity(), orderRequestDTO.getUserId(), orderRequestDTO.getTotalPrice() )));
//    }
}
