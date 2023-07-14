package com.sentinelcorp.trading.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sentinelcorp.trading.TokenChecker;
import com.sentinelcorp.trading.model.Account;
import com.sentinelcorp.trading.model.Order;
import com.sentinelcorp.trading.repository.OrdersRepository;

@RestController
public class OrderRest {
	@Autowired
	private OrdersRepository ordersRepo;

	@GetMapping("trading/order/getAll")
	public List<Order> getAll(@RequestParam(name = "token") String token) {
		Account account = TokenChecker.verifyToken(token);
		List<Order> c = null;
		if (account != null) {
			c =  ordersRepo.findAllByAccountId(account.getId());
		}
		return c;
	}

}
