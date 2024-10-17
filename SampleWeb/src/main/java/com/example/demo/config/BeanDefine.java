package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanDefine {

	/*
	 * @Configurationとセットで@Beanを付与したメソッドはDIコンテナに登録され
	 * DI注入(@RequiredArgsConstructor配下でprivate finalしてインスタンス化)
	 * することができる*/
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
