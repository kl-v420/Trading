package com.sentinelcorp.trading;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.sentinelcorp.model.Account;

public class AccountManagement {
	private List<Account> accountList;
	private FileDataBase db;

	public AccountManagement() {
		this.accountList = new ArrayList<Account>();
		this.db = new FileDataBase();
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void readAccounts() {
		accountList = db.readAccounts();
	}

	public void addAccount(String email, String name, String password) {
		String sha256hex = DigestUtils.sha256Hex(password);
		Account account1 = new Account();
		account1.setEmail(email);
		account1.setName(name);
		account1.setPassword(sha256hex);
		accountList.add(account1);
		db.saveAccounts(accountList);

	}

	public void deposit(String email, BigDecimal depCount) {
		int i = 0;
		boolean found = false;
		while (!found && i < accountList.size()) {
			Account account = accountList.get(i);
			if (email.equals(account.getEmail())) {
				account.setAmount(depCount.add(account.getAmount()));
				db.saveAccounts(accountList);
				found = true;
			}
			i++;
		}
	}

	public boolean login(String email, String password) {
		boolean found = false;
		int i = 0;

		while (!found && i < accountList.size()) {
			String sha256hex = DigestUtils.sha256Hex(password);
			if (email.equalsIgnoreCase(accountList.get(i).getEmail())
					&& sha256hex.equals(accountList.get(i).getPassword())) {
				found = true;
			}
			i++;
		}
		return found;
	}
}
