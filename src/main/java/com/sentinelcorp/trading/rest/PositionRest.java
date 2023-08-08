package com.sentinelcorp.trading.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
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
		HashMap<String, Position> postMap = new HashMap<String, Position>();
		if (account != null) {
			List<Position> positions = positionsRepo.findAllByAccountId(account.getId());
			for (int i = 0; i < positions.size(); i++) {
				if (postMap.containsKey(positions.get(i).getSymbol())) {
					Position position = postMap.get(positions.get(i).getSymbol());
					position.setQuantity(position.getQuantity() + positions.get(i).getQuantity());
					BigDecimal p1 = BigDecimal.valueOf(positions.get(i).getQuantity());
					BigDecimal p2 = BigDecimal.valueOf(position.getQuantity());
					BigDecimal stock = BigDecimal.valueOf(position.getQuantity() + positions.get(i).getQuantity());
					BigDecimal price = positions.get(i).getPrice().multiply(p1);
					BigDecimal amounted = position.getPrice().multiply(p2);
					position.setPrice(amounted.add(price).divide(stock));
				} else {
					postMap.put(postMap.get(i).getSymbol(), positions.get(i));
				}
			}
		}
		p = new ArrayList<Position>(postMap.values());
		return p;
	}
}
