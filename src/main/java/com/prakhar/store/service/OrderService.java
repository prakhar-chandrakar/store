package com.prakhar.store.service;

import com.prakhar.store.exception.InvalidOrderException;
import com.prakhar.store.exception.ItemNotFoundException;
import com.prakhar.store.exception.OutOfStockException;
import com.prakhar.store.model.Item;
import com.prakhar.store.model.Order;
import com.prakhar.store.repository.ItemRepository;
import com.prakhar.store.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
                .orElseThrow(()->new ItemNotFoundException("Item with id " + orderItemId + " not found"));

        int itemQuantity = itemDetails.getQuantity();
        if(itemQuantity < orderItemQuantity)
            throw new OutOfStockException("Item out of stock");

        itemDetails.setQuantity(itemQuantity-orderItemQuantity);
        itemRepository.save(itemDetails);

        double expectedPrice = itemDetails.getPrice() * orderItemQuantity;
        if(expectedPrice > totalPricePaid)
            throw new InvalidOrderException("Total price cannot be less than actual price");

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
