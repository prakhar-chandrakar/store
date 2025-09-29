package com.prakhar.store.service;

import com.prakhar.store.model.Item;
import com.prakhar.store.model.Order;
import com.prakhar.store.repository.ItemRepository;
import com.prakhar.store.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    public final ItemRepository itemRepository;
    public final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public void buyItem(String orderItemId, Integer orderItemQuantity,String userId, double totalPricePaid) {
        Item itemDetails = itemRepository.findById(orderItemId)
                .orElseThrow(()->new RuntimeException("Item not found"));

        int itemQuantity = itemDetails.getQuantity();
        if(itemQuantity < orderItemQuantity)
            throw new RuntimeException("Item out of stock");

        itemDetails.setQuantity(itemQuantity-orderItemQuantity);
        itemRepository.save(itemDetails);

        double expectedPrice = itemDetails.getPrice() * orderItemQuantity;
        if(expectedPrice > totalPricePaid)
            throw new RuntimeException("SCAM happened");

        Order orderDetails = new Order();
        orderDetails.setUserId(userId);
        orderDetails.setItemId(orderItemId);
        orderDetails.setTotalPrice(totalPricePaid);
        orderDetails.setQuantity(orderItemQuantity);
        orderDetails.setCreatedAt(new Date());

        orderRepository.save(orderDetails);
    }

    public List<Order> getOrdersByUser(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
