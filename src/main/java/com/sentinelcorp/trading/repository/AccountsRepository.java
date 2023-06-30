package com.sentinelcorp.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sentinelcorp.trading.model.Account;

public interface AccountsRepository extends JpaRepository<Account, Integer> {
	public Account findByEmailIgnoreCase(String email);

	public long countByEmailIgnoreCase(String email);

	public long countByEmailIgnoreCaseAndPassword(String email, String password);
}