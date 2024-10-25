package com.example.demo.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面 
 */
@Service
@RequiredArgsConstructor
public class SignupService {
	
	/** ユーザー情報テーブルDAO*/
	private final UserInfoRepository repository;
	
	
	/** PasswordEncoder*/
	private final  PasswordEncoder passwordEncoder;
	
	
	/** Dozer Mapper
	 * DozerBeanMapper.javaはMapperインターフェースの実装クラスなので
	 * Mapperインターフェースの型でDIを行う
	 * */
	private final Mapper mapper;
	
	
	
	/**
	 * ユーザー情報テーブル 新規登録
	 * @param form 入力情報
	 * @return 登録情報（ユーザー情報Entity)、既にユーザーIDで登録情報がある場合はEmpty
	 * 
	 */
	public Optional <UserInfo> resistUserInfo(SignupForm form) {
		//使う際に型が分かりにくいので変数名にOptとつけている
		 var uesrInfoExistedOpt = repository.findById(form.getLoginId()); 
		 if(uesrInfoExistedOpt.isPresent()) {//isPresentなので存在したらtrueが返る
			 return Optional.empty();
		 }
			/* この処理はDozerMapperを使って実装
			 * DozerMapperにより
			 * インスタンスを生成し
			 * フィールドをコピーする処理を
			 * DozerMapperに任せられる
			 * 
			 *  var userInfo = new UserInfo();
			 *  userInfo.setLoginId(form.getLoginId());
			 *  userInfo.setPassword(form.getPassword());*/
		
		/* 第一引数の(form)情報を
		 * 第二引数(UserInfo.class)コピーしてnewしたインスタンスを返す*/
		var userInfo = mapper.map(form,UserInfo.class);
		
		var encodedPassword = passwordEncoder.encode(form.getPassword());
		userInfo.setPassword(encodedPassword);
		
		return Optional.of(repository.save(userInfo));
	}

}
