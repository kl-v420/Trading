package com.sentinelcorp.trading;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sentinelcorp.model.Account;
import com.sentinelcorp.model.Order;
import com.sentinelcorp.model.Portofio;
import com.sentinelcorp.model.Stock;

@SpringBootTest
public class TradingApplicationTests {

	@Test
	public void contextLoads() {
		Account jorji = new Account();
		jorji.setEmail("jorjiScam10@gmail.com");
		jorji.setName("Jorji Costava");
		jorji.setPassword("123456789");

		Stock gamestop = new Stock();
		gamestop.setName("Gamestop");
		gamestop.setPrice(new BigDecimal(30));
		gamestop.setVolume(100);
		gamestop.setTicker("GME");

		Order apple = new Order();
		apple.setTicker("AAPL");
		apple.setNumShares(1000000);
		apple.setPrice(new BigDecimal(130));
		apple.setDate(LocalDateTime.now());

		Order TMobile = new Order();
		TMobile.setTicker("TMUS");
		TMobile.setNumShares(1000);
		TMobile.setPrice(new BigDecimal(5));
		TMobile.setDate(LocalDateTime.now());

		Portofio ledger = new Portofio();
		ledger.addOrder(apple);
		ledger.addOrder(TMobile);

		System.out.println("------------------------");
		System.out.println(jorji);
		System.out.println("------------------------");
		System.out.println(gamestop);
		System.out.println("------------------------");
		System.out.println(ledger);
	}

}
