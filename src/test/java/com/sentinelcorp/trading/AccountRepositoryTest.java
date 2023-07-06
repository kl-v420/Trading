package com.sentinelcorp.trading;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sentinelcorp.trading.model.Account;
import com.sentinelcorp.trading.repository.AccountsRepository;

@SpringBootTest
public class AccountRepositoryTest {
	@Autowired
	private AccountsRepository repo;

	@Test
	public void test() {
		Account jorji = new Account();
		// jorji.setEmail("jorjiScam10@gmail.com");
		// jorji.setName("Jorji Costava");
		// jorji.setPassword("123456789");
		// jorji.setAmount(BigDecimal.valueOf(100000000));
		// repository.save(jorji);
		// System.out.println(repository.count());
		String email = "jorjiScam10@gmail";
		System.out.println(repo.findAll());
		Account account = repo.findByEmailIgnoreCase(email);
		String password = "123456789";
		for (Account a : repo.findAll()) {
			System.out.println(a.getEmail());
			System.out.println(a.getId());
		}

	}
}
