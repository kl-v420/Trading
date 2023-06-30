package com.sentinelcorp.trading;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sentinelcorp.trading.model.Account;
import com.sentinelcorp.trading.model.Order;

@SpringBootTest
public class TradingApplicationTests {

	@Test
	public void contextLoads() {
		Account jorji = new Account();
		jorji.setEmail("jorjiScam10@gmail.com");
		jorji.setName("Jorji Costava");
		jorji.setPassword("123456789");

		Order apple = new Order();
		apple.setSymbol("AAPL");
		apple.setNumShares(1000000);
		apple.setLimitPrice(new BigDecimal(130));
		apple.setPlaceTime(LocalDateTime.now());

		Order TMobile = new Order();
		TMobile.setSymbol("TMUS");
		TMobile.setNumShares(1000);
		TMobile.setLimitPrice(new BigDecimal(5));
		TMobile.setPlaceTime(LocalDateTime.now());

		System.out.println("------------------------");
		System.out.println(jorji);
	}

}
