package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleWeb1Application {

	public static void main(String[] args) {
		SpringApplication.run(SampleWeb1Application.class, args);
		}

}

/*以下DBの接続やMapperの定義がうまくいっているか
 * 確認のために実行した時の内容*/
//package com.example.demo;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
//
//import com.example.demo.dto.UserInfoSearchRequest;
//import com.example.demo.entity.UserInfo;
//import com.example.demo.service.UserInfoService;
//
//import lombok.RequiredArgsConstructor;
//
//@SpringBootApplication
//@RequiredArgsConstructor
//public class SampleWeb1Application {
//
//	public static void main(String[] args) {
//	    SpringApplication app = new SpringApplication(SampleWeb1Application.class);
//	    ApplicationContext context = app.run(args);
//	    
//	    // UserInfoSearchRequestの準備
//	    UserInfoSearchRequest req = new UserInfoSearchRequest();
//	    req.setLoginId("rootuser"); // テストするlogin_idを設定
//
//	    // UserInfoServiceを取得
//	    UserInfoService service = context.getBean(UserInfoService.class);
//	    UserInfo user = service.searchByIdUserInfo(req);
//	    
//	    System.out.println(user);
//	}
//}

