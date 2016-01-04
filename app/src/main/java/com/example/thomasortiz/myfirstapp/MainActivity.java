package com.example.thomasortiz.myfirstapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.InputStream;
import movie101.question.QuestionState;
import movie101.quiz.MovieQuiz;
import movie101.util.EmptyQuestionListException;
import question_library.QuestionException;

import static com.example.thomasortiz.myfirstapp.R.string.Next_Questions;

public class MainActivity extends AppCompatActivity {
	private Button submitButton, nextButton;
	private RadioGroup radioGroup;
	private RadioButton answerA, answerB, answerC, answerD;
	private TextView textView1, textView2, correctQuestions, totalQuestions;
	private MovieQuiz mq;
	private GoogleApiClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		submitButton = (Button) findViewById(R.id.Button1);
		nextButton = (Button) findViewById(R.id.Button2);

		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		answerA = (RadioButton) findViewById(R.id.answer1);
		answerB = (RadioButton) findViewById(R.id.answer2);
		answerC = (RadioButton) findViewById(R.id.answer3);
		answerD = (RadioButton) findViewById(R.id.answer4);

		textView1 = (TextView) findViewById(R.id.text1);
		textView2 = (TextView) findViewById(R.id.text2);
		correctQuestions = (TextView) findViewById(R.id.correctQuestions);
		totalQuestions = (TextView) findViewById(R.id.totalQuestions);

		submitButton.setEnabled(false);
		nextButton.setEnabled(false);

		try {
			InputStream is = getResources().openRawResource(R.raw.questions);
			this.mq = new MovieQuiz(is);

			textView1.setText(mq.getCurrentQuestionText());
			textView2.setText("");
			String[] answers = mq.getCurrentQuestionChoices();
			answerA.setText(answers[0]);
			answerB.setText(answers[1]);
			answerC.setText(answers[2]);
			answerD.setText(answers[3]);

			//Should add a scoreboard
			correctQuestions.setText(mq.getNumCorrectQuestions());
			totalQuestions.setText(mq.getNumAttemptedQuestions());

		} catch (IllegalArgumentException | QuestionException | IndexOutOfBoundsException | EmptyQuestionListException e) {
			e.getMessage();
		}

		client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

