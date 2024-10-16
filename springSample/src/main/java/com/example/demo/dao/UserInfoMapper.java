package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.UserAddRequest;
import com.example.demo.dto.UserSearchRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.UserInfo;

@Mapper
public interface UserInfoMapper {
	
	/**
	 * ユーザー情報全件検索
	 * @param user 検索用リクエストデータ
	 * @return 検索結果
	 * */
	List<UserInfo>  findAll();
	
	/**
	 *ユーザー情報主キー検索
	 *@param id 主キー
	 *@return 検索結果
	 * */
	UserInfo findById(Long id);
	
	/**
	 * ユーザー情報検索
	 *  @param userSearchRequest
	 *  @return 検索結果
	 * */
	List<UserInfo> search(UserSearchRequest user);
	
	/**
	 * ユーザー情報登録
	 * @param userCreateRequest 登録用リクエストデータ
	 * */
	void save(UserAddRequest userAddRequest);
	
	/**
	 * ユーザー情報更新
	 * @param userUpdateRequest
	 * */
	void update(UserUpdateRequest userUpdateRequest);
	
	
	/**
	 * ユーザー情報論理削除
	 *  @param
	 * */
	void delete(Long id);
	

	
	
}
