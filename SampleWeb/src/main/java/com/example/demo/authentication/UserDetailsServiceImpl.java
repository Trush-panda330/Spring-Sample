package com.example.demo.authentication;

import java.time.LocalDateTime;

import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 *  ユーザー情報生成
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	/** ユーザー情報テーブルRepository*/
	private final UserInfoRepository repository;

	/** アカウントロックを行うログインじっぱい回数境界地 */
	private final int LOCKING_BORDER_COUNT = 3;

	/** アカウントロックの継続時間*/
	private final int LOCKING_TIME_ONE_HOUR = 1;

	/**
	 * ユーザー情報生成
	 * 
	 * @param username ログインID
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var userInfo = repository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));

		var accountLockedTime = userInfo.getAccountLockedTime();
		/**
		 * accountLockedTimeがnullでなく、
		 * かつaccountLockedTimeに1時間(LOCKING_TIME_ONE_HOUR)を加えた時間が
		 * 現在の時間(LocalDateTime.now())より後であればtrue。
		 * つまり、アカウントがまだロック状態であることを示す。
		 */
		var isAccountLocked = accountLockedTime != null
				&& accountLockedTime.plusHours(LOCKING_TIME_ONE_HOUR).isAfter(LocalDateTime.now());
		return User.withUsername(userInfo.getLoginId())
				.password(userInfo.getPassword())
				.roles("USER")
				.disabled(userInfo.isDisabled())
				.accountLocked(isAccountLocked)
				// .accountExpired(true) ※アカウント有効期限切れか
				// .credentialsExpired(true) ※パスワード有効期限切れか
				.build();
	}

	/**
	 * 認証失敗時にログイン失敗情報を加算、ロック日時を更新する
	 * 
	 * @param event イベント情報
	 */
	public void handle(AuthenticationFailureBadCredentialsEvent event) {
		var loginId = event.getAuthentication().getName();
		repository.findById(loginId).ifPresent(userInfo -> {
			repository.save(userInfo.incrementLoginFailureCount());

			var isReachFailureCount = userInfo.getLoginFailureCount() == LOCKING_BORDER_COUNT;
			if (isReachFailureCount) {
				repository.save(userInfo.updateAccountLocked());
			}
		});
	}
	
	/**
	 * 認証成功時にログイン失敗回数をリセットする
	 * 
	 * @param event イベント情報
	 */
	public void handle(AuthenticationSuccessEvent event) {
		var loginId = event.getAuthentication().getName();
		repository.findById(loginId).ifPresent( userInfo -> {
			repository.save(userInfo.resetLoginFailureInfo());
		});
	}

}
