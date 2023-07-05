package com.sentinelcorp.trading;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sentinelcorp.trading.model.Account;
import com.sentinelcorp.trading.rest.AccountRest;

@SpringBootTest
public class AccountTest {
	@Autowired
	private AccountRest management;

	private static final String EMAIL = "JordanJohns11@gmail.com";
	private static final String PASSWORD = "JoeMamalol";
	private static final String NAME = "John";
	private static final BigDecimal DEPOSIT = BigDecimal.valueOf(10000);

	@Test
	public void addTest() {
		management.createAccount(NAME, EMAIL, PASSWORD);
		Account actual = management.findByEmail(EMAIL);

		Assertions.assertEquals(EMAIL, actual.getEmail());
		Assertions.assertEquals(management.encrypt(PASSWORD), actual.getPassword());
		Assertions.assertEquals(NAME, actual.getName());
		Assertions.assertTrue(actual.getId() > 0);
	}

	@Test
	public void afterDupeTest() {
		Assertions.assertTrue(management.searchDupe(EMAIL));
	}

	@Test
	public void loginTest() {
		Assertions.assertTrue(!management.login(EMAIL, PASSWORD).isEmpty());
	}

	@Test
	public void depositTest() {
		Account expected = management.findByEmail(EMAIL);
		BigDecimal expectedAmount = expected.getAmount().add(DEPOSIT);

		management.deposit(EMAIL, DEPOSIT);
		Account actual = management.findByEmail(EMAIL);
		Assertions.assertEquals(expectedAmount, actual.getAmount());
	}
}
