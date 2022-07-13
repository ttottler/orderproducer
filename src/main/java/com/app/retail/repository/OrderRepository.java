package com.app.retail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.retail.model.Order;

public interface OrderRepository extends MongoRepository<Order, Integer> {

}
