package com.prakhar.store.controller;

import com.prakhar.store.dto.ItemRequestDTO;
import com.prakhar.store.dto.ItemResponseDTO;
import com.prakhar.store.model.Item;
import com.prakhar.store.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ItemService itemService;

    private ItemResponseDTO convertToResponseDTO(Item item) {
        ItemResponseDTO responseDTO = new ItemResponseDTO();
        responseDTO.setId(item.getId());
        responseDTO.setName(item.getName());
        responseDTO.setPrice(item.getPrice());
        responseDTO.setQuantity(item.getQuantity());
        return responseDTO;
    }

    public AdminController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items")
    public ResponseEntity<ItemResponseDTO> addItem(@Valid @RequestBody ItemRequestDTO requestDTO) {
        // DTO -> Model
        Item item = new Item();
        item.setName(requestDTO.getName());
        item.setPrice(requestDTO.getPrice());
        item.setQuantity(requestDTO.getQuantity());

        Item savedItem = itemService.addItem(item);

        // Model -> DTO
//        ItemResponseDTO response = new ItemResponseDTO();
//        response.setId(savedItem.getId());
//        response.setName(savedItem.getName());
//        response.setPrice(savedItem.getPrice());
//        response.setQuantity(savedItem.getQuantity());
//        return response;
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToResponseDTO(savedItem));
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(@PathVariable String id, @Valid @RequestBody ItemRequestDTO requestDTO) {

        Item item = new Item();
        item.setName(requestDTO.getName());
        item.setPrice(requestDTO.getPrice());
        item.setQuantity(requestDTO.getQuantity());

        Item savedItem = itemService.updateItem(id, item);

        return ResponseEntity
                .created(URI.create("/admin/items/" + savedItem.getId()))
                .body(convertToResponseDTO(savedItem));

    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemResponseDTO> getItem(@PathVariable String id) {
        Item savedItem = itemService.getItemById(id);
        return ResponseEntity.ok(convertToResponseDTO(savedItem));
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemResponseDTO>> getAllItems() {
        List<Item> items = itemService.getAllItems();

        List<ItemResponseDTO> itemResponseDTOS = new ArrayList<>();
        for (Item item : items) {
            itemResponseDTOS.add(convertToResponseDTO(item));
        }
        return ResponseEntity.ok(itemResponseDTOS);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
