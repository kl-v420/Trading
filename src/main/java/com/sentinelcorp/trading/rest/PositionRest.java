package com.sentinelcorp.trading.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sentinelcorp.trading.TokenChecker;
import com.sentinelcorp.trading.model.Account;
import com.sentinelcorp.trading.model.Position;
import com.sentinelcorp.trading.repository.PositionsRepository;

@RestController
public class PositionRest {
	@Autowired
	private PositionsRepository positionsRepo;

	@GetMapping("trading/position/getAll")
	public List<Position> getAll(@RequestParam(name = "token") String token) {
		List<Position> p = null;
		Account account = TokenChecker.verifyToken(token);
		if (account != null) {
			return positionsRepo.findAllByAccountId(account.getId());
		}
		return p;
	}

}
