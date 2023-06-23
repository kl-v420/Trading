package com.sentinelcorp.model;

import java.math.BigDecimal;

public class Account {
	private String email;
	private String password;
	private String name;
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getEmail());
		sb.append(' ');
		sb.append(getPassword());
		sb.append(' ');
		sb.append(getName());
		return sb.toString();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
