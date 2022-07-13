package com.app.retail.service;

import java.util.List;
import java.util.Optional;

import com.app.retail.model.Order;

public interface OrderService {
	
	/**
	 * Method to place order
	 * @param com.app.retail.model.Order.class
	 */
	public void placeOrder(Order order);
	
	/**
	 * Method to retrieve all orders
	 * @return List<com.app.retail.model.Order.class>
	 */
	public List<Order> findAllOrders();
	
	/**
	 * Method to retrieve order by ID
	 * @param id
	 * @return Optional<com.app.retail.model.Order.class>
	 */
	public Optional<Order> findOrderById(int id);
	
}
