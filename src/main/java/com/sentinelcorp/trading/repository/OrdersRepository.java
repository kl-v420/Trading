package com.sentinelcorp.trading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sentinelcorp.trading.model.Order;

public interface OrdersRepository extends JpaRepository<Order, Integer> {
	public List<Order> findAllByAccountIdAndFinishFalse(int id);

	public List<Order> findAllByFinishFalse();

	public List<Order> findAllByAccountId(int id);
}
