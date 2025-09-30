package com.prakhar.store.service;

import com.prakhar.store.model.Item;
import com.prakhar.store.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(String id) {
//        if(itemRepository.existsById(id))
//            return itemRepository.findById(id);
//        throw new RuntimeException("Item not found");
        return itemRepository.findById(id).orElseThrow(()-> new RuntimeException("Item not found"));
    }

    public Item updateItem(String id, Item newItem ) {
        Item oldItem = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        oldItem.setName(newItem.getName());
        oldItem.setPrice(newItem.getPrice());
        oldItem.setQuantity(newItem.getQuantity());
        return itemRepository.save(oldItem);
    }

    public void deleteItem(String id) {
        if(itemRepository.existsById(id))

            itemRepository.deleteById(id);
        else throw new RuntimeException("Item not found");
    }
}
