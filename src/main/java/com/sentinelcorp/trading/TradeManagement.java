package com.sentinelcorp.trading;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.sentinelcorp.trading.model.Account;
import com.sentinelcorp.trading.model.Order;
import com.sentinelcorp.trading.model.Position;
import com.sentinelcorp.trading.model.Stock;
import com.sentinelcorp.trading.repository.AccountsRepository;
import com.sentinelcorp.trading.repository.OrdersRepository;
import com.sentinelcorp.trading.repository.PositionsRepository;

@Controller
public class TradeManagement {
	private final static LocalTime NINE_THIRTY = LocalTime.of(9, 30);
	private final static LocalTime FOUR_PM = LocalTime.of(16, 0);
	private final static int MAX_API_CALLS = 60;
	private final static String PENDING = "Pending...";
	private final static String DONE = "Done!";
	private final static String NO_FUNDS = "Insufficient funds.";
	private final static String EXPIRED = "Order has Expired";
	private final static BigDecimal COMMISSION = BigDecimal.valueOf(4.95);
	private int apiCalls;
	private LocalDateTime lastReset;

	@Autowired
	private AccountsRepository accRepo;

	@Autowired
	private OrdersRepository orderRepo;

	@Autowired
	private PositionsRepository posRepo;

	public TradeManagement() {
		this.lastReset = LocalDateTime.now();
		this.apiCalls = 0;
	}

	public void marketOrder(Account account, String symbol, int numShares, boolean day) {
		Order sixtysix = new Order();
		sixtysix.setStatus(PENDING);
		sixtysix.setFinish(false);
		sixtysix.setDay(day);
		sixtysix.setNumShares(numShares);
		sixtysix.setSymbol(symbol);
		sixtysix.setPlaceTime(LocalDateTime.now());
		sixtysix.setAccountId(account.getId());
		orderRepo.save(sixtysix);
	}

	public void limitOrder(Account account, String symbol, int numShares, boolean day, BigDecimal limit) {
		Order sixtysix = new Order();
		sixtysix.setStatus(PENDING);
		sixtysix.setFinish(false);
		sixtysix.setDay(day);
		sixtysix.setNumShares(numShares);
		sixtysix.setSymbol(symbol);
		sixtysix.setPlaceTime(LocalDateTime.now());
		sixtysix.setAccountId(account.getId());
		sixtysix.setLimitPrice(limit);
		orderRepo.save(sixtysix);
	}

	@Scheduled(fixedRate = 15000)
	public void checkOrder() {
		if (isWeekday() && isMarketHours()) {
			List<Order> orderList = orderRepo.findAllByFinishFalse();
			for (int i = 0; i < orderList.size(); i++) {
				Order order = orderList.get(i);
				BigDecimal price = BigDecimal.valueOf(getStock(order.getSymbol()).getC());
				BigDecimal cost = price.multiply(BigDecimal.valueOf(order.getNumShares()));
				cost = cost.add(COMMISSION);
				Account account = accRepo.findById(order.getAccountId()).get();

				if (!isNotBroke(account.getAmount(), cost)) {
					order.setFinish(true);
					order.setStatus(NO_FUNDS);
					orderRepo.save(order);
				} else {
					if (order.getLimitPrice() != null) {
						LocalDateTime close = LocalDateTime.of(order.getPlaceTime().toLocalDate(), FOUR_PM);
						if (order.isDay() && order.getPlaceTime().isAfter(close)) {
							order.setFinish(true);
							order.setStatus(EXPIRED);
							orderRepo.save(order);
						} else if (order.getLimitPrice().compareTo(price) <= 0) {
							fill(order, price, account, cost);
						}
					} else {
						// market order
						fill(order, price, account, cost);
					}
				}
			}
		}
	}

	public void fill(Order order, BigDecimal price, Account account, BigDecimal cost) {
		Position position = posRepo.findByAccountIdAndSymbol(order.getAccountId(), order.getSymbol());
		if (position != null) {
			BigDecimal newQuan = BigDecimal.valueOf(order.getNumShares());
			BigDecimal orderTotal = price.multiply(newQuan);
			BigDecimal newQuan2 = BigDecimal.valueOf(position.getQuantity());
			BigDecimal positionTotal = position.getPrice().multiply(newQuan2);
			BigDecimal total = orderTotal.add(positionTotal);
			BigDecimal avgPrice = total.divide(newQuan.add(newQuan2), 4, RoundingMode.HALF_UP);
			position.setPrice(avgPrice);
			position.setQuantity(position.getQuantity() + order.getNumShares());
			price = BigDecimal.valueOf(getStock(order.getSymbol()).getC());
		} else {
			position = new Position();
			position.setPrice(price);
			position.setQuantity(order.getNumShares());
			position.setSymbol(order.getSymbol());
			position.setAccountId(account.getId());
		}

		posRepo.save(position);

		order.setFillTime(LocalDateTime.now());
		order.setCommission(COMMISSION);
		order.setPrice(price);
		order.setFinish(true);
		order.setStatus(DONE);
		orderRepo.save(order);

		account.setAmount(account.getAmount().subtract(cost));
		accRepo.save(account);
	}

	private boolean isWeekday() {
		DayOfWeek day = LocalDate.now().getDayOfWeek();
		return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
	}

	private boolean isMarketHours() {
		LocalTime time = LocalTime.now();
		return time.isAfter(NINE_THIRTY) && time.isBefore(FOUR_PM);
	}

	private boolean isNotBroke(BigDecimal amount, BigDecimal cost) {
		return amount.compareTo(cost) == 1;
	}

	// cidheh1r01qvscdan400cidheh1r01qvscdan40g
	// "https://finnhub.io/api/v1/quote?symbol=AAPL&token=cidheh1r01qvscdan400cidheh1r01qvscdan40g"
	public Stock getStock(String symbol) {
		Stock stock = null;
		if (!timeout()) {
			RestTemplate temp = new RestTemplate();
			StringBuilder url = new StringBuilder();
			url.append("https://finnhub.io/api/v1/quote?symbol=");
			url.append(symbol);
			url.append("&token=cidheh1r01qvscdan400cidheh1r01qvscdan40g");
			stock = temp.getForObject(url.toString(), Stock.class);
			apiCalls++;
		}
		return stock;
	}

	public boolean timeout() {
		boolean timeout = false;

		if (LocalDateTime.now().isAfter(lastReset.plusMinutes(1))) {
			apiCalls = 0;
			lastReset = LocalDateTime.now();
		}

		if (apiCalls >= MAX_API_CALLS) {
			timeout = true;
		}

		return timeout;
	}
}