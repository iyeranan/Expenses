package com.expenses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

public class ExpensesActivity extends Activity implements OnClickListener{
	Button btnAddPeople;
	TableLayout tblPersons;
	Button btnContinue;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		tblPersons = (TableLayout) findViewById(R.id.tblPersons);
		btnAddPeople = (Button) findViewById(R.id.btnAddPeople);
		btnContinue = (Button) findViewById(R.id.btnContinue);

		Log.d("btnAddPeople", "btnAddPeople::"+btnAddPeople);
		System.out.println("btnAddPeople::"+btnAddPeople);
		btnAddPeople.setOnClickListener(this);
		btnContinue.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAddPeople:

			final TableRow tr = new TableRow(this);
			tr.setId(1);

			EditText editText = new EditText(this);
			editText.setId(200);
			editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
			editText.setMinWidth(100);
			//			labelTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			
			tr.addView(editText);
			editText.requestFocus();

			Button deleteButton =  new Button(this);
			deleteButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.delete));
			deleteButton.setId(200);
			deleteButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					tblPersons.removeView(tr);
				}
			});

			tr.addView(deleteButton);

			tblPersons.addView(tr);

			break;
		case R.id.btnContinue:
			String names = "";

			for (int i = 0; i < tblPersons.getChildCount(); i++) {
				TableRow row = (TableRow) tblPersons.getChildAt(i);
				String name = ((EditText)(row.getChildAt(0))).getText().toString();
				if(name!=null && name.trim()!=""){
					names = names + name.trim();	
				}				

				if(i < tblPersons.getChildCount()-1){
					names = names + ",";
				}
			}

			System.out.println("!!!!!!!!!!names:"+ names);

			Intent intentExpenses = new Intent();
			intentExpenses.setClassName("com.expenses", "com.expenses.ExpensesShareActivity");
			intentExpenses.putExtra("com.expenses.PersonNames", names); 
			startActivity(intentExpenses);

			break;
		default:
			break;
		}		
	}
}