package com.sentinelcorp.trading.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private BigDecimal commission;
	private String symbol;
	private BigDecimal limitPrice;
	private int numShares;
	private LocalDateTime placeTime;
	private int accountId;
	private BigDecimal price;
	private boolean day;
	private LocalDateTime fillTime;
	private boolean finish;
	private String status;

	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(getAccountId());
		sb.append(' ');
		sb.append(getSymbol());
		sb.append(' ');
		sb.append(getLimitPrice());
		sb.append(' ');
		sb.append(getNumShares());
		sb.append(' ');
		sb.append(getPlaceTime());
		sb.append(' ');
		sb.append(getId());
		sb.append(' ');
		sb.append(isDay());
		sb.append(' ');
		sb.append(getPrice());
		sb.append(' ');
		sb.append(getFillTime());
		sb.append(' ');
		sb.append(getStatus());
		return sb.toString();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isDay() {
		return day;
	}

	public void setDay(boolean day) {
		this.day = day;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(BigDecimal limitPrice) {
		this.limitPrice = limitPrice;
	}

	public int getNumShares() {
		return numShares;
	}

	public void setNumShares(int numShares) {
		this.numShares = numShares;
	}

	public LocalDateTime getPlaceTime() {
		return placeTime;
	}

	public void setPlaceTime(LocalDateTime placeTime) {
		this.placeTime = placeTime;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public LocalDateTime getFillTime() {
		return fillTime;
	}

	public void setFillTime(LocalDateTime fillTime) {
		this.fillTime = fillTime;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
}