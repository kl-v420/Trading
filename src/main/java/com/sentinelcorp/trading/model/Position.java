package com.sentinelcorp.trading.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "positions")
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String symbol;
	private BigDecimal price;
	private int quantity;
	private int accountId;

	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(getAccountId());
		sb.append(' ');
		sb.append(getSymbol());
		sb.append(' ');
		sb.append(getPrice());
		sb.append(' ');
		sb.append(getQuantity());
		sb.append(' ');
		sb.append(getId());
		return sb.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getQuantity() {
		return quantity;
	}

}