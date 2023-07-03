package com.sentinelcorp.trading.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sentinelcorp.trading.model.Order;
import com.sentinelcorp.trading.repository.OrdersRepository;

@RestController
public class OrderRest {
	@Autowired
	private OrdersRepository ordersRepo;

	@GetMapping("trading/order/getAll")
	public List<Order> getAll() {
		return ordersRepo.findAll();
	}

	@GetMapping("trading/order/getAllByAccountId/(id)")
	public List<Order> getAllByAccountId(@PathVariable int id) {
		return ordersRepo.findAllByAccountId(id);

	}
}
