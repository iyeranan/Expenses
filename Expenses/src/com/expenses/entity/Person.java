package com.expenses.entity;

import java.io.Serializable;


public class Person implements Comparable<Person>, Serializable{
	private static final long serialVersionUID = 1312702756758463938L;
	private String name;
	private Float amount;
	private boolean resultSatisfied = false;

	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(String name, Float amount) {
		this.name=name;
		this.amount=amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public boolean isResultSatisfied() {
		return resultSatisfied;
	}

	public void setResultSatisfied(boolean resultSatisfied) {
		this.resultSatisfied = resultSatisfied;
	}

	public int compareTo(Person person) {
		return this.amount.compareTo(person.getAmount());
	}


}
