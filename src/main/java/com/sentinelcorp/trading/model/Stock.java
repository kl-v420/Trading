package com.sentinelcorp.trading.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Stock {
	private double c;
	private double d;
	private double dp;
	private double h;
	private double l;
	private double o;
	private double pc;

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
		String s = null;
		try {
			s = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return s;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public double getDp() {
		return dp;
	}

	public void setDp(double dp) {
		this.dp = dp;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getL() {
		return l;
	}

	public void setL(double l) {
		this.l = l;
	}

	public double getO() {
		return o;
	}

	public void setO(double o) {
		this.o = o;
	}

	public double getPc() {
		return pc;
	}

	public void setPc(double pc) {
		this.pc = pc;
	}

}
