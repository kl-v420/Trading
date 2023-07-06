package com.sentinelcorp.trading.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sentinelcorp.trading.TokenChecker;
import com.sentinelcorp.trading.model.Account;
import com.sentinelcorp.trading.model.Order;
import com.sentinelcorp.trading.repository.AccountsRepository;
import com.sentinelcorp.trading.repository.OrdersRepository;

@RestController
public class AccountRest {
	private static final String FROM = "admin@hub1616.com";
	private static final String SUBJECT = "o1616 Password Recovery";
	private static final String LINK = "http://hub1616.com/passwordChange.html?token=";

	@Autowired
	private AccountsRepository accountsRepo;
	@Autowired
	private OrdersRepository ordersRepo;
	@Autowired
	private JavaMailSender emailSender;

	@GetMapping("trading/account/getAll")
	public List<Account> getAll() {
		return accountsRepo.findAll();
	}

	@GetMapping("trading/account/createAccount")
	public String createAccount(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {
		if (!searchDupe(email)) {
			Account account1 = new Account();
			account1.setEmail(email);
			account1.setName(name);
			account1.setPassword(encrypt(password));
			account1.setAmount(BigDecimal.ZERO);
			accountsRepo.save(account1);
		}
		return "Account Created";
	}

	@GetMapping("trading/account/login")
	public String login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
		String token = "";
		password = encrypt(password);
		Account account = accountsRepo.findByEmailIgnoreCaseAndPassword(email, password);
		if (account != null) {
			token = encrypt(account.toString() + LocalDateTime.now());
			TokenChecker.addToken(token, account, true);
		}
		return token;
	}

	@GetMapping("trading/account/passwordRecovery")
	public boolean passwordRecovery(@RequestParam(name = "email") String email) {
		boolean success = false;
		Account account = findByEmail(email);
		if (account != null) {
			String token = encrypt(account.getEmail() + LocalDate.now());
			TokenChecker.addToken(token, account, false);
			sendSimpleMessage(account.getEmail(), SUBJECT, LINK + token);
			success = true;
		}
		return success;
	}

	@GetMapping("trading/account/passwordChange")
	public boolean passwordChange(@RequestParam(name = "password") String password,
			@RequestParam(name = "token") String token) {
		boolean success = false;
		Account account = TokenChecker.changePassToken(token);
		if (account != null) {
			account.setPassword(encrypt(password));
			accountsRepo.save(account);
			TokenChecker.deleteToken();
			success = true;
		}
		return success;
	}

	@GetMapping("trading/account/findAmount")
	public String findAmount(@RequestParam(name = "token") String token) {
		String success = "Not found";
		Account account = TokenChecker.verifyToken(token);
		if (account != null) {
			success = account.getAmount().setScale(2).toString();
		}
		return success;
	}

	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(FROM);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

	@GetMapping("trading/account/logout")
	public boolean logout(@RequestParam(name = "token") String token) {
		return TokenChecker.logout(token);
	}

	public boolean searchDupe(String email) {
		return accountsRepo.countByEmailIgnoreCase(email) != 0;
	}

	public String encrypt(String s) {
		return DigestUtils.sha256Hex(s);
	}

	@GetMapping("trading/account/deposit")
	public String deposit(@RequestParam(name = "token") String token, @RequestParam(name = "amount") String amount) {
		String success = "Foundn't";
		Account account = TokenChecker.verifyToken(token);
		if (account != null) {
			BigDecimal bd = new BigDecimal(amount);
			account.setAmount(bd.add(account.getAmount()));
			accountsRepo.save(account);
			success = account.getAmount().setScale(2).toString();

			Order order = new Order();
			order.setAccountId(account.getId());
			order.setCommission(BigDecimal.ZERO);
			order.setDay(true);
			order.setPlaceTime(LocalDateTime.now());
			order.setFillTime(LocalDateTime.now());
			order.setFinish(true);
			order.setLimitPrice(null);
			order.setNumShares(0);
			order.setPrice(bd);
			order.setStatus("Deposited");
			order.setSymbol("CASH");
			ordersRepo.save(order);
		}
		return success;
	}

	public Account findByEmail(String email) {
		return accountsRepo.findByEmailIgnoreCase(email);
	}

}
