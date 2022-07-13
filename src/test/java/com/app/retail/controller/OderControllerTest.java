package com.app.retail.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.retail.model.Order;
import com.app.retail.service.OrderService;

@SpringBootTest
public class OderControllerTest {

	@Mock
	private OrderService orderService;
	
	@Mock
	private RabbitTemplate rabbitTemplate;
	
	@InjectMocks
	private OrderController orderController;
	
	@BeforeEach
	private void setMocks() {
		
	}
	
	@Test
	public void testPlaceOrder() {
		Order order = new Order();
		orderController.place(order);
		verify(orderService, times(1)).placeOrder(order);
	}
	
	@Test
	public void testGetOrders() {
		orderController.getOrders();
		verify(orderService, times(1)).findAllOrders();
	}
	
	@Test
	public void testGetOrder( ) {
		orderController.getOrder(1);
		verify(orderService, times(1)).findOrderById(1);
	}
	
}
