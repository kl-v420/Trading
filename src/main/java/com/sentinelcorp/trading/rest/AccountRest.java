package com.sentinelcorp.trading.rest;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sentinelcorp.trading.model.Account;
import com.sentinelcorp.trading.repository.AccountsRepository;

@RestController
public class AccountRest {
	@Autowired
	private AccountsRepository accountsRepo;

	@GetMapping("trading/account/getAll")
	public List<Account> getAll() {
		return accountsRepo.findAll();
	}

	@GetMapping("trading/account/createAccount")
	public String createAccount(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {
		if (!searchDupe(email)) {
			Account account1 = new Account();
			account1.setEmail(email);
			account1.setName(name);
			account1.setPassword(encrypt(password));
			account1.setAmount(BigDecimal.ZERO);
			accountsRepo.save(account1);
		}
		return "Account Created";
	}

	public boolean searchDupe(String email) {
		return accountsRepo.countByEmailIgnoreCase(email) != 0;
	}

	public String encrypt(String s) {
		return DigestUtils.sha256Hex(s);
	}

}
