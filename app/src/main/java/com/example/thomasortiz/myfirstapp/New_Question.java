package com.example.thomasortiz.myfirstapp;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class New_Question extends AppCompatActivity {
	DatabaseHelper myDb;
	private TextView description;
	private Button ele, sta, adv, submit_Question;
	private EditText questionText, ansA, ansB, ansC, ansD, ans, hint;
	private String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_questions);
		myDb = new DatabaseHelper(this);

		description = (TextView)findViewById(R.id.desc);
		ele = (Button) findViewById((R.id.ele));
		sta = (Button) findViewById((R.id.stand));
		adv = (Button) findViewById((R.id.adv));
		submit_Question = (Button) findViewById(R.id.Submit_Question);
		questionText = (EditText) findViewById(R.id.QuestionText);
		ansA = (EditText) findViewById(R.id.ansA);
		ansB = (EditText) findViewById(R.id.ansB);
		ansC = (EditText) findViewById(R.id.ansC);
		ansD = (EditText) findViewById(R.id.ansD);
		ans = (EditText) findViewById(R.id.ans);
		hint = (EditText) findViewById(R.id.hint);

		questionText.setEnabled(false);
		ansA.setEnabled(false);
		ansB.setEnabled(false);
		ansC.setEnabled(false);
		ansD.setEnabled(false);
		ans.setEnabled(false);
		hint.setEnabled(false);
		submit_Question.setEnabled(false);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		Toast.makeText(this, "Orientation Changed", Toast.LENGTH_SHORT);
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void onEleClicked(View view) {
		type = "ele";
		ele.setVisibility(View.INVISIBLE);
		sta.setVisibility(View.INVISIBLE);
		adv.setVisibility(View.INVISIBLE);
		ele.setEnabled(false);
		sta.setEnabled(false);
		adv.setEnabled(false);

		questionText.setEnabled(true);
		ansA.setEnabled(true);
		ansB.setEnabled(true);
		ansC.setEnabled(true);
		ansD.setEnabled(true);
		ans.setEnabled(true);
		hint.setEnabled(true);
		description.setText(R.string.Enter_Question_Parts);
		submit_Question.setEnabled(true);

	}
	public void onStandardClicked(View view) {
		type = "sta";
		ele.setVisibility(View.INVISIBLE);
		sta.setVisibility(View.INVISIBLE);
		adv.setVisibility(View.INVISIBLE);
		ele.setEnabled(false);
		sta.setEnabled(false);
		adv.setEnabled(false);

		questionText.setEnabled(true);
		ansA.setEnabled(true);
		ansB.setEnabled(true);
		ansC.setEnabled(true);
		ansD.setEnabled(true);
		ans.setEnabled(true);
		hint.setEnabled(false);
		description.setText(R.string.Enter_Question_Parts);
		submit_Question.setEnabled(true);


	}
	public void onAdvancedClicked(View view) {
		type = "adv";
		ele.setVisibility(View.INVISIBLE);
		sta.setVisibility(View.INVISIBLE);
		adv.setVisibility(View.INVISIBLE);
		ele.setEnabled(false);
		sta.setEnabled(false);
		adv.setEnabled(false);

		questionText.setEnabled(true);
		ansA.setEnabled(true);
		ansB.setEnabled(true);
		ansC.setEnabled(true);
		ansD.setEnabled(true);
		ans.setEnabled(true);
		hint.setEnabled(false);
		description.setText(R.string.Enter_Question_Parts);
		submit_Question.setEnabled(true);

	}

	public void onSubmitQuestionClicked(View view){
		boolean isInserted = false;
		if( checkQuestion() ) {
			Toast.makeText(this, "Question Added!", Toast.LENGTH_SHORT).show();

			questionText.setEnabled(false);
			ansA.setEnabled(false);
			ansB.setEnabled(false);
			ansC.setEnabled(false);
			ansD.setEnabled(false);
			ans.setEnabled(false);
			hint.setEnabled(false);
			description.setText(R.string.Question_Req);
			submit_Question.setEnabled(false);
			ele.setEnabled(true);
			ele.setVisibility(View.VISIBLE);
			sta.setEnabled(true);
			sta.setVisibility(View.VISIBLE);
			adv.setEnabled(true);
			adv.setVisibility(View.VISIBLE);

			if (type.equals("ele")) {
				// do stuff with all the editText fields
				isInserted = myDb.insertData(questionText.getText().toString(),
											ansA.getText().toString(),
											ansB.getText().toString(),
											ansC.getText().toString(),
											ansD.getText().toString(),
											ans.getText().toString(),
											hint.getText().toString() );
			} else if (type.equals("adv")) {
				isInserted = myDb.insertData(questionText.getText().toString(),
											ansA.getText().toString(),
											ansB.getText().toString(),
											ansC.getText().toString(),
											ansD.getText().toString(),
											ans.getText().toString(),
											hint.getText().toString() );
			} else {
				isInserted = myDb.insertData(questionText.getText().toString(),
											ansA.getText().toString(),
											ansB.getText().toString(),
											ansC.getText().toString(),
											ansD.getText().toString(),
											ans.getText().toString(),
											hint.getText().toString() );
			}

			if(isInserted){
				Toast.makeText(New_Question.this, "Data Inserted", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(New_Question.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
			}

			questionText.setText("");
			ansA.setText("");
			ansB.setText("");
			ansC.setText("");
			ansD.setText("");
			ans.setText("");
			hint.setText("");

		} else {  // Question is not valid
			Toast.makeText(this, "Invalid Question, try your input again", Toast.LENGTH_SHORT).show();

			questionText.setEnabled(false);
			ansA.setEnabled(false);
			ansB.setEnabled(false);
			ansC.setEnabled(false);
			ansD.setEnabled(false);
			ans.setEnabled(false);
			hint.setEnabled(false);
			description.setText(R.string.Question_Req);
			submit_Question.setEnabled(false);
			ele.setEnabled(true);
			ele.setVisibility(View.VISIBLE);
			sta.setEnabled(true);
			sta.setVisibility(View.VISIBLE);
			adv.setEnabled(true);
			adv.setVisibility(View.VISIBLE);
		}

	}

	public boolean checkQuestion(){
		if( !questionText.getText().toString().isEmpty() )
			return true;
		else if( !ansA.getText().toString().isEmpty() )
			return true;
		else if( !ansB.getText().toString().isEmpty() )
			return true;
		else if( !ansC.getText().toString().isEmpty() )
			return true;
		else if( !ansD.getText().toString().isEmpty() )
			return true;
		else if( !ans.getText().toString().isEmpty() )
			return true;
		else if( hint.getText().toString().isEmpty() )
			return false;
		return false;
	}

	public void onReturnToMainClicked(View view){
		Intent nextScreen = new Intent(getApplicationContext(), Main_Menu.class);
		finish();
		startActivity(nextScreen);
	}

}
