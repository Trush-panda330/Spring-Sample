package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupService;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面 Controller
 */
@Controller
@RequiredArgsConstructor
public class SignupController {

	/**ログイン画面 service*/
	private final SignupService service;
	
	/**
	 *  初期表示
	 *  
	 *  @param model 
	 *  @param form 入力情報
	 *  @return 表示画面
	 */
	@GetMapping("/signup")
	public String view(@ModelAttribute SignupForm form) {
		return "signup";
	}
	
	/**
	 * ユーザー登録
	 * 
	 * login認証をしてメニュー画面に遷移する
	 * @Model model 
	 * @param LoginForm  form 入力情報
	 * @return 表示画面
	 * */
	@PostMapping("/signup")
	public void signup(SignupForm form, Model model) {
		var userInfo = service.resistUserInfo(form);
		
//		return "signup";
	}
}
