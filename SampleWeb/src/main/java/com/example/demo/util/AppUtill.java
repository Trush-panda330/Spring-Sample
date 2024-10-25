package com.example.demo.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * アプリケーション共通クラス
 */
public class AppUtill {
	/*MessageSource getMessage(引数が３つ）
	 * 第1引数：MessageSourceを使う
	 * 第2引数：
	 * 第3引数：
	 * */
	/**
	 * 
	 * @param messageSource メッセージソース
	 * @param key メッセージキー
	 * @param params 置換文字群
	 * @return メッセージ
	 */
	public static String getMessage(MessageSource messageSource, String key, Object... params) {
		return messageSource.getMessage(key, params,Locale.JAPAN);
	}
}
