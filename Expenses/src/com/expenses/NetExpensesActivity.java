package com.expenses;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

import com.expenses.entity.NetExpenses;

public class NetExpensesActivity extends Activity implements OnClickListener{

	TableLayout tblNetExpenses;
	TableRow trHeaderNetExpenses;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.netexpenses);

		tblNetExpenses = (TableLayout) findViewById(R.id.tblNetExpenses);
		trHeaderNetExpenses = (TableRow) findViewById(R.id.trHeaderNetExpenses);		

		Intent intent = getIntent();
		ArrayList<NetExpenses> netExpenses = (ArrayList<NetExpenses>) intent.getSerializableExtra("com.expenses.NetExpensesList");

		if(netExpenses !=null && netExpenses.size()>0) {
			for (NetExpenses netExpense : netExpenses) {
				LayoutParams layoutParams = new LayoutParams(1,LayoutParams.WRAP_CONTENT,1f);
				layoutParams.setMargins(1, 1, 1, 1);

				TextView fromName = new TextView(this);
				fromName.setText(netExpense.getFromName());
				fromName.setLayoutParams(layoutParams);
				fromName.setGravity(Gravity.CENTER_HORIZONTAL);
				fromName.setBackgroundColor(Color.WHITE);
				fromName.setTextColor(Color.BLACK);

				TextView amount = new TextView(this);
				amount.setText(netExpense.getAmount().toString());
				amount.setLayoutParams(layoutParams);
				amount.setGravity(Gravity.CENTER_HORIZONTAL);
				amount.setBackgroundColor(Color.WHITE);
				amount.setTextColor(Color.BLACK);

				TextView toName = new TextView(this);
				toName.setText(netExpense.getToName());
				toName.setLayoutParams(layoutParams);
				toName.setGravity(Gravity.CENTER_HORIZONTAL);
				toName.setBackgroundColor(Color.WHITE);
				toName.setTextColor(Color.BLACK);

				TableRow tr = new TableRow(this);
				tr.addView(fromName);
				tr.addView(toName);
				tr.addView(amount);

				tblNetExpenses.addView(tr);
			}
		} else {
			tblNetExpenses.removeView(trHeaderNetExpenses);
		}
	}

	public void onClick(View v) {
	}
}