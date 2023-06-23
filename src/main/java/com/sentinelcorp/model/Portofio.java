package com.sentinelcorp.model;

import java.util.ArrayList;
import java.util.List;

public class Portofio {
	private List<Order> orderlist;

	public Portofio() {
		this.orderlist = new ArrayList<Order>();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Order o : orderlist) {
			// for (int i = 0; i < orderlist.size(); i++) {
			// sb.append(getOrderList().get(i));
			sb.append(o);
			sb.append(' ');
		}
		return sb.toString();
	}

	public List<Order> getOrderList() {
		return orderlist;
	}

	public void addOrder(Order o) {
		orderlist.add(o);
	}
}
