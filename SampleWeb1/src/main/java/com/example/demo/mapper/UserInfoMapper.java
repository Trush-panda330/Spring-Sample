package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.UserInfoSearchRequest;
import com.example.demo.entity.UserInfo;

@Mapper
public interface UserInfoMapper {
	
	UserInfo getUserInfoById(UserInfoSearchRequest userInfoId);

}
