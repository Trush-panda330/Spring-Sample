package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService service;

	@GetMapping("login")
	public String displayLogin(@ModelAttribute LoginForm form) {
		return "login";
	}

	/**
	 * login認証をしてメニュー画面に遷移する
	 * @Model model 
	 * @param LoginForm  form 入力情報
	 * @return 表示画面
	 *  
	 * */
	@PostMapping("login")
	public String login(LoginForm form, Model model) {
		var userInfo = service.searchUserById(form.getLoginId());
		var isCorrectUserAuth = userInfo.isPresent() &&
				form.getPassword().equals(userInfo.get().getPassword());

		// パスワード認証で分岐（いいとき）
		if (isCorrectUserAuth) {
			return "redirect:/menu";
		//あかん時
		} else {
			model.addAttribute("errorMsg", "ログインIDとパスワードの組み合わせが間違っています。");
			return "login";
		}

	}

}
