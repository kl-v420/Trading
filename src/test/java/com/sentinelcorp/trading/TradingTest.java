package com.sentinelcorp.trading;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sentinelcorp.trading.model.Account;
import com.sentinelcorp.trading.model.Order;
import com.sentinelcorp.trading.model.Stock;
import com.sentinelcorp.trading.repository.AccountsRepository;
import com.sentinelcorp.trading.repository.OrdersRepository;
import com.sentinelcorp.trading.rest.TradeRest;

@SpringBootTest
public class TradingTest {
    private static final String EMAIL = "JordanJohns11@gmail.com";
    private static final String SYMBOL = "GGG";
    private static final int NUM_SHARES = 2;
    @Autowired
    private TradeRest management;

    @Autowired
    private AccountsRepository accRepo;

    @Autowired
    private OrdersRepository orderRepo;

    @Test
    public void testMarketOrder() {
	Account account = accRepo.findByEmailIgnoreCase(EMAIL);
	System.out.println(orderRepo.findAll());
	List<Order> actual = orderRepo.findAllByAccountIdAndFinishFalse(account.getId());
	System.out.println(actual);
	Assertions.assertEquals(SYMBOL, actual.get(0).getSymbol());
	Assertions.assertEquals(NUM_SHARES, actual.get(0).getNumShares());
    }

    @Test
    public void test6() {
	TokenChecker.addToken(EMAIL, null, false);
	Stock gme = management.getStock(EMAIL, "GME");
	Assertions.assertTrue(gme.getC() > 0);
    }

}
