package com.example.demo.form;

import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * ユーザー登録画面 form
 */
@Data
public class SignupForm {
	
	/**ログインID*/
	@Size(min=8, max = 20 )
	private String loginId;
	
	/**パスワード*/
	@Size(min = 8,max = 20)
	private String password;

}
