package com.sentinelcorp.trading;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sentinelcorp.trading.repository.PositionsRepository;

@SpringBootTest
public class PositionRepositoryTest {
	@Autowired
	private PositionsRepository repository;

	@Test
	public void test() {
//		Position position = new Position();
//		position.setAccountId(1);
//		position.setSymbol("AAPL");
//		position.setQuantity(200);
//		position.setPrice(BigDecimal.valueOf(60));
//		repository.save(position);
		System.out.println(repository.findAll());

	}

}
