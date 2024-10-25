package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserInfoSearchRequest;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.UserInfoMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
	
	private final UserInfoMapper mapper;

	@Override
	public UserInfo searchByIdUserInfo(UserInfoSearchRequest userInfoSearchRequest) {
		return mapper.getUserInfoById(userInfoSearchRequest);
	}

}
