package com.sentinelcorp.trading;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sentinelcorp.trading.model.Order;
import com.sentinelcorp.trading.repository.OrdersRepository;

@SpringBootTest
public class OrderRepositoryTest {
	@Autowired
	private OrdersRepository repository;

	@Test
	public void test() {
		Order order = new Order();
//		order.setDay(true);
//		order.setFillTime(LocalDateTime.now());
//		order.setNumShares(100);
//		order.setLimitPrice(BigDecimal.valueOf(40));
//		order.setSymbol("AAPL");
//		order.setPlaceTime(LocalDateTime.now());
//		order.setPrice(BigDecimal.valueOf(10));
//		repository.save(order);
		System.out.println(repository.findAll());
	}

}
