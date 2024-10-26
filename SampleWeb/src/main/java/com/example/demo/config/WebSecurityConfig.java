package com.example.demo.config;

import org.springframework.context.MessageSource;
// 必要なライブラリをインポート
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.constant.UrlConst;

import lombok.RequiredArgsConstructor;

// Webセキュリティを有効にするアノテーション
@EnableWebSecurity
// このクラスが設定クラスであることを示すアノテーション
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	/** パスワードエンコーダー*/
	private final PasswordEncoder passwordEncoder;
	
	/** ユーザー情報取得Ser-vice*/
	private final UserDetailsService userDetailsService;
	
	/** メッセージ取得*/
	private final MessageSource messageSource;

	/** ユーザー名name属性*/
	private final String USERNAME_PARAMETER = "loginId";

	/**
	 * SecurityFilterChainをBean定義
	 * @param http HttpSecurityオブジェクト
	 * @return SecurityFilterChainのインスタンス
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
				.authorizeHttpRequests(
						authorize -> authorize.requestMatchers(UrlConst.NO_AUTENTICATION).permitAll()
								.anyRequest().authenticated())
				.formLogin(
						login -> login.loginPage(UrlConst.LOGIN) // 自作ログイン画面(Controller)を使うための設定
								.usernameParameter(USERNAME_PARAMETER) // ユーザー名のパラメータのname属性
								.defaultSuccessUrl(UrlConst.MENU)); // ログイン成功時のリダイレクトURL

		// HttpSecurityオブジェクトをビルドして返す
		return http.build();
	}
	
	/**
	 * Provider定義
	 * 
	 * @return カスタマイズProvider情報
	 */
	@Bean
	AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		provider.setMessageSource(messageSource);
		return provider;
	}
}
