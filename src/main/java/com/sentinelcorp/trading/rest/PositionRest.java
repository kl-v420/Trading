package com.sentinelcorp.trading.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sentinelcorp.trading.model.Position;
import com.sentinelcorp.trading.repository.PositionsRepository;

@RestController
public class PositionRest {
	@Autowired
	private PositionsRepository positionsRepo;

	@GetMapping("trading/position/getAll")
	public List<Position> getAll() {
		return positionsRepo.findAll();

	}

	@GetMapping("trading/position/getAllByAccountId/(id)")
	public List<Position> getAllByAccountId(@PathVariable int id) {
		return positionsRepo.findAllByAccountId(id);

	}
}
