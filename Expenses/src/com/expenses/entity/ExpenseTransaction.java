package com.expenses.entity;

import java.io.Serializable;
import java.util.List;

public class ExpenseTransaction implements Serializable {
	private static final long serialVersionUID = 2505411958468486668L;

	private String payeeName;
	private Float amount;
	private List<String> recipientList;

	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public List<String> getRecipientList() {
		return recipientList;
	}
	public void setRecipientList(List<String> recipientList) {
		this.recipientList = recipientList;
	}

}
