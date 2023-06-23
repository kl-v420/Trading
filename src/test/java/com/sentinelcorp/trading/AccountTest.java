package com.sentinelcorp.trading;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountTest {
	private AccountManagement management;

	@Test
	public void test() {
		management = new AccountManagement();
		management.addAccount("JordanJohns11@gmail.com", "John", "JoeMamalol");
		management.addAccount("a@yahoo.com", "Moe", "0987654321");
		Assertions.assertEquals("John", management.getAccountList().get(0).getName());
		Assertions.assertEquals("Moe", management.getAccountList().get(1).getName());
	}

	@Test
	public void readTest() {
		management = new AccountManagement();
		management.readAccounts();
		Assertions.assertEquals("John", management.getAccountList().get(0).getName());
		Assertions.assertEquals("Moe", management.getAccountList().get(1).getName());
	}

	@Test
	public void loginTest() {
		management = new AccountManagement();
		management.readAccounts();
		Assertions.assertTrue(management.login("JordanJohns11@gmail.com", "JoeMamalol"));
	}
}