	public void onQuitClicked(View view) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Your Score: " + mq.getNumCorrectQuestions());
		alertDialog.setMessage("You answered " + mq.getNumCorrectQuestions() + " out of " + mq.getNumAttemptedQuestions() + " questions correctly! Go you!");
		alertDialog.setButton("Back to Main Menu", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent nextScreen = new Intent(getApplicationContext(), Main_Menu.class);
				finish();
				startActivity(nextScreen);
			}
		});
		alertDialog.show();
	}

	public void onNextQuestionClicked(View view) {
		radioGroup.clearCheck();
		answerA.setEnabled(true);
		answerB.setEnabled(true);
		answerC.setEnabled(true);
		answerD.setEnabled(true);
		correctQuestions.setText(mq.getNumCorrectQuestions());
		totalQuestions.setText(mq.getNumAttemptedQuestions());
		submitButton.setEnabled(false);

		if (mq.hasMoreQuestions()) {
			textView1.setVisibility(View.INVISIBLE);
		}

		nextButton.setEnabled(false);
		textView1.setVisibility(ViewStub.VISIBLE);
		textView2.setText("");
		nextButton.setText(Next_Questions);
		if (mq.hasMoreQuestions()) {

			try {
				textView1.setText(mq.getCurrentQuestionText());
				answerA.setText(mq.getCurrentQuestionChoices()[0]);
				answerB.setText(mq.getCurrentQuestionChoices()[1]);
				answerC.setText(mq.getCurrentQuestionChoices()[2]);
				answerD.setText(mq.getCurrentQuestionChoices()[3]);

			} catch (EmptyQuestionListException e1) {
				e1.getMessage();
			}

			nextButton.setEnabled(false);
			radioGroup.setEnabled(true);

		} else { // IF there are no more questions in test
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Your Score: " + mq.getNumCorrectQuestions());
			alertDialog.setMessage("You answered " + mq.getNumCorrectQuestions() + " out of " + mq.getNumAttemptedQuestions() + " questions correctly! Go you!");

			alertDialog.setButton("Return to Main Menu", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent nextScreen = new Intent(getApplicationContext(), Main_Menu.class);
					finish();
					startActivity(nextScreen);
				}
			});

			alertDialog.show();
		}
	}

	public void onRadioButtonClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		// Check which radio button was clicked
		switch (view.getId()) {
			case R.id.answer1:
				if (checked)
					break;
			case R.id.answer2:
				if (checked)
					break;
			case R.id.answer3:
				if (checked)
					break;
			case R.id.answer4:
				if (checked)
					break;
		}
		submitButton.setEnabled(true);
	}

	public void onSubmitQuestionClicked(View view) {
		nextButton.setEnabled(true);
		submitButton.setEnabled(false);
		QuestionState current = mq.getQuestions().getCurrentState();

		try {
			if (answerA.isChecked()) {
				//textView2.setText(mq.processAnswer("a"));
				textView2.setText(mq.processAnswer(answerA.getText().toString()));
			} else if (answerB.isChecked()) {
				//textView2.setText(mq.processAnswer("b"));
				textView2.setText(mq.processAnswer(answerB.getText().toString()));
			} else if (answerC.isChecked()) {
				//textView2.setText(mq.processAnswer("c"));
				textView2.setText(mq.processAnswer(answerC.getText().toString()));
			} else if (answerD.isChecked()) {
				//textView2.setText(mq.processAnswer("d"));
				textView2.setText(mq.processAnswer(answerD.getText().toString()));
			}

		} catch (EmptyQuestionListException EQLE) {
			EQLE.getCause();
		} catch (IndexOutOfBoundsException ee) {
			submitButton.setEnabled(false);
			nextButton.setEnabled(false);
			textView2.setText(R.string.Out_of_Questions);
		}

		correctQuestions.setText(mq.getNumCorrectQuestions());
		totalQuestions.setText(mq.getNumAttemptedQuestions());
		nextButton.setText(Next_Questions);

		if ((!textView2.getText().toString().equals("Correct!")) &&
				(current == mq.getQuestions().getEleQuestionState())
				&& mq.getQuestions().getEleQuestionState().getAttempts() == 1) {

			if(mq.getQuestions().getEleQuestionState().getAttempts() >= 2){
				mq.getQuestions().getEleQuestionState().setAttemptsToZero();
			}
			nextButton.setText(R.string.Try_Again);

		}

		// Disables these buttons while moving to new question.
		submitButton.setEnabled(false);
		radioGroup.setEnabled(false);
		answerA.setEnabled(false);
		answerB.setEnabled(false);
		answerC.setEnabled(false);
		answerD.setEnabled(false);
	}

	@Override
	public void onStart() {
		super.onStart();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client.connect();
		Action viewAction = Action.newAction(
				Action.TYPE_VIEW, // TODO: choose an action type.
				"Main Page", // TODO: Define a title for the content shown.
				// TODO: If you have web page content that matches this app activity's content,
				// make sure this auto-generated web page URL is correct.
				// Otherwise, set the URL to null.
				Uri.parse("http://host/path"),
				// TODO: Make sure this auto-generated app deep link URI is correct.
				Uri.parse("android-app://com.example.thomasortiz.myfirstapp/http/host/path")
		);
		AppIndex.AppIndexApi.start(client, viewAction);
	}

	@Override
	public void onStop() {
		super.onStop();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		Action viewAction = Action.newAction(
				Action.TYPE_VIEW, // TODO: choose an action type.
				"Main Page", // TODO: Define a title for the content shown.
				// TODO: If you have web page content that matches this app activity's content,
				// make sure this auto-generated web page URL is correct.
				// Otherwise, set the URL to null.
				Uri.parse("http://host/path"),
				// TODO: Make sure this auto-generated app deep link URI is correct.
				Uri.parse("android-app://com.example.thomasortiz.myfirstapp/http/host/path")
		);
		AppIndex.AppIndexApi.end(client, viewAction);
		client.disconnect();
	}
}