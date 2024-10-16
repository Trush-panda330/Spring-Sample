package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String name;
	
	private String address;
	
	private String phone;
	
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@Column(name = "update_date")
	private LocalDateTime updateDate;
	
	@Column(name = "delete_date")
	private LocalDateTime deleteDate;
	

	

}
