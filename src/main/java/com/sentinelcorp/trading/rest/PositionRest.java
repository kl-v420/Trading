package com.sentinelcorp.trading.rest;

import java.util.HashMap;
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
			p = positionsRepo.findAllByAccountId(account.getId());
		}
		return p;
	}

	public Position SortByAccId(Account account) {
		List<Position> positions = positionsRepo.findAllByAccountId(account.getId());
		HashMap<String, Position> postMap = new HashMap<String, Position>();
		for (int i = 0; i < positions.size(); i++) {
			for (int j = 0; j < positions.size(); j++) {
				if (postMap.containsKey(positions.get(i).getSymbol())) {

				}

			}
			if (account != null && positions.get(i).getSymbol() != null) {
				postMap.put(positions.get(i).getSymbol(), positions.get(i));
			} else {

			}
		}
		return null;

	}

}
