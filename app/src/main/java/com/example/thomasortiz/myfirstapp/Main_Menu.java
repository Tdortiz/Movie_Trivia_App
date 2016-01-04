package com.example.thomasortiz.myfirstapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Main_Menu extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
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

	public void onQuizClicked(View view) {
		Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
		finish();
		startActivity(nextScreen);

	}

	public void onRulesClicked(View view) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Rules");
		alertDialog.setMessage("There are three different sets of questions: Easy, Standard, and " +
				"Advanced.\n\nYou always start with an Easy question.\n\nEasy Question:\n * If you " +
				"correctly answer two Easy questions in a row you move to a Standard Question.\n *If " +
				"you answer an Easy Question wrong you are given a 2nd chance and a hint to help " +
				"you.\n\nStandard Question:\n * If you answer a Standard Question correct you move on " +
				"to an Advanced Question.\n * No hints are given if you get this wrong.\n\n * Answer " +
				"any of these questions wrong and you bumped down to the Question level below it.  ");
		alertDialog.show();
	}

	public void onMainMenuQuitClicked(View view) {
		finish();
		System.exit(1);
	}

	public void onAddQuestionClicked(View view){
		Intent nextScreen = new Intent(getApplicationContext(), New_Question.class);
		finish();
		startActivity(nextScreen);
	}

}