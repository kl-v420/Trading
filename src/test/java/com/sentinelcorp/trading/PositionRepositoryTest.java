package com.sentinelcorp.trading;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sentinelcorp.trading.repository.AccountsRepository;
import com.sentinelcorp.trading.repository.PositionsRepository;

@SpringBootTest
public class PositionRepositoryTest {
	@Autowired
	private PositionsRepository repository;

	@Autowired
	private AccountsRepository repo;

	@Test
	public void test() {
//		Position position = new Position();
//		position.setAccountId(9);
//		position.setSymbol("AAPL");
//		position.setQuantity(50);
//		position.setPrice(BigDecimal.valueOf(90));
//		repository.save(position);
		System.out.println(repository.findAll());

	}

}
