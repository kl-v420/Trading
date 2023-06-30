package com.sentinelcorp.trading;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sentinelcorp.trading.model.Account;
import com.sentinelcorp.trading.model.Order;
import com.sentinelcorp.trading.model.Stock;
import com.sentinelcorp.trading.repository.AccountsRepository;
import com.sentinelcorp.trading.repository.OrdersRepository;
import com.sentinelcorp.trading.repository.PositionsRepository;

@SpringBootTest
public class TradingTest {
	private static final String EMAIL = "JordanJohns11@gmail.com";
	private static final String SYMBOL = "GGG";
	private static final int NUM_SHARES = 2;
	@Autowired
	private TradeManagement management;

	@Autowired
	private AccountsRepository accRepo;

	@Autowired
	private OrdersRepository orderRepo;

	@Autowired
	private PositionsRepository posRepo;

	@Test
	public void testMarketOrder() {
		Account account = accRepo.findByEmailIgnoreCase(EMAIL);
		System.out.println(orderRepo.findAll());
		management.marketOrder(account, SYMBOL, NUM_SHARES, true);
		List<Order> actual = orderRepo.findAllByAccountIdAndFinishFalse(account.getId());
		System.out.println(actual);
		Assertions.assertEquals(SYMBOL, actual.get(0).getSymbol());
		Assertions.assertEquals(NUM_SHARES, actual.get(0).getNumShares());
	}

	@Test
	public void testNoMoney() {
//		management = new TradeManagement();
//		
//
//		management.fill();
//		Assertions.assertTrue(management.getPostionList().isEmpty());
	}

	@Test
	public void test4time() {
//		management = new TradeManagement();
//		Account jorji = new Account();
//		jorji.setEmail("jorjiScam10@gmail.com");
//		jorji.setName("Jorji Costava");
//		jorji.setPassword("123456789");
//		jorji.setAmount(BigDecimal.valueOf(1));
//		management.marketOrder(jorji, "GGG", 100, true);
//		for (Order order : management.getOrderList()) {
//			order.setPlaceTime(LocalDateTime.of(2023, 6, 24, 10, 30));
//		}
//		management.fill();
//		Assertions.assertTrue(management.getPostionList().isEmpty());
	}

	@Test
	public void test5time() {
//		management = new TradeManagement();
//		Account jorji = new Account();
//		jorji.setEmail("jorjiScam10@gmail.com");
//		jorji.setName("Jorji Costava");
//		jorji.setPassword("123456789");
//		jorji.setAmount(BigDecimal.valueOf(1));
//		management.marketOrder(jorji, "GGG", 100, true);
//		for (Order order : management.getOrderList()) {
//			order.setPlaceTime(LocalDateTime.of(2023, 6, 27, 1, 30));
//		}
//		management.fill();
//		Assertions.assertTrue(management.getPostionList().isEmpty());
	}

	@Test
	public void test6() {
		Stock gme = management.getStock("GME");
		Assertions.assertTrue(gme.getC() > 0);
	}

}
