package com.sentinelcorp.trading.rest;

import java.math.BigDecimal;
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
					p.get(i).getPrice();
					positions.get(i);

					position.setQuantity(position.getQuantity() + postMap.get(positions.get(i)).getQuantity());
					BigDecimal price = postMap.get(positions.get(i)).getPrice();
					position.setPrice(position.getPrice().add(price).divide(/* something here */ ));
				} else {
					postMap.put(positions.get(i).getSymbol(), positions.get(i));
				}
			}
			for (int i = 0; i < postMap.size(); i++)
				p.add(postMap.get(/* something here */ ));
		}
		return p;
	}

}
