package com.app.retail.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order")
public class Order {
	
	@Transient
	private static final String SEQUENCE_NAME = "order_sequence";
	
	@Id
	private int id;
	
	private String status;
	
	private List<OrderItem> orderItems;


}
