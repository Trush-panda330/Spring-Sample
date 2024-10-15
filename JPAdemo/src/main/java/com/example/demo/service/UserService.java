package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	// UserRepositoryのインスタンスを自動的に注入
	@Autowired
	UserRepository userRepository;
	
	// 全てのUserエンティティを取得するメソッド
	public List<User> searchAll() {
		// UserRepositoryのfindAllメソッドを呼び出して全てのUserを取得
		return userRepository.findAll();
	}
}
