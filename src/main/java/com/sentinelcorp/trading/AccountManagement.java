package com.sentinelcorp.trading;

import java.math.BigDecimal;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sentinelcorp.trading.model.Account;
import com.sentinelcorp.trading.repository.AccountsRepository;

@Controller
public class AccountManagement {
	@Autowired
	private AccountsRepository repo;

	public void addAccount(String email, String name, String password) {
		if (!searchDupe(email)) {
			Account account1 = new Account();
			account1.setEmail(email);
			account1.setName(name);
			account1.setPassword(encrypt(password));
			account1.setAmount(BigDecimal.ZERO);
			repo.save(account1);
		}
	}

	public String encrypt(String s) {
		return DigestUtils.sha256Hex(s);
	}

	public void deposit(String email, BigDecimal depCount) {
		Account account = repo.findByEmailIgnoreCase(email);
		account.setAmount(depCount.add(account.getAmount()));
		repo.save(account);
	}

	public boolean searchDupe(String email) {
		return repo.countByEmailIgnoreCase(email) != 0;
	}

	public boolean login(String email, String password) {
		password = encrypt(password);
		return repo.countByEmailIgnoreCaseAndPassword(email, password) != 0;
	}

	public Account findByEmail(String email) {
		return repo.findByEmailIgnoreCase(email);
	}

	public void deleteById(int id) {
		repo.deleteById(id);
	}
}
