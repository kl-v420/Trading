package com.sentinelcorp.trading.rest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sentinelcorp.trading.TokenChecker;
import com.sentinelcorp.trading.model.Account;
import com.sentinelcorp.trading.model.Order;
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

	public Position getStockByAccId(BigDecimal price, Account account, Order order) {
		Position position = new Position();
		BigDecimal newQuan = BigDecimal.valueOf(order.getNumShares());
		BigDecimal orderTotal = price.multiply(newQuan);
		BigDecimal newQuan2 = BigDecimal.valueOf(position.getQuantity());
		BigDecimal positionTotal = position.getPrice().multiply(newQuan2);
		BigDecimal total = orderTotal.add(positionTotal);
		BigDecimal avgPrice = total.divide(newQuan.add(newQuan2), 4, RoundingMode.HALF_UP);
		position.setPrice(avgPrice);
		position.setQuantity(position.getQuantity() + order.getNumShares());

	}

}
