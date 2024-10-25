package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.SignupMessage;
import com.example.demo.constant.UrlConst;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupService;
import com.example.demo.util.AppUtill;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面 Controller
 */
@Controller
@RequiredArgsConstructor
public class SignupController {

	/**ログイン画面 service*/
	private final SignupService service;

	/**メッセージソース */
	private final MessageSource messageSource;

	/**
	 *  初期表示
	 *  
	 *  @param model 
	 *  @param form 入力情報
	 *  @return 表示画面
	 */
	@GetMapping(UrlConst.SIGNUP)
	public String view(@ModelAttribute SignupForm form) {
		return "signup";
	}

	/**
	 * ユーザー登録
	 * 
	 * login認証をしてメニュー画面に遷移する
	 * @Model model 
	 * @param LoginForm  form 入力情報
	 * @param bdResult 入力チェック結果
	 * @return 表示画面
	 * */
	@PostMapping(UrlConst.SIGNUP)
	public void signup(Model model, @Validated SignupForm form, BindingResult result) {
		if(result.hasErrors()) {
//			var message = AppUtill.getMessage(messageSource, MessageConst.FROM_ERROR);
//			model.addAttribute("messeage" ,message);
//			model.addAttribute("isError" , true);
			editGuideMessage(model, MessageConst.FROM_ERROR, true);
			return ;
		}
		var userInfoOpt = service.resistUserInfo(form);
		var signupMessage = judgeMessageKey(userInfoOpt);
//		var messageId = AppUtill.getMessage(messageSource, signupMessage.getMessageId());
//		model.addAttribute("message", messageId);
//		model.addAttribute("isError" ,signupMessage.isError());
		editGuideMessage(model, signupMessage.getMessageId(), signupMessage.isError());
	}

	/**
	 * 画面に表示するガイドメッセージを設定する
	 * 
	 * @param model モデル
	 * @param messageId メッセージID
	 * @param isError エラー有無
	 */
	private void editGuideMessage(Model model, String messageId, boolean isError) {
		var message = AppUtill.getMessage(messageSource, messageId);
		model.addAttribute("message" ,message);
		model.addAttribute("isError", isError);
	}
	
	/**
	 * ユーザ情報登録の結果でメッセージを変える
	 * 
	 * @param userInfoOpt
	 * @return
	 */
	private SignupMessage judgeMessageKey(Optional<UserInfo> userInfoOpt) {
		if (userInfoOpt.isEmpty()) { // userInfoがなかった場合(isEmpty)処理は成功するので
			return SignupMessage.EXISTED_LOGIN_ID;
		} else {
			return SignupMessage.SUCCEED;
		}
	}
}
