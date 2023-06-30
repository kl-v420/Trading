package com.sentinelcorp.trading.model;

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

		StringBuilder sb = new StringBuilder();
		sb.append(getC());
		sb.append(' ');
		sb.append(getH());
		sb.append(' ');
		sb.append(getL());
		sb.append(' ');
		sb.append(getD());
		return sb.toString();
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
