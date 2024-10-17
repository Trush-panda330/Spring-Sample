package com.example.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面Controller
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

	/**ログイン画面 service*/
	private final LoginService service;
	
	/** PasswordEncoder*/
	private final  PasswordEncoder passwordEncoder;
	
	/**
	 *  初期表示
	 *  
	 *  @param model 
	 *  @param form 入力情報
	 *  @return 表示画面
	 */
	@GetMapping("/login")
	public String displayLogin(@ModelAttribute LoginForm form) {
		return "login";
	}
	
	/**
	 * ログイン
	 * 
	 * login認証をしてメニュー画面に遷移する
	 * @Model model 
	 * @param LoginForm  form 入力情報
	 * @return 表示画面
	 * */
	@PostMapping("/login")
	public String login(LoginForm form, Model model) {
		var userInfo = service.searchUserById(form.getLoginId());
		//TODO パスワードはハッシュ化したものを使用する
		var isCorrectUserAuth = userInfo.isPresent() &&
				//passwordEncoderのmatches()引数１にformからとってきた生のパス（入力されたパス）
				//引数2にデータベースのuserInfoのハッシュ化されたパス
				passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
		// パスワード認証で分岐trueの時
		if (isCorrectUserAuth) {
			return "redirect:/menu";
		// ユーザーとパスワードの組み合わせが合わない時
		} else {
			//TODO エラーメッセージはプロパティファイルで管理する
			model.addAttribute("errorMsg", "ログインIDとパスワードの組み合わせが間違っています。");
			return "login";
		}

	}

}
