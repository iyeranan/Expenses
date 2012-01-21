package com.expenses.entity;

import java.io.Serializable;

public class NetExpenses implements Serializable{
	private static final long serialVersionUID = -1274177016556700720L;

	public NetExpenses(String fromName, Float amount, String toName) {
		super();
		this.fromName = fromName;
		this.amount = amount;
		this.toName = toName;
	}

	private String fromName = "";	
	private Float amount;
	private String toName = "";

	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
}
