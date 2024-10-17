package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="user_info")
public class UserInfo {
	
	@Id
	@Column(name="login_id")
	private String roginId;
	
	private String password;

}
