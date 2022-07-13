package com.app.retail.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.retail.model.Order;
import com.app.retail.repository.OrderRepository;
import com.app.retail.service.OrderService;
import com.app.retail.service.SequenceGeneratorService;
import com.app.retail.utils.Constants;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Override
	public void placeOrder(Order order) {
		int seqNumber = sequenceGeneratorService.getSequenceNumber("order_sequence");
		order.setId(seqNumber);
		order.setStatus(Constants.ORDER_STATUS_PLACED);
		orderRepository.save(order);
	}

	@Override
	public List<Order> findAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Optional<Order> findOrderById(int id) {
		return orderRepository.findById(id);
	}
		
}
