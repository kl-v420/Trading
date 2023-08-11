package com.sentinelcorp.trading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sentinelcorp.trading.model.Position;

public interface PositionsRepository extends JpaRepository<Position, Integer> {
	public Position findByAccountIdAndSymbol(int id, String symbol);

	public List<Position> findAllByAccountId(int id);

	public List<Position> findAllByAccountIdAndSymbol(int id, String symbol);

}
