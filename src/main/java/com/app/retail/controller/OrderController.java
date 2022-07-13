package com.app.retail.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.retail.messageproducer.CustomMessage;
import com.app.retail.messageproducer.MQConfig;
import com.app.retail.model.Order;
import com.app.retail.service.OrderService;
import com.app.retail.utils.Constants;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private RabbitTemplate template;
	
	@PostMapping(path = "/place", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> place(@RequestBody Order order) {
		orderService.placeOrder(order);
		publishMessage(order);
		return new ResponseEntity<String>("Order Placed", HttpStatus.OK);
	}

	@GetMapping("/getAllOrders")
	public ResponseEntity<List<Order>> getOrders() {
		return new ResponseEntity<List<Order>>(orderService.findAllOrders(), HttpStatus.OK);
	}

	@GetMapping("/getOrderStatus/{id}")
	public ResponseEntity<Optional<Order>> getOrder(@PathVariable int id) {
		return new ResponseEntity<Optional<Order>>(orderService.findOrderById(id), HttpStatus.OK);
	}

	private void publishMessage(Order order) {
		CustomMessage customMessage = new CustomMessage();
		customMessage.setMessageId(UUID.randomUUID().toString());
		customMessage.setMessageDate(new Date());
		customMessage.setMessage(Constants.ORDER_STATUS_PLACED);
		customMessage.setOrder(order);
		template.convertAndSend(MQConfig.TOPIC_EXCHANGE, 
				MQConfig.MESSAGE_ROUTINGKEY,
				customMessage);
	}
	
}
