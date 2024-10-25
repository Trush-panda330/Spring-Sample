package com.example.demo.config;

// 必要なライブラリをインポート
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// Webセキュリティを有効にするアノテーション
@EnableWebSecurity
// このクラスが設定クラスであることを示すアノテーション
@Configuration
public class WebSecurityConfig {

    /**
     * SecurityFilterChainをBean定義
     * @param http HttpSecurityオブジェクト
     * @return SecurityFilterChainのインスタンス
     * @throws Exception
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // フォームログインの設定
        http.formLogin(login -> 
            login.loginPage("/login") // ログインページの指定
                 .usernameParameter("loginId") // ユーザー名のパラメータ名
                 .defaultSuccessUrl("/menu") // ログイン成功時のリダイレクト先
        );

        // HttpSecurityオブジェクトをビルドして返す
        return http.build();
    }
}
