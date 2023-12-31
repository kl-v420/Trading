package com.sentinelcorp.trading;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sentinelcorp.trading.model.Account;
import com.sentinelcorp.trading.model.Token;

public class TokenChecker {
	private static final Map<String, Token> TOKEN_MAP = new HashMap<String, Token>();

	public static void addToken(String token, Account account, boolean login) {
		Token coin = new Token();
		coin.setLogin(login);
		coin.setAccount(account);
		coin.setToken(token);
		coin.setExpire(LocalDateTime.now().plusMinutes(30));
		TOKEN_MAP.put(token, coin);
	}

	public static void deleteToken() {
		List<String> del = new ArrayList<String>();
		for (String key : TOKEN_MAP.keySet()) {
			Token coin = TOKEN_MAP.get(key);
			if (LocalDateTime.now().isAfter(coin.getExpire())) {
				del.add(key);
			}
		}
		for (int i = 0; i < del.size(); i++) {
			TOKEN_MAP.remove(del.get(i));
		}
	}

	public static Account changePassToken(String key) {
		Token coin = TOKEN_MAP.get(key);
		Account account = null;

		if (coin != null && LocalDateTime.now().isBefore(coin.getExpire()) && !coin.isLogin()) {
			account = coin.getAccount();
		}
		return account;
	}

	public static boolean logout(String key) {
		boolean success = false;
		Token token = TOKEN_MAP.remove(key);
		if (token != null) {
			success = true;
		}
		return success;
	}

	public static Account verifyToken(String key) {
		Token coin = TOKEN_MAP.get(key);
		Account account = null;

		if (coin != null && LocalDateTime.now().isBefore(coin.getExpire()) && coin.isLogin()) {
			account = coin.getAccount();
		}
		return account;
	}
}
