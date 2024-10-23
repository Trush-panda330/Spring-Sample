package com.example.demo.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanDefine {

	/*
	 * @Configurationとセットで@Beanを付与したメソッドはDIコンテナに登録され
	 * DI注入(@RequiredArgsConstructorを付けてprivate finalしてインスタンス化)
	 * することができる*/
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	Mapper mapper() {
		return new DozerBeanMapper();
	}

}
