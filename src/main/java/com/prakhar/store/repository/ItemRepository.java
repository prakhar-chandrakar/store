package com.prakhar.store.repository;

import com.prakhar.store.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    Optional<Item> findByName(String name);
}
