package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("user/list")
	public String displayUserList(Model model) {
		List<User> userList = userService.searchAll();
		model.addAttribute("userList", userList);
		return "user/list";
		
	}
}
