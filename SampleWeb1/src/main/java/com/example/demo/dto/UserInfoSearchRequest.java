package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserInfoSearchRequest implements Serializable {
	private String loginId;
}
