package com.app.retail.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "userdetails")
@Getter
@Setter
public class User {
	
	@Transient
	private static final String SEQUENCE_NAME = "user_sequence";
	
	@Id
	private int id;
	private String userName;
	private String password;
	private String email;

}
