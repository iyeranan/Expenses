package com.expenses.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.expenses.entity.ExpenseTransaction;
import com.expenses.entity.NetExpenses;
import com.expenses.entity.Person;

public class ExpenseCalculator {
	static Map<String, Float> resultMap = new HashMap<String, Float>();
	static List<Person> debtorsList = new ArrayList<Person>();
	static List<Person> creditorsList = new ArrayList<Person>();

	public static void calculateAmounts(List<ExpenseTransaction> transactions) {
		for (ExpenseTransaction txn : transactions) {
			int numberOfRecipients = txn.getRecipientList().size();
			Float share = txn.getAmount()/numberOfRecipients;
			//			Float owePayee = 0f;
			for (String recipient : txn.getRecipientList()) {
				//				owePayee = owePayee + share;
				if(resultMap.get(recipient)!=null){
					resultMap.put(recipient, resultMap.get(recipient)-share);
				}else{
					resultMap.put(recipient, - share);
				}
			}
			if(resultMap.get(txn.getPayeeName())!=null){
				resultMap.put(txn.getPayeeName(), resultMap.get(txn.getPayeeName())+txn.getAmount());
			}else{
				resultMap.put(txn.getPayeeName(),  txn.getAmount());
			}
		}

		Iterator<Entry<String, Float>> it = resultMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Float> pairs = it.next();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
			if(pairs.getValue()>0){
				creditorsList.add(new Person(pairs.getKey(), pairs.getValue()));
			} else if (pairs.getValue()<0){
				debtorsList.add(new Person(pairs.getKey(), pairs.getValue()));
			}
		}
	}

	public static ArrayList<NetExpenses> fetchResults(){
		ArrayList<NetExpenses> netExpensesList = new ArrayList<NetExpenses>();
		Collections.sort(debtorsList);
		Collections.reverse(debtorsList);
		Collections.sort(creditorsList);

		for (Person debtor : debtorsList) {
			for (Person creditor : creditorsList) {
				if(debtor.getAmount()!=0 && creditor.getAmount()!=0) {
					Float transferAmount = (-debtor.getAmount())<creditor.getAmount()?(-debtor.getAmount()):creditor.getAmount();

					System.out.println(debtor.getName()+" pays "+ transferAmount + " to "+ creditor.getName());

					netExpensesList.add(new NetExpenses(debtor.getName(), 
							new BigDecimal(transferAmount).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue(), 
							creditor.getName()));
					debtor.setAmount(debtor.getAmount()+transferAmount);
					creditor.setAmount(creditor.getAmount()-transferAmount);

					if(debtor.getAmount()==0){
						break;
					}
				}
			}
		}
		return netExpensesList;
	}

	public static void main(String[] args) {
		System.out.println("Hello");

		List<ExpenseTransaction> transactions = new ArrayList<ExpenseTransaction>();

		ExpenseTransaction txn = new ExpenseTransaction();
		txn.setAmount(90f);
		txn.setPayeeName("Sagar");
		List<String> recipientList = new ArrayList<String>();
		recipientList.add("Sagar");
		recipientList.add("Andy");
		recipientList.add("Roshan");
		recipientList.add("Nithin");
		txn.setRecipientList(recipientList);
		transactions.add(txn);

		ExpenseTransaction txn2 = new ExpenseTransaction();
		txn2.setAmount(200f);
		txn2.setPayeeName("Andy");
		List<String> recipientList2 = new ArrayList<String>();
		recipientList2.add("Sagar");
		recipientList2.add("Andy");
		recipientList2.add("Roshan");
		recipientList2.add("Nithin");
		txn2.setRecipientList(recipientList2);
		transactions.add(txn2);

		calculateAmounts(transactions);
		fetchResults();
		System.out.println("Bye");
	}
}
