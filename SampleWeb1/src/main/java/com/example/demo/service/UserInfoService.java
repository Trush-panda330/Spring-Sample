package com.example.demo.service;

import com.example.demo.dto.UserInfoSearchRequest;
import com.example.demo.entity.UserInfo;

public interface UserInfoService {
	
	/**
	 * 
	 * @param userInfoSearchRequest
	 * @return
	 */
	UserInfo searchByIdUserInfo(UserInfoSearchRequest userInfoSearchRequest);
	

}
