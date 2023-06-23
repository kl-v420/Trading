package com.sentinelcorp.model;

import java.math.BigDecimal;

public class Stock {
	private String name;
	private String ticker;
	private BigDecimal price;
	private int volume;

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(getName());
		sb.append(' ');
		sb.append(getTicker());
		sb.append(' ');
		sb.append(getPrice());
		sb.append(' ');
		sb.append(getVolume());
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
}
