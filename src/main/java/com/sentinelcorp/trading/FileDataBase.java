package com.sentinelcorp.trading;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sentinelcorp.model.Account;

public class FileDataBase {
	private ObjectMapper objectMapper;

	public FileDataBase() {
		this.objectMapper = new ObjectMapper();
	}

	public void saveAccounts(List<Account> accountList) {
		try {
			objectMapper.writeValue(new File("src/main/resources/accounts.json"), accountList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Account> readAccounts() {
		List<Account> account = null;
		try {
			account = objectMapper.readValue(new File("src/main/resources/accounts.json"),
					new TypeReference<List<Account>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return account;
	}

}
