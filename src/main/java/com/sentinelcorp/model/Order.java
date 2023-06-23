package com.sentinelcorp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
	private String ticker;
	private BigDecimal price;
	private int numShares;
	private LocalDateTime date;

	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(getTicker());
		sb.append(' ');
		sb.append(getPrice());
		sb.append(' ');
		sb.append(getNumShares());
		sb.append(' ');
		sb.append(getDate());
		return sb.toString();
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getNumShares() {
		return numShares;
	}

	public void setNumShares(int numShares) {
		this.numShares = numShares;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}