package com.sentinelcorp.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sentinelcorp.trading.model.Position;

public interface PositionsRepository extends JpaRepository<Position, Integer> {
	public Position findByAccountIdAndSymbol(int id, String symbol);
}
