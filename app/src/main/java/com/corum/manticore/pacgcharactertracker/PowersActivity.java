package com.corum.manticore.pacgcharactertracker;

import com.corum.manticore.sqlite.helper.DatabaseHelper;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class PowersActivity extends Activity {
	private DatabaseHelper db;
	private int adventurer_id;
	private int powers_id;
	private int char_id;
	private int skills_id;
	private String charName;
	private int[] powerData;
	private int smallText = 15;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new DatabaseHelper(getApplicationContext());
		setContentView(R.layout.activity_powers);

		Intent intent = getIntent();
		this.adventurer_id = intent.getIntExtra("ADVENTURER_ID", 0);
		this.powers_id = intent.getIntExtra("POWERS_ID", 0);
		this.char_id = intent.getIntExtra("CHARACTER_ID", 0);
		this.skills_id = intent.getIntExtra("SKILLS_ID", 0);
		this.charName = intent.getStringExtra("CHAR_NAME");
		
		powerData = db.getPowers(this.powers_id).getPowers();
		addPowers();
		
		setTitle(this.charName + " - Powers");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.powers, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){		    
		case R.id.menu_about:
        	ContextThemeWrapper cw = new ContextThemeWrapper(this, R.style.AlertDialogTheme);
        	AlertDialog.Builder builder = new AlertDialog.Builder(cw);
        	builder.setTitle(R.string.comm_policy)
        		   .setMessage(R.string.community_use)
        	       .setCancelable(false)
        	       .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	                //do things
        	           }
        	       });
        	AlertDialog alert = builder.create();
        	alert.show();
            return true;
		}

		return true;
	}
	
	private void addPowers(){
		// Add Hand Size
		Button hand = (Button) findViewById(R.id.number);
		hand.setText(String.valueOf(this.powerData[0]));
		
		if(this.char_id == 1){ addPowersAmiri();}
		else if(this.char_id == 2){ addPowersEzren();}
		else if(this.char_id == 3){ addPowersHarsk();}
		else if(this.char_id == 4){ addPowersKyra();}
		else if(this.char_id == 5){ addPowersLem();}
		else if(this.char_id == 6){ addPowersLini();}
		else if(this.char_id == 7){ addPowersMerisiel();}
		else if(this.char_id == 8){ addPowersSajan();}
		else if(this.char_id == 9){ addPowersSeelah();}
		else if(this.char_id == 10){ addPowersSeoni();}
		else if(this.char_id == 11){ addPowersValeros();}
		
	}
	
	public void chooseClass(View v){
		int preSelect = 0;
		int items = 0;
		if(this.char_id == 1){ items = R.array.classes_amiri;}
		else if(this.char_id == 2){ items = R.array.classes_ezren;}
		else if(this.char_id == 3){ items = R.array.classes_harsk;}
		else if(this.char_id == 4){ items = R.array.classes_kyra;}
		else if(this.char_id == 5){ items = R.array.classes_lem;}
		else if(this.char_id == 6){ items = R.array.classes_lini;}
		else if(this.char_id == 7){ items = R.array.classes_merisiel;}
		else if(this.char_id == 8){ items = R.array.classes_sajan;}
		else if(this.char_id == 9){ items = R.array.classes_seelah;}
		else if(this.char_id == 10){ items = R.array.classes_seoni;}
		else if(this.char_id == 11){ items = R.array.classes_valeros;}
		
		if(this.powerData[1] == 2){ preSelect = 2;}
		else if(this.powerData[1] == 1){ preSelect = 1;}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
		builder.setTitle(R.string.select_role);
		builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				db.updatePower(powers_id, 2, which);
				refresh();
			}				
		});	
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	private void addPowersAmiri(){	
		// Set Proficiencies
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(40, 0, 0, 0);
		// Proficiencies Word
		TextView button = (TextView) findViewById(R.id.prof_word);
		button.setVisibility(View.VISIBLE);
		// Light Armors
		button = (Button) findViewById(R.id.prof_light);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		button.setOnClickListener(null);
		// Heavy Armors
		button = (Button) findViewById(R.id.prof_heavy);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		if(this.powerData[15] == 0){ 
			button.setTextColor(Color.GRAY);
			button.setTypeface(null, Typeface.ITALIC);
			button.setTextSize(this.smallText);}
		// Weapons
		button = (Button) findViewById(R.id.prof_weapon);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		button.setOnClickListener(null);
		View view = (View) findViewById(R.id.hr2);
		view.setVisibility(View.VISIBLE);
		
		// Set Class/Role Name
		String className = null;
		switch(powerData[1]){
		case 0:
			className = getString(R.string.class_amiri_base); break;
		case 1:
			className = getString(R.string.class_amiri_1);  break;
		case 2:
			className = getString(R.string.class_amiri_2);  break;
		}
		button = (Button) findViewById(R.id.name_and_class);
		button.setText(className);
		
		// Get Base Powers
		String power1 = getString(R.string.power_amiri_012_1_base_1);
		if(powerData[1] == 1 && powerData[7] == 1){ power1 += " " + getString(R.string.power_amiri_1_1_upg_6);}
		power1 += " " + getString(R.string.power_amiri_012_1_base_2);
		if(powerData[1] == 1){
			if(powerData[10] > 0){ power1 += " " + getString(R.string.power_amiri_1_1_upg_9);}
			else if(powerData[9] > 0){ power1 += " " + getString(R.string.power_amiri_12_1_upg_87);}
			else if(powerData[8] > 0){ power1 += " " + getString(R.string.power_amiri_12_1_upg_76);}
			else if(powerData[4] > 0){ power1 += " " + getString(R.string.power_amiri_012_1_upg_3);}
		}else if(powerData[1] == 2){
			if(powerData[8] > 0){ power1 += " " + getString(R.string.power_amiri_12_1_upg_87);}
			else if(powerData[7] > 0){ power1 += " " + getString(R.string.power_amiri_12_1_upg_76);}
			else if(powerData[4] > 0){ power1 += " " + getString(R.string.power_amiri_012_1_upg_3);}
		}else{ if(powerData[4] > 0){ power1 += " " + getString(R.string.power_amiri_012_1_upg_3);}}
		power1 += " " + getString(R.string.power_amiri_012_1_base_3);
		button = (Button) findViewById(R.id.power1);
		button.setVisibility(View.VISIBLE);
		button.setText(power1);
		button.setOnClickListener(ami_listener);

		String power2 = getString(R.string.power_amiri_012_2_base_1);
		if(powerData[5] > 0){ power2 += " " + getString(R.string.power_amiri_012_2_upg_4);}
		power2 += getString(R.string.power_amiri_012_2_base_2);
		button = (Button) findViewById(R.id.power2);
		button.setVisibility(View.VISIBLE);
		button.setText(power2);
		button.setOnClickListener(ami_listener);

		// Check for Role 1 - Berserker
		if(this.powerData[1] == 1){
			String power3 = getString(R.string.power_amiri_1_3_upg_10_base_1);
			if(powerData[12] > 0){ power3 += " " + getString(R.string.power_amiri_1_3_upg_11);}
			else{ power3 += " " + getString(R.string.power_amiri_1_3_upg_10_base_2);}
			power3 += " " + getString(R.string.power_amiri_1_3_upg_10_base_3);
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(ami_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[11] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			//Power 4
			String power4 = getString(R.string.power_amiri_12_45_upg_12);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(ami_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
		}
		// Check for Role 2 - Juggernaut
		else if(this.powerData[1] == 2) {	
			String power3 = getString(R.string.power_amiri_2_3_upg_8_base_1);
			if(powerData[10] > 0){ power3 += " " + getString(R.string.power_amiri_2_3_upg_9);}
			power3 += " " + getString(R.string.power_amiri_2_3_upg_8_base_2);
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(ami_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[9] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
				
			// Power 4
			String power4 = getString(R.string.power_amiri_2_4_upg_10_base_1);
			if(powerData[12] > 0){ power4 += " " + getString(R.string.power_amiri_2_4_upg_11);}
			else{ power4 += " " + getString(R.string.power_amiri_2_4_upg_10_base_2);}
			power4 += " " + getString(R.string.power_amiri_2_4_upg_10_base_3);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(ami_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[11] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 5
			String power5 = getString(R.string.power_amiri_12_45_upg_12);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(ami_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
		}
	}
	
	private OnClickListener ami_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final int id = v.getId();
			int preSelect = 0;
			int items = 0;
			switch(id){
			case R.id.power1: 
				if(powerData[1] == 0){
					items = R.array.power_amiri_0_1;
					if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}else if(powerData[1] == 1){
					items = R.array.power_amiri_1_1;
					if(powerData[10] > 0){ preSelect = 6;}
					else if(powerData[9] > 0){ preSelect = 5;}
					else if(powerData[8] > 0){ preSelect = 4;}
					else if(powerData[4] > 0){ preSelect = 3;}
					else{ preSelect = 2;}
				}else{
					items = R.array.power_amiri_2_1;
					if(powerData[8] > 0){ preSelect = 3;}
					else if(powerData[7] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power2:
				items = R.array.power_amiri_012_2;
				if(powerData[5] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			case R.id.power3: 
				if(powerData[1] == 1){
					items = R.array.power_amiri_12_34;
					if(powerData[12] > 0){ preSelect = 2;}
					else if(powerData[11] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				} else{
					items = R.array.power_amiri_2_3;
					if(powerData[10] > 0){ preSelect = 2;}
					else if(powerData[9] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power4:
				if(powerData[1] == 1){ 
					items = R.array.power_amiri_12_45;
					if(powerData[13] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				else if(powerData[1] == 2){ 
					items = R.array.power_amiri_12_34;
					if(powerData[12] > 0){ preSelect = 2;}
					else if(powerData[11] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			default:
				items = R.array.power_amiri_12_45;
				if(powerData[13] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
			builder.setTitle(R.string.select_feat);
			builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					updatePowerAmiri(id, which);
					dialog.dismiss();
					refresh();
				}				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	public void updatePowerAmiri(int power, int which){
		switch(power){
		case R.id.power1:
			if(powerData[1] == 0){
				switch(which){
				case 0:
					db.updatePower(powers_id, 5, 0); break;
				case 1:
					db.updatePower(powers_id, 5, 1); break;
				}
			}else if(powerData[1] == 1){
				switch(which){
				case 0: 
					db.updatePower(powers_id, 8, 0); break;
				case 1:
					db.updatePower(powers_id, 8, 1); break;
				case 2:
					db.updatePower(powers_id, 5, 0);
					db.updatePower(powers_id, 9, 0);
					db.updatePower(powers_id, 10, 0);
					db.updatePower(powers_id, 11, 0); break;
				case 3:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 9, 0);
					db.updatePower(powers_id, 10, 0);
					db.updatePower(powers_id, 11, 0); break;
				case 4: 
					db.updatePower(powers_id, 9, 1);
					db.updatePower(powers_id, 10, 0);
					db.updatePower(powers_id, 11, 0); break;
				case 5:
					db.updatePower(powers_id, 10, 1);
					db.updatePower(powers_id, 11, 0); break;
				case 6:	
					db.updatePower(powers_id, 11, 1); break;		
				}
			}else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 5, 0);
					db.updatePower(powers_id, 8, 0);
					db.updatePower(powers_id, 9, 0); break;
				case 1:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 8, 0);
					db.updatePower(powers_id, 9, 0); break;
				case 2:
					db.updatePower(powers_id, 8, 1);
					db.updatePower(powers_id, 9, 0); break;
				case 3:
					db.updatePower(powers_id, 9, 1); break;
				}
			}
			break;
		case R.id.power2:
			switch(which){
			case 0:
				db.updatePower(powers_id, 6, 0); break;
			case 1:
				db.updatePower(powers_id, 6, 1); break;
			}
			break;
		case R.id.power3:
			if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 12, 0);
					db.updatePower(powers_id, 13, 0); break;
				case 1:
					db.updatePower(powers_id, 12, 1);
					db.updatePower(powers_id, 13, 0); break;
				case 2:
					db.updatePower(powers_id, 12, 1);
					db.updatePower(powers_id, 13, 1); break;
				}
			}else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 10, 0);
					db.updatePower(powers_id, 11, 0); break;
				case 1:
					db.updatePower(powers_id, 10, 1);
					db.updatePower(powers_id, 11, 0); break;
				case 2:
					db.updatePower(powers_id, 10, 1);
					db.updatePower(powers_id, 11, 1); break;
				}
			}
			break;
		case R.id.power4:
			if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 14, 0); break;
				case 1:
					db.updatePower(powers_id, 14, 1); break;
				}
			} else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 12, 0);
					db.updatePower(powers_id, 13, 0); break;
				case 1:
					db.updatePower(powers_id, 12, 1);
					db.updatePower(powers_id, 13, 0); break;
				case 2:
					db.updatePower(powers_id, 12, 1);
					db.updatePower(powers_id, 13, 1); break;
				}
			} 
			break;
		case R.id.power5:
			switch(which){
			case 0:
				db.updatePower(powers_id, 14, 0); break;
			case 1:
				db.updatePower(powers_id, 14, 1); break;
			} 
			break;
		}
	}
	
	private void addPowersEzren(){		
		// Get Base Powers
		String power1 = getString(R.string.power_ezren_012_1_base_1);
		if(powerData[1] == 2 && powerData[6] > 0){ power1 += " " + getString(R.string.power_ezren_2_1_upg_5);}
		power1 += getString(R.string.power_ezren_012_1_base_2);
		Button button = (Button) findViewById(R.id.power1);
		button.setVisibility(View.VISIBLE);
		button.setText(power1);
		if(this.powerData[1] == 2){ button.setOnClickListener(ezr_listener);}

		String power2 = getString(R.string.power_ezren_012_2);
		button = (Button) findViewById(R.id.power2);
		button.setVisibility(View.VISIBLE);
		button.setText(power2);
		
		// Set Class/Role Name
		String className = null;
		switch(powerData[1]){
		case 0:
			className = getString(R.string.class_ezren_base); break;
		case 1:
			className = getString(R.string.class_ezren_1);  break;
		case 2:
			className = getString(R.string.class_ezren_2);  break;
		}
		button = (Button) findViewById(R.id.name_and_class);
		button.setText(className);

		//Power 3
		String power3 = getString(R.string.power_ezren_012_3_upg_3_base_1);
		if(powerData[1] > 0){
			if(powerData[8] > 0){ power3 += " " + getString(R.string.power_ezren_12_3_upg_7);}
			else if(powerData[7] > 0){ power3 += " " + getString(R.string.power_ezren_12_3_upg_6);}
			else if(powerData[5] > 0){ power3 += " " + getString(R.string.power_ezren_012_3_upg_4);}
			else{ power3 += " " + getString(R.string.power_ezren_012_3_upg_3_base_2);}
		}else{
			if(powerData[5] > 0){ power3 += " " + getString(R.string.power_ezren_012_3_upg_4);}
			else{ power3 += " " + getString(R.string.power_ezren_012_3_upg_3_base_2);}
		}
		power3 += " " + getString(R.string.power_ezren_012_3_upg_3_base_3);
		button = (Button) findViewById(R.id.power3);
		button.setVisibility(View.VISIBLE);
		button.setText(power3);
		button.setOnClickListener(ezr_listener);
		View view = (View) findViewById(R.id.hr5);
		view.setVisibility(View.VISIBLE);
		if(this.powerData[4] == 0){ 
			button.setTextColor(Color.GRAY);
			button.setTypeface(null, Typeface.ITALIC);
			button.setTextSize(this.smallText);}

		// Check for Role 1 - Evoker
		if(this.powerData[1] == 1){
			//Power 4
			String power4 = getString(R.string.power_ezren_1_4_upg_8_base_1);
			if(powerData[10] > 0){ power4 += " " + getString(R.string.power_ezren_1_4_upg_9);}
			if(powerData[11] > 0){ power4 += " " + getString(R.string.power_ezren_1_4_upg_10);}
			if(powerData[10] > 0 || powerData[11] > 0){ power4 += " " + getString(R.string.power_ezren_1_4_upg_8_base_3);}
			else{ power4 += " " + getString(R.string.power_ezren_1_4_upg_8_base_2);}
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(ezr_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[9] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			//Power 5
			String power5 = getString(R.string.power_ezren_1_5_upg_11_base_1);
			if(powerData[13] > 0){ power5 += " " + getString(R.string.power_ezren_1_5_upg_12);}
			else{ power5 += " " + getString(R.string.power_ezren_1_5_upg_11_base_2);}
			power5 += " " + getString(R.string.power_ezren_1_5_upg_11_base_3);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(ezr_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[12] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
		}
		// Check for Role 2 - Illusionist
		else if(this.powerData[1] == 2) {			
			// Power 4
			String power4 = getString(R.string.power_ezren_2_4_upg_8);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(ezr_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[9] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 5
			String power5 = getString(R.string.power_ezren_2_5_upg_9_base_1);
			if(powerData[11] > 0){ power5 += " " + getString(R.string.power_ezren_2_5_upg_10);}
			else{ power5 += " " + getString(R.string.power_ezren_2_5_upg_9_base_2);}
			power5 += " " + getString(R.string.power_ezren_2_5_upg_9_base_3);
			if(powerData[12] > 0){ power5 += " " + getString(R.string.power_ezren_2_5_upg_11);}
			power5 += getString(R.string.power_ezren_2_5_upg_9_base_4);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(ezr_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[10] == 0 && this.powerData[11] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 6
			String power6 = getString(R.string.power_ezren_2_6_upg_12);
			button = (Button) findViewById(R.id.power6);
			button.setVisibility(View.VISIBLE);
			button.setText(power6);
			button.setOnClickListener(ezr_listener);
			view = (View) findViewById(R.id.hr8);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
		}
	}
	
	private OnClickListener ezr_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final int id = v.getId();
			int preSelect = 0;
			int items = 0;
			switch(id){
			case R.id.power1: 
				items = R.array.power_ezren_2_1;
				if(powerData[6] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			case R.id.power3: 
				if(powerData[1] > 0){
					items = R.array.power_ezren_12_3;
					if(powerData[8] > 0){ preSelect = 4;}
					else if(powerData[7] > 0){ preSelect = 3;}
					else if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				} else{
					items = R.array.power_ezren_0_3;
					if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power4:
				if(powerData[1] == 1){ 
					items = R.array.power_ezren_1_4;
					if(powerData[9] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				else if(powerData[1] == 2){ 
					items = R.array.power_ezren_2_4;
					if(powerData[9] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power5:
				if(powerData[1] == 1){
					items = R.array.power_ezren_1_5;
					if(powerData[13] > 0){ preSelect = 2;}
					else if(powerData[12] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				else{
					items = R.array.power_ezren_2_5;
					if(powerData[11] > 0){ preSelect = 2;}
					else if(powerData[10] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			default: 
				items = R.array.power_ezren_2_6;
				if(powerData[13] > 0){ preSelect = 1;}
				else{ preSelect = 2;}
				break;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
			builder.setTitle(R.string.select_feat);
			builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					updatePowerEzren(id, which);
					dialog.dismiss();
					refresh();
				}				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};

	public void updatePowerEzren(int power, int which){
		switch(power){
		case R.id.power1:
			switch(which){
			case 0:
				db.updatePower(powers_id, 7, 0); break;
			case 1:
				db.updatePower(powers_id, 7, 1); break;
			} break;
		case R.id.power3:
			switch(which){
			case 0:
				db.updatePower(powers_id, 5, 0);
				db.updatePower(powers_id, 6, 0);
				db.updatePower(powers_id, 8, 0);
				db.updatePower(powers_id, 9, 0); break;
			case 1:
				db.updatePower(powers_id, 5, 1); 
				db.updatePower(powers_id, 6, 0);
				db.updatePower(powers_id, 8, 0);
				db.updatePower(powers_id, 9, 0); break;
			case 2:
				db.updatePower(powers_id, 5, 1); 
				db.updatePower(powers_id, 6, 1);
				db.updatePower(powers_id, 8, 0);
				db.updatePower(powers_id, 9, 0); break;
			case 3:
				db.updatePower(powers_id, 5, 1); 
				db.updatePower(powers_id, 6, 1);
				db.updatePower(powers_id, 8, 1);
				db.updatePower(powers_id, 9, 0); break;
			case 4:
				db.updatePower(powers_id, 5, 1); 
				db.updatePower(powers_id, 6, 1);
				db.updatePower(powers_id, 8, 1);
				db.updatePower(powers_id, 9, 1); break;
			} break;
		case R.id.power4:
			if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 10, 0); 
					db.updatePower(powers_id, 11, 0); 
					db.updatePower(powers_id, 12, 0);  break;
				case 1:
					db.updatePower(powers_id, 10, 1); 
					db.updatePower(powers_id, 11, 0); 
					db.updatePower(powers_id, 12, 0);  break;
				case 2:
					db.updatePower(powers_id, 11, 1);  break;
				case 3: 
					db.updatePower(powers_id, 12, 1); break;
				}
			} else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 10, 0); break;
				case 1:
					db.updatePower(powers_id, 10, 1); break;
				}
			} break;
		case R.id.power5:
			if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 13, 0);
					db.updatePower(powers_id, 14, 0);  break;
				case 1:
					db.updatePower(powers_id, 13, 1); break;
				case 2:
					db.updatePower(powers_id, 13, 1);
					db.updatePower(powers_id, 14, 1);  break;
				}
			} else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 11, 0);
					db.updatePower(powers_id, 12, 0);
					db.updatePower(powers_id, 13, 0); break;
				case 1:
					db.updatePower(powers_id, 11, 1); break;
				case 2:
					db.updatePower(powers_id, 11, 0);
					db.updatePower(powers_id, 12, 1); break;
				case 3:
					db.updatePower(powers_id, 13, 0); break;
				case 4:
					db.updatePower(powers_id, 13, 1); break;
				}
			} break;
		case R.id.power6:
			if(which == 0){ db.updatePower(powers_id, 14, 0);}
			else{ db.updatePower(powers_id, 14, 1);}
			break;
		}
	}
	
	private void addPowersHarsk(){	
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(40, 0, 0, 0);
		// Light Armors
		Button button = (Button) findViewById(R.id.prof_light);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		button.setOnClickListener(null);
		// Weapons
		button = (Button) findViewById(R.id.prof_weapon);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		button.setOnClickListener(null);
		View view = (View) findViewById(R.id.hr2);
		view.setVisibility(View.VISIBLE);
		
		// Set Class/Role Name
		String className = null;
		switch(powerData[1]){
		case 0:
			className = getString(R.string.class_harsk_base); break;
		case 1:
			className = getString(R.string.class_harsk_1);  break;
		case 2:
			className = getString(R.string.class_harsk_2);  break;
		}
		button = (Button) findViewById(R.id.name_and_class);
		button.setText(className);
		
		// Get Base Powers
		String power1 = getString(R.string.power_harsk_012_1_base_1);
		if (this.powerData[3] > 0){ power1 += " " + getString(R.string.power_harsk_012_1_upg_2);}
		else if (this.powerData[1] == 2 && this.powerData[7] > 0){ power1 += " " + getString(R.string.power_harsk_2_1_upg_6);}
		power1 += " " + getString(R.string.power_harsk_012_1_base_2);

		String power2 = getString(R.string.power_harsk_012_2_base_1);
		if(powerData[1] == 2 && powerData[8] > 0){ power2 += " " + getString(R.string.power_harsk_12_2_upg_67);}
		else if(powerData[1] == 1 && powerData[8] > 0){ power2 += " " + getString(R.string.power_harsk_1_2_upg_7);}
		else if(powerData[1] == 1 && powerData[7] > 0){ power2 += " " + getString(R.string.power_harsk_12_2_upg_67);}
		else if(powerData[5] > 0){ power2 += " " + getString(R.string.power_harsk_012_2_upg_4);}
		else if(powerData[4] > 0){ power2 += " " + getString(R.string.power_harsk_012_2_upg_3);}
		power2 += " " + getString(R.string.power_harsk_012_2_base_2);
		
		button = (Button) findViewById(R.id.power1);
		button.setVisibility(View.VISIBLE);
		button.setText(power1);
		button.setOnClickListener(har_listener);
		button = (Button) findViewById(R.id.power2);
		button.setVisibility(View.VISIBLE);
		button.setText(power2);
		button.setOnClickListener(har_listener);

		// Check for Role 1 - Sniper
		if(this.powerData[1] == 1){	
			//Power 3
			String power3 = getString(R.string.power_harsk_1_3_upg_8_base_1);
			if(this.powerData[10] > 0){ power3 += " " + getString(R.string.power_harsk_1_3_upg_9);}
			else{ power3 += " " + getString(R.string.power_harsk_1_3_upg_8_base_2);}
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(har_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[9] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			String power4 = getString(R.string.power_harsk_1_4_upg_10);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(har_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[11] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			String power5 = getString(R.string.power_harsk_12_5_upg_11);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(har_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[12] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			String power6 = getString(R.string.power_harsk_1_6_upg_12);
			button = (Button) findViewById(R.id.power6);
			button.setVisibility(View.VISIBLE);
			button.setText(power6);
			button.setOnClickListener(har_listener);
			view = (View) findViewById(R.id.hr8);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
		}
		// Check for Role 2 - Tracker
		else if(this.powerData[1] == 2) {
			// Power 3
			String power3 = getString(R.string.power_harsk_2_3_upg_8_base_1);
			if(powerData[10] > 0){ power3 += " " + getString(R.string.power_harsk_2_3_upg_9);}
			power3 += " " + getString(R.string.power_harsk_2_3_upg_8_base_2);
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(har_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[9] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);
			}
			
			// Power 4
			String power4 = getString(R.string.power_harsk_2_4_upg_10);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(har_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[11] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 5
			String power5 = getString(R.string.power_harsk_12_5_upg_11);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(har_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[12] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);
			}
			
			String power6 = getString(R.string.power_harsk_2_6_upg_12);
			button = (Button) findViewById(R.id.power6);
			button.setVisibility(View.VISIBLE);
			button.setText(power6);
			button.setOnClickListener(har_listener);
			view = (View) findViewById(R.id.hr8);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
		}
	}
	
	private OnClickListener har_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final int id = v.getId();
			int preSelect = 0;
			int items = 0;
			switch(id){
			case R.id.power1: 
				if(powerData[1] == 2){ items = R.array.power_harsk_2_1;}
				else{ items = R.array.power_harsk_01_1;}
				if(powerData[1] == 2 && powerData[7] > 0){ preSelect = 2;}
				else if(powerData[3] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			case R.id.power2: 
				if(powerData[1] == 0){ 
					items = R.array.power_harsk_0_2;
					if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				else if(powerData[1] == 1){ 
					items = R.array.power_harsk_1_2;
					if(powerData[8] > 0){ preSelect = 4;}
					else if(powerData[7] > 0){ preSelect = 3;}
					else if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				else{ 
					items = R.array.power_harsk_2_2;
					if(powerData[8] > 0){ preSelect = 3;}
					else if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power3: 
				if(powerData[1] == 2){ items = R.array.power_harsk_2_3;}
				else{ items = R.array.power_harsk_1_3;}
				if(powerData[10] > 0){ preSelect = 2;}
				else if(powerData[9] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			case R.id.power4:
				items = R.array.power_harsk_12_4;
				if(powerData[11] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			case R.id.power5:
				items = R.array.power_harsk_12_5;
				if(powerData[12] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			default: 
				items = R.array.power_harsk_12_6;
				if(powerData[13] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
			builder.setTitle(R.string.select_feat);
			builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					updatePowerHarsk(id, which);
					dialog.dismiss();
					refresh();
				}				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	private void updatePowerHarsk(int power, int which){
		switch(power){
		case R.id.power1: 
			switch(which){
			case 0: db.updatePower(powers_id, 4, 0); break;
			case 1: 
				db.updatePower(powers_id, 4, 1);
				if(powerData[1] == 2){ db.updatePower(powers_id, 8, 0);}
				break;
			case 2: db.updatePower(powers_id, 8, 1); break;
			} break;
		case R.id.power2: 
			switch(which){
			case 0: 
				db.updatePower(powers_id, 5, 0);
				db.updatePower(powers_id, 6, 0);
				if(powerData[1] == 1){ db.updatePower(powers_id, 8, 0);}
				db.updatePower(powers_id, 9, 0); break;
			case 1: 
				db.updatePower(powers_id, 5, 1);
				db.updatePower(powers_id, 6, 0);
				if(powerData[1] == 1){ db.updatePower(powers_id, 8, 0);}
				db.updatePower(powers_id, 9, 0); break;
			case 2: 
				db.updatePower(powers_id, 6, 1);
				if(powerData[1] == 1){ db.updatePower(powers_id, 8, 0);}
				db.updatePower(powers_id, 9, 0); break;
			case 3: 
				if(powerData[1] == 1){ 
					db.updatePower(powers_id, 8, 1);
					db.updatePower(powers_id, 9, 0);}
				else{ db.updatePower(powers_id, 9, 1);} break;
			case 4: 
				db.updatePower(powers_id, 9, 1); break;
			} break;
		case R.id.power3:
			switch(which){
			case 0:
				db.updatePower(powers_id, 10, 0);
				db.updatePower(powers_id, 11, 0); break;
			case 1:
				db.updatePower(powers_id, 10, 1);
				db.updatePower(powers_id, 11, 0); break;
			case 2:
				db.updatePower(powers_id, 11, 1); break;
			} break;
		case R.id.power4:
			switch(which){
			case 0:
				db.updatePower(powers_id, 12, 0); break;
			case 1:
				db.updatePower(powers_id, 12, 1); break;
			} break;
		case R.id.power5:
			switch(which){
			case 0:
				db.updatePower(powers_id, 13, 0);
				db.updateSkill(skills_id, 11, 0);
				db.updateSkill(skills_id, 12, 0); break;
			case 1:
				db.updatePower(powers_id, 13, 1);
				db.updateSkill(skills_id, 11, 5);
				db.updateSkill(skills_id, 12, 9); break;
			} break;
		case R.id.power6:
			switch(which){
			case 0:
				db.updatePower(powers_id, 14, 0); break;
			case 1:
				db.updatePower(powers_id, 14, 1); break;
			} break;
		}
	}
	
	private void addPowersKyra(){		
		// Set Proficiencies
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(40, 0, 0, 0);
		// Proficiencies Word
		TextView button = (TextView) findViewById(R.id.prof_word);
		button.setVisibility(View.VISIBLE);
		// Light Armors
		button = (Button) findViewById(R.id.prof_light);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		button.setOnClickListener(null);
		// Heavy Armors
		button = (Button) findViewById(R.id.prof_heavy);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		button.setOnClickListener(null);
		// Weapons
		button = (Button) findViewById(R.id.prof_weapon);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		if(this.powerData[16] == 0){ 
			button.setTextColor(Color.GRAY);
			button.setTypeface(null, Typeface.ITALIC);
			button.setTextSize(this.smallText);}
		View view = (View) findViewById(R.id.hr2);
		view.setVisibility(View.VISIBLE);
		
		// Set Class/Role Name
		String className = null;
		switch(powerData[1]){
		case 0:
			className = getString(R.string.class_kyra_base); break;
		case 1:
			className = getString(R.string.class_kyra_1);  break;
		case 2:
			className = getString(R.string.class_kyra_2);  break;
		}
		button = (Button) findViewById(R.id.name_and_class);
		button.setText(className);
		
		// Get Base Powers
		String power1 = getString(R.string.power_kyra_012_1_base_1);
		if (this.powerData[1] == 1 && this.powerData[8] > 0){ power1 += " " + getString(R.string.power_kyra_1_1_upg_7);}
		else if (this.powerData[4] > 0){ power1 += " " + getString(R.string.power_kyra_012_1_upg_3);}
		else {power1 += " " + getString(R.string.power_kyra_012_1_base_2);}
		power1 += " " + getString(R.string.power_kyra_012_1_base_3);
		if (this.powerData[1] == 1 && this.powerData[9] > 0){ power1 += " " + getString(R.string.power_kyra_1_1_upg_8);}
		else {power1 += " " + getString(R.string.power_kyra_02_1_base_4); }

		String power2 = getString(R.string.power_kyra_012_2_base_1);
		if (this.powerData[5] > 0){ power2 += " " + getString(R.string.power_kyra_012_2_upg_4);}
		power2 += " " + getString(R.string.power_kyra_012_2_base_2);
		if(this.powerData[1] == 2 && this.powerData[7] > 0){ power2 += " " + getString(R.string.power_kyra_2_2_upg_6);}
		power2 += " " + getString(R.string.power_kyra_012_2_base_3);
		
		button = (Button) findViewById(R.id.power1);
		button.setVisibility(View.VISIBLE);
		button.setText(power1);
		button.setOnClickListener(kyr_listener);
		button = (Button) findViewById(R.id.power2);
		button.setVisibility(View.VISIBLE);
		button.setText(power2);
		button.setOnClickListener(kyr_listener);

		// Check for Role 1 - Healer 
		if(this.powerData[1] == 1){	
			//Power 3
			String power3 = getString(R.string.power_kyra_1_3_upg_9_base_1);
			if(this.powerData[11] > 0){ power3 += " " + getString(R.string.power_kyra_1_3_upg_10);}
			else{ power3 += " " + getString(R.string.power_kyra_1_3_upg_9_base_2);}
			power3 += " " + getString(R.string.power_kyra_1_3_upg_9_base_3);
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(kyr_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[10] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			String power4 = getString(R.string.power_kyra_12_4_base_1);
			if(powerData[13] > 0){ power4 += " " + getString(R.string.power_kyra_1_4_upg_12);}
			power4 += " " + getString(R.string.power_kyra_12_4_base_2);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(kyr_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[12] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
		}
		// Check for Role 2 - Exorcist
		else if(this.powerData[1] == 2) {
			// Power 3
			String power3 = getString(R.string.power_kyra_2_3_base_1);
			if(powerData[9] > 0){ power3 += " " + getString(R.string.power_kyra_2_3_upg_8);}
			else{ power3 += " " + getString(R.string.power_kyra_2_3_base_2);}
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(kyr_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[8] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);
			}
			
			// Power 4
			String power4 = getString(R.string.power_kyra_12_4_base_1);
			if(powerData[12] > 0){ power4 += " " + getString(R.string.power_kyra_2_4_upg_10);}
			power4 += " " + getString(R.string.power_kyra_12_4_base_2);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(kyr_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[11] == 0){
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 5
			String power5 = getString(R.string.power_kyra_2_5_base_1);
			if(powerData[13] > 0){ power5 += " " + getString(R.string.power_kyra_2_5_upg_12);}
			power5 += " " + getString(R.string.power_kyra_2_5_base_2);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(kyr_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[12] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);
			}
		}
	}
	
	private OnClickListener kyr_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final int id = v.getId();
			int preSelect = 0;
			int items = 0;
			switch(id){
			case R.id.power1: 
				if(powerData[1] == 1){ items = R.array.power_kyra_1_1;}
				else{ items = R.array.power_kyra_02_1;}
				if(powerData[1] == 1 && powerData[8] > 0){ preSelect = 2;}
				else if(powerData[4] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			case R.id.power2: 
				if(powerData[1] == 2){ items = R.array.power_kyra_2_2;}
				else{ items = R.array.power_kyra_01_2;}
				if(powerData[1] == 2 && powerData[7] > 0){ preSelect = 2;}
				else if(powerData[5] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			case R.id.power3: 
				if(powerData[1] == 1){ items = R.array.power_kyra_1_3;
					if(powerData[11] > 0){ preSelect = 2;}
					else if(powerData[10] > 0){ preSelect = 1;}
					else{ preSelect = 0;}}
				else{ 
					items = R.array.power_kyra_2_3;
					if(powerData[9] > 0){ preSelect = 2;}
					else if(powerData[8] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}break;
			case R.id.power4:
				if(powerData[1] == 1){ items = R.array.power_kyra_1_4;
					if(powerData[13] > 0){ preSelect = 2;}
					else if(powerData[12] > 0){ preSelect = 1;}
					else{ preSelect = 0;}}
				else{ 
					items = R.array.power_kyra_2_4;
					if(powerData[11] > 0){ preSelect = 2;}
					else if(powerData[10] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			default: 
				items = R.array.power_kyra_2_5;
				if(powerData[13] > 0){ preSelect = 2;}
				else if(powerData[12] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
			builder.setTitle(R.string.select_feat);
			builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					updatePowerKyra(id, which);
					dialog.dismiss();
					refresh();
				}				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	private void updatePowerKyra(int power, int which){
		switch(power){
		case R.id.power1: 
			switch(which){
			case 0: 
				db.updatePower(powers_id, 5, 0); 
				if(powerData[1] == 1){ db.updatePower(powers_id, 9, 0);}
				break;
			case 1: 
				db.updatePower(powers_id, 5, 1); 
				if(powerData[1] == 1){ db.updatePower(powers_id, 9, 0);}
				break;
			case 2: 
				db.updatePower(powers_id, 5, 1); 
				db.updatePower(powers_id, 9, 1); break;
			case 3:
				db.updatePower(powers_id, 10, 0); break;
			case 4:
				db.updatePower(powers_id, 10, 1); break;
			} break;
		case R.id.power2: 
			switch(which){
			case 0: 
				db.updatePower(powers_id, 6, 0); 
				if(powerData[1] == 2){ db.updatePower(powers_id, 8, 0);} break;
			case 1:
				db.updatePower(powers_id, 6, 1);
				if(powerData[1] == 2){ db.updatePower(powers_id, 8, 0);} break;
			case 2: 
				db.updatePower(powers_id, 6, 1);
				db.updatePower(powers_id, 8, 1); break;
			} break;
		case R.id.power3:
			if(this.powerData[1] == 1){
				switch(which){
				case 0: 
					db.updatePower(powers_id, 11, 0);
					db.updatePower(powers_id, 12, 0); break;
				case 1:
					db.updatePower(powers_id, 11, 1);
					db.updatePower(powers_id, 12, 0); break;
				case 2:
					db.updatePower(powers_id, 11, 1);
					db.updatePower(powers_id, 12, 1); break;
				}
			}else{
				switch(which){
				case 0: 
					db.updatePower(powers_id, 9, 0);
					db.updatePower(powers_id, 10, 0); break;
				case 1:
					db.updatePower(powers_id, 9, 1);
					db.updatePower(powers_id, 10, 0); break;
				case 2:
					db.updatePower(powers_id, 9, 1);
					db.updatePower(powers_id, 10, 1); break;
				}
			} break;
		case R.id.power4:
			if(this.powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 13, 0);
					db.updatePower(powers_id, 14, 0); break;
				case 1:
					db.updatePower(powers_id, 13, 1);
					db.updatePower(powers_id, 14, 0); break;
				case 2:
					db.updatePower(powers_id, 13, 1);
					db.updatePower(powers_id, 14, 1); break;
				}
			} else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 11, 0);
					db.updatePower(powers_id, 12, 0); break;
				case 1:
					db.updatePower(powers_id, 11, 1);
					db.updatePower(powers_id, 12, 0); break;
				case 2:
					db.updatePower(powers_id, 11, 1);
					db.updatePower(powers_id, 12, 1); break;
				}
			} break;
		case R.id.power5:
			switch(which){
			case 0:
				db.updatePower(powers_id, 13, 0);
				db.updatePower(powers_id, 14, 0); break;
			case 1:
				db.updatePower(powers_id, 13, 1);
				db.updatePower(powers_id, 14, 0); break;
			case 2:
				db.updatePower(powers_id, 13, 1);
				db.updatePower(powers_id, 14, 1); break;
			}
			break;
		}
	}
	
	private void addPowersLem(){	
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(40, 0, 0, 0);
		// Light Armors
		Button button = (Button) findViewById(R.id.prof_light);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		if(this.powerData[14] == 0){ 
			button.setTextColor(Color.GRAY);
			button.setTypeface(null, Typeface.ITALIC);
			button.setTextSize(this.smallText);}
		// Weapons
		button = (Button) findViewById(R.id.prof_weapon);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		if(this.powerData[16] == 0){ 
			button.setTextColor(Color.GRAY);
			button.setTypeface(null, Typeface.ITALIC);
			button.setTextSize(this.smallText);}
		View view = (View) findViewById(R.id.hr2);
		view.setVisibility(View.VISIBLE);
		
		// Set Class/Role Name
		String className = null;
		switch(powerData[1]){
		case 0:
			className = getString(R.string.class_lem_base); break;
		case 1:
			className = getString(R.string.class_lem_1); break;
		case 2:
			className = getString(R.string.class_lem_2); break;
		}
		button = (Button) findViewById(R.id.name_and_class);
		button.setText(className);
				
		// Get Base Powers
		String power1 = getString(R.string.power_lem_012_1_base_1);
		if (this.powerData[1] == 1 && this.powerData[7] > 0){ power1 += " " + getString(R.string.power_lem_12_1_upg_67);}
		else if (this.powerData[1] == 2 && this.powerData[8] > 0){ power1 += " " + getString(R.string.power_lem_12_1_upg_67);}
		else if (this.powerData[5] > 0){ power1 += " " + getString(R.string.power_lem_012_1_upg_4);}
		else if (this.powerData[4] > 0){ power1 += " " + getString(R.string.power_lem_012_1_upg_3);}
		power1 += " " + getString(R.string.power_lem_012_1_base_2);
		if (this.powerData[1] == 1 && this.powerData[8] > 0){ power1 += " " + getString(R.string.power_lem_1_1_upg_7);}
		power1 += " " + getString(R.string.power_lem_012_1_base_3);

		String power2 = getString(R.string.power_lem_012_2_base_1);
		if (this.powerData[1] == 1 && this.powerData[9] > 0){ power2 += " " + getString(R.string.power_lem_1_2_upg_8);}
		power2 += " " + getString(R.string.power_lem_012_2_base_2);
		
		button = (Button) findViewById(R.id.power1);
		button.setVisibility(View.VISIBLE);
		button.setText(power1);
		button.setOnClickListener(lem_listener);
		button = (Button) findViewById(R.id.power2);
		button.setVisibility(View.VISIBLE);
		button.setText(power2);
		if(powerData[1] == 1){ button.setOnClickListener(lem_listener);}

		// Check for Role 1 - Virtuoso 
		if(this.powerData[1] == 1){	
			//Power 3
			String power3 = getString(R.string.power_lem_1_3_upg_9);
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(lem_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[10] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			String power4 = getString(R.string.power_lem_1_4_upg_10_base_1);
			if(this.powerData[12] > 0){ power4 += " " + getString(R.string.power_lem_1_4_upg_11);}
			power4 += " " + getString(R.string.power_lem_1_4_upg_10_base_2);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(lem_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[11] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 5
			String power5 = getString(R.string.power_lem_12_5_upg_12);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(lem_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
		}
		// Check for Role 2 - Charlatan
		else if(this.powerData[1] == 2) {
			// Power 3
			String power3 = getString(R.string.power_lem_2_3_upg_8_base_1);
			if(powerData[10] > 0){ power3 += " " + getString(R.string.power_lem_2_3_upg_9);}
			if(powerData[11] > 0){ power3 += " " + getString(R.string.power_lem_2_3_upg_10);}
			power3 += getString(R.string.power_lem_2_3_upg_8_base_2);
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(lem_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[9] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);
			}
			
			// Power 4
			String power4 = getString(R.string.power_lem_2_4_upg_11);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(lem_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[12] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 5
			String power5 = getString(R.string.power_lem_12_5_upg_12);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(lem_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
		}
	}
	
	private OnClickListener lem_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final int id = v.getId();
			int preSelect = 0;
			int items = 0;
			switch(id){
			case R.id.power1: 
				if(powerData[1] == 0){ items = R.array.power_lem_0_1;}
				else if(powerData[1] == 1){ items = R.array.power_lem_1_1;}
				else{ items = R.array.power_lem_2_1;}
				if(powerData[1] == 0){
					if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				else if(powerData[1] == 1){ 
					if(powerData[7] > 0){ preSelect = 3;}
					else if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				else{ 
					if(powerData[8] > 0){ preSelect = 3;}
					else if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power2: 
				items = R.array.power_lem_1_2;
				if(powerData[9] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			case R.id.power3: 
				if(powerData[1] == 1){ items = R.array.power_lem_1_3;
					if(powerData[10] > 0){ preSelect = 1;}
					else{ preSelect = 0;}}
				else{ 
					items = R.array.power_lem_2_3;
					if(powerData[11] > 0){ preSelect = 3;}
					else if(powerData[10] > 0){ preSelect = 2;}
					else if(powerData[9] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}break;
			case R.id.power4:
				if(powerData[1] == 1){ items = R.array.power_lem_1_4;
					if(powerData[12] > 0){ preSelect = 2;}
					else if(powerData[12] > 0){ preSelect = 1;}
					else{ preSelect = 0;}}
				else{ 
					items = R.array.power_lem_2_4;
					if(powerData[12] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			default: 
				items = R.array.power_lem_12_5;
				if(powerData[13] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
			builder.setTitle(R.string.select_feat);
			builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					updatePowerLem(id, which);
					dialog.dismiss();
					refresh();
				}				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	private void updatePowerLem(int power, int which){
		switch(power){
		case R.id.power1: 
			switch(which){
			case 0: 
				db.updatePower(powers_id, 5, 0);
				db.updatePower(powers_id, 6, 0);
				if(powerData[1] == 1){ db.updatePower(powers_id, 8, 0);}
				else{ db.updatePower(powers_id, 9, 0);}
				break;
			case 1: 
				db.updatePower(powers_id, 5, 1);
				db.updatePower(powers_id, 6, 0);
				if(powerData[1] == 1){ db.updatePower(powers_id, 8, 0);}
				else{ db.updatePower(powers_id, 9, 0);}
				break;
			case 2: 
				db.updatePower(powers_id, 5, 1);
				db.updatePower(powers_id, 6, 1);
				if(powerData[1] == 1){ db.updatePower(powers_id, 8, 0);}
				else{ db.updatePower(powers_id, 9, 0);}
				break;
			case 3: 
				db.updatePower(powers_id, 5, 1);
				db.updatePower(powers_id, 6, 1);
				if(powerData[1] == 1){ db.updatePower(powers_id, 8, 1);}
				else{ db.updatePower(powers_id, 9, 1);}
				break;
			case 4:
				db.updatePower(powers_id, 9, 0); break;
			case 5:
				db.updatePower(powers_id, 9, 1); break;
			} break;
		case R.id.power2: 
			switch(which){
			case 0: db.updatePower(powers_id, 10, 0); break;
			case 1: db.updatePower(powers_id, 10, 1); break;
			} break;
		case R.id.power3:
			if(this.powerData[1] == 1){
				switch(which){
				case 0: 
					db.updatePower(powers_id, 11, 0); break;
				case 1:
					db.updatePower(powers_id, 11, 1); break;
				}
			}else{
				switch(which){
				case 0: 
					db.updatePower(powers_id, 10, 0);
					db.updatePower(powers_id, 11, 0);
					db.updatePower(powers_id, 12, 0); break;
				case 1:
					db.updatePower(powers_id, 10, 1);
					db.updatePower(powers_id, 11, 0);
					db.updatePower(powers_id, 12, 0); break;
				case 2:
					db.updatePower(powers_id, 10, 1);
					db.updatePower(powers_id, 11, 1);
					db.updatePower(powers_id, 12, 0); break;
				case 3:
					db.updatePower(powers_id, 10, 1);
					db.updatePower(powers_id, 11, 1);
					db.updatePower(powers_id, 12, 1); break;
				}
			} break;
		case R.id.power4:
			if(this.powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 12, 0);
					db.updatePower(powers_id, 13, 0); break;
				case 1:
					db.updatePower(powers_id, 12, 1);
					db.updatePower(powers_id, 13, 0); break;
				case 2:
					db.updatePower(powers_id, 12, 1);
					db.updatePower(powers_id, 13, 1); break;
				}
			} else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 13, 0); break;
				case 1:
					db.updatePower(powers_id, 13, 1); break;
				}
			} break;
		case R.id.power5:
			switch(which){
			case 0:
				db.updatePower(powers_id, 14, 0); break;
			case 1: 
				db.updatePower(powers_id, 14, 1); break;
			}
			break;
		}
	}
	
	private void addPowersLini(){	
		// Set Proficiencies
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(40, 0, 0, 0);
		// Proficiencies Word
		TextView button = (TextView) findViewById(R.id.prof_word);
		button.setVisibility(View.VISIBLE);
		// Light Armors
		button = (Button) findViewById(R.id.prof_light);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		if(this.powerData[14] == 0){ 
			button.setTextColor(Color.GRAY);
			button.setTypeface(null, Typeface.ITALIC);
			button.setTextSize(this.smallText);}
		// Weapons
		button = (Button) findViewById(R.id.prof_weapon);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		if(this.powerData[16] == 0){ 
			button.setTextColor(Color.GRAY);
			button.setTypeface(null, Typeface.ITALIC);
			button.setTextSize(this.smallText);}
		View view = (View) findViewById(R.id.hr2);
		view.setVisibility(View.VISIBLE);
		
		// Set Class/Role Name
		String className = null;
		switch(powerData[1]){
		case 0:
			className = getString(R.string.class_lini_base); break;
		case 1:
			className = getString(R.string.class_lini_1); break;
		case 2:
			className = getString(R.string.class_lini_2); break;
		}
		button = (Button) findViewById(R.id.name_and_class);
		button.setText(className);
		
		// Get Base Powers
		String power1 = getString(R.string.power_lini_012_1);
		button = (Button) findViewById(R.id.power1);
		button.setVisibility(View.VISIBLE);
		button.setText(power1);

		String power2 = getString(R.string.power_lini_012_2_base_1);
		if(powerData[1] == 2){
			if(powerData[9] > 0){ power2 += " " + getString(R.string.power_lini_2_2_upg_8);}
			else if(powerData[8] > 0){ power2 += " " + getString(R.string.power_lini_12_2_upg_67);}
			else if(powerData[5] > 0){ power2 += " " + getString(R.string.power_lini_012_2_upg_4);}
			else if(powerData[4] > 0){ power2 += " " + getString(R.string.power_lini_012_2_upg_3);}
		}else if(powerData[1] == 1){
			if(powerData[7] > 0){ power2 += " " + getString(R.string.power_lini_12_2_upg_67);}
			else if(powerData[5] > 0){ power2 += " " + getString(R.string.power_lini_012_2_upg_4);}
			else if(powerData[4] > 0){ power2 += " " + getString(R.string.power_lini_012_2_upg_3);}
		}
		else if(powerData[5] > 0){ power2 += " " + getString(R.string.power_lini_012_2_upg_4);}
		else if(powerData[4] > 0){ power2 += " " + getString(R.string.power_lini_012_2_upg_3);}
		power2 += " " + getString(R.string.power_lini_012_2_base_2);
		button = (Button) findViewById(R.id.power2);
		button.setVisibility(View.VISIBLE);
		button.setText(power2);
		button.setOnClickListener(lin_listener);

		String power3 = getString(R.string.power_lini_012_3_base_1);
		if(powerData[1] == 1){
			if(powerData[9] > 0){ 
				power3 += " " + getString(R.string.power_lini_1_3_upg_8);
				if(powerData[10] > 0){ power3 += " " + getString(R.string.power_lini_1_3_upg_9);}
			}
			else if(powerData[8] > 0){ power3 += " " + getString(R.string.power_lini_1_3_upg_7);}			
		}
		power3 += " " + getString(R.string.power_lini_012_3_base_2);
		button = (Button) findViewById(R.id.power3);
		button.setVisibility(View.VISIBLE);
		button.setText(power3);
		if(powerData[1] == 1){ button.setOnClickListener(lin_listener);}
		view = (View) findViewById(R.id.hr5);
		view.setVisibility(View.VISIBLE);

		// Check for Role 1 - Shapeshifter
		if(this.powerData[1] == 1){
			//Power 4
			String power4 = getString(R.string.power_lini_1_4_upg_10_base_1);
			if(powerData[12] > 0){ power4 += " " + getString(R.string.power_lini_1_4_upg_11);}
			else{ power4 += " " + getString(R.string.power_lini_1_4_upg_10_base_2);}
			power4 += " " + getString(R.string.power_lini_1_4_upg_10_base_3);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(lin_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[11] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			//Power 5
			String power5 = getString(R.string.power_lini_12_56_upg_12);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(lin_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
		}
		// Check for Role 2 - Wild Warden
		else if(this.powerData[1] == 2) {	
			// Power 4
			String power4 = getString(R.string.power_lini_2_4_upg_9_base_1);
			if(powerData[11] > 0){ power4 += " " + getString(R.string.power_lini_2_4_upg_10);}
			else{ power4 += " " + getString(R.string.power_lini_2_4_upg_9_base_2);}
			power4 += " " + getString(R.string.power_lini_2_4_upg_9_base_3);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(lin_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[10] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 5
			String power5 = getString(R.string.power_lini_2_5_upg_11);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(lin_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[12] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 6
			String power6 = getString(R.string.power_lini_12_56_upg_12);
			button = (Button) findViewById(R.id.power6);
			button.setVisibility(View.VISIBLE);
			button.setText(power6);
			button.setOnClickListener(lin_listener);
			view = (View) findViewById(R.id.hr8);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
		}
	}
	
	private OnClickListener lin_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final int id = v.getId();
			int preSelect = 0;
			int items = 0;
			switch(id){
			case R.id.power2:
				if(powerData[1] == 2){
					items = R.array.power_lini_2_2;
					if(powerData[9] > 0){ preSelect = 4;}
					else if(powerData[8] > 0){ preSelect = 3;}
					else if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}else if(powerData[1] == 1){
					items = R.array.power_lini_1_2;
					if(powerData[7] > 0){ preSelect = 3;}
					else if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}else{
					items = R.array.power_lini_0_2;
					if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				} break;
			case R.id.power3: 
				items = R.array.power_lini_1_3;
				if(powerData[10] > 0){ preSelect = 3;}
				else if(powerData[9] > 0){ preSelect = 2;}
				else if(powerData[8] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			case R.id.power4:
				if(powerData[1] == 1){ 
					items = R.array.power_lini_1_4;
					if(powerData[12] > 0){ preSelect = 2;}
					else if(powerData[11] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				else if(powerData[1] == 2){ 
					items = R.array.power_lini_2_4;
					if(powerData[11] > 0){ preSelect = 2;}
					else if(powerData[10] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power5:
				if(powerData[1] == 1){
					items = R.array.power_lini_12_56;
					if(powerData[13] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				else{
					items = R.array.power_lini_2_5;
					if(powerData[12] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			default: 
				items = R.array.power_lini_12_56;
				if(powerData[13] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
			builder.setTitle(R.string.select_feat);
			builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					updatePowerLini(id, which);
					dialog.dismiss();
					refresh();
				}				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	public void updatePowerLini(int power, int which){
		switch(power){
		case R.id.power2:
			if(powerData[1] == 0){
				switch(which){
				case 0:
					db.updatePower(powers_id, 5, 0);
					db.updatePower(powers_id, 6, 0); break;
				case 1:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 0); break;
				case 2:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 1); break;
				}
			}else if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 5, 0);
					db.updatePower(powers_id, 6, 0);
					db.updatePower(powers_id, 8, 0); break;
				case 1:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 0);
					db.updatePower(powers_id, 8, 0); break;
				case 2:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 1);
					db.updatePower(powers_id, 8, 0); break;
				case 3:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 1);
					db.updatePower(powers_id, 8, 1); break;
				}
			}else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 5, 0);
					db.updatePower(powers_id, 6, 0);
					db.updatePower(powers_id, 9, 0);
					db.updatePower(powers_id, 10, 0); break;
				case 1:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 0);
					db.updatePower(powers_id, 9, 0);
					db.updatePower(powers_id, 10, 0); break;
				case 2:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 1);
					db.updatePower(powers_id, 9, 0);
					db.updatePower(powers_id, 10, 0); break;
				case 3:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 1);
					db.updatePower(powers_id, 9, 1);
					db.updatePower(powers_id, 10, 0); break;
				case 4:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 1);
					db.updatePower(powers_id, 9, 1);
					db.updatePower(powers_id, 10, 1); break;
				}
			}break;
		case R.id.power3:
			switch(which){
			case 0:
				db.updatePower(powers_id, 9, 0);
				db.updatePower(powers_id, 10, 0);
				db.updatePower(powers_id, 11, 0); break;
			case 1:
				db.updatePower(powers_id, 9, 1);
				db.updatePower(powers_id, 10, 0);
				db.updatePower(powers_id, 11, 0); break;
			case 2:
				db.updatePower(powers_id, 9, 1);
				db.updatePower(powers_id, 10, 1);
				db.updatePower(powers_id, 11, 0); break;
			case 3:
				db.updatePower(powers_id, 9, 1);
				db.updatePower(powers_id, 10, 1);
				db.updatePower(powers_id, 11, 1); break;
			}break;
		case R.id.power4:
			if(powerData[1] == 2){
				switch(which){
				case 0:
					db.updatePower(powers_id, 11, 0);
					db.updatePower(powers_id, 12, 0); break;
				case 1:
					db.updatePower(powers_id, 11, 1);
					db.updatePower(powers_id, 12, 0); break;
				case 2:
					db.updatePower(powers_id, 11, 1);
					db.updatePower(powers_id, 12, 1); break;
				}
			} else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 12, 0);
					db.updatePower(powers_id, 13, 0); break;
				case 1:
					db.updatePower(powers_id, 12, 1);
					db.updatePower(powers_id, 13, 0); break;
				case 2:
					db.updatePower(powers_id, 12, 1);
					db.updatePower(powers_id, 13, 1); break;
				}
			} break;
		case R.id.power5:
			if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 14, 0); break;
				case 1:
					db.updatePower(powers_id, 14, 1); break;
				}
			} else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 13, 0); break;
				case 1:
					db.updatePower(powers_id, 13, 1); break;
				}
			} break;
		case R.id.power6:
			if(which == 0){ db.updatePower(powers_id, 14, 0);}
			else{ db.updatePower(powers_id, 14, 1);}
			break;
		}
	}
	
	private void addPowersMerisiel(){	
		// Set Proficiencies
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(40, 0, 0, 0);
		// Proficiencies Word
		TextView button = (TextView) findViewById(R.id.prof_word);
		button.setVisibility(View.VISIBLE);
		// Light Armors
		button = (Button) findViewById(R.id.prof_light);
		button.setLayoutParams(params);
		button.setOnClickListener(null);
		button.setVisibility(View.VISIBLE);
		// Weapons
		button = (Button) findViewById(R.id.prof_weapon);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		if(this.powerData[16] == 0){ 
			button.setTextColor(Color.GRAY);
			button.setTypeface(null, Typeface.ITALIC);
			button.setTextSize(this.smallText);}
		View view = (View) findViewById(R.id.hr2);
		view.setVisibility(View.VISIBLE);
		
		// Set Class/Role Name
		String className = null;
		switch(powerData[1]){
		case 0:
			className = getString(R.string.class_merisiel_base); break;
		case 1:
			className = getString(R.string.class_merisiel_1); break;
		case 2:
			className = getString(R.string.class_merisiel_2); break;
		}
		button = (Button) findViewById(R.id.name_and_class);
		button.setText(className);
		
		// Get Base Powers
		String power1 = getString(R.string.power_merisiel_012_1_base_1);
		if(powerData[7] == 1){ power1 += " " + getString(R.string.power_merisiel_1_1_upg_6);}
		power1 += getString(R.string.power_merisiel_012_1_base_2);
		button = (Button) findViewById(R.id.power1);
		button.setVisibility(View.VISIBLE);
		button.setText(power1);
		if(this.powerData[1] == 1){ button.setOnClickListener(mer_listener);}
	
		String power2 = getString(R.string.power_merisiel_012_2_base_1);
		if(powerData[1] == 2){
			if(powerData[7] > 0){ power2 += " " + getString(R.string.power_merisiel_2_2_upg_6);}
			else if(powerData[6] > 0){ power2 += " " + getString(R.string.power_merisiel_12_2_upg_75);}
			else if(powerData[5] > 0){ power2 += " " + getString(R.string.power_merisiel_012_2_upg_4);}
			else if(powerData[4] > 0){ power2 += " " + getString(R.string.power_merisiel_012_2_upg_3);}
		}else if(powerData[1] == 1){
			if(powerData[8] > 0){ power2 += " " + getString(R.string.power_merisiel_12_2_upg_75);}
			else if(powerData[5] > 0){ power2 += " " + getString(R.string.power_merisiel_012_2_upg_4);}
			else if(powerData[4] > 0){ power2 += " " + getString(R.string.power_merisiel_012_2_upg_3);}
		}
		else if(powerData[5] > 0){ power2 += " " + getString(R.string.power_merisiel_012_2_upg_4);}
		else if(powerData[4] > 0){ power2 += " " + getString(R.string.power_merisiel_012_2_upg_3);}
		power2 += " " + getString(R.string.power_merisiel_012_2_base_2);
		button = (Button) findViewById(R.id.power2);
		button.setVisibility(View.VISIBLE);
		button.setText(power2);
		button.setOnClickListener(mer_listener);
	
		// Check for Role 1 - Acrobat
		if(this.powerData[1] == 1){
			String power3 = getString(R.string.power_merisiel_1_3_upg_8_base_1);
			if(powerData[10] > 0){ power3 += " " + getString(R.string.power_merisiel_1_3_upg_9);}
			power3 += getString(R.string.power_merisiel_1_3_upg_8_base_2);
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(mer_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[9] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
	
			//Power 4
			String power4 = getString(R.string.power_merisiel_1_4_upg_10_base_1);
			if(powerData[12] > 0){ power4 += " " + getString(R.string.power_merisiel_1_4_upg_11);}
			else{ power4 += " " + getString(R.string.power_merisiel_1_4_upg_10_base_2);}
			power4 += " " + getString(R.string.power_merisiel_1_4_upg_10_base_3);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(mer_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[11] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
	
			//Power 5
			String power5 = getString(R.string.power_merisiel_1_5_upg_12);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(mer_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
		}
		// Check for Role 2 - Thief
		else if(this.powerData[1] == 2) {	
			String power3 = getString(R.string.power_merisiel_2_3_upg_7);
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(mer_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[8] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
				
			// Power 4
			String power4 = getString(R.string.power_merisiel_2_4_upg_8_base_1);
			if(powerData[11] > 0){ power4 += " " + getString(R.string.power_merisiel_2_4_upg_10);}
			else if(powerData[10] > 0){ power4 += " " + getString(R.string.power_merisiel_2_4_upg_9);}
			else{ power4 += " " + getString(R.string.power_merisiel_2_4_upg_8_base_2);}
			power4 += " " + getString(R.string.power_merisiel_2_4_upg_8_base_3);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(mer_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[9] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 5
			String power5 = getString(R.string.power_merisiel_2_5_upg_11);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(mer_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[12] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 6
			String power6 = getString(R.string.power_merisiel_2_6_upg_12);
			button = (Button) findViewById(R.id.power6);
			button.setVisibility(View.VISIBLE);
			button.setText(power6);
			button.setOnClickListener(mer_listener);
			view = (View) findViewById(R.id.hr8);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
		}
	}

	private OnClickListener mer_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final int id = v.getId();
			int preSelect = 0;
			int items = 0;
			switch(id){
			case R.id.power1: 
				items = R.array.power_merisiel_1_1;
				if(powerData[7] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			case R.id.power2:
				if(powerData[1] == 2){
					items = R.array.power_merisiel_2_2;
					if(powerData[7] > 0){ preSelect = 4;}
					else if(powerData[6] > 0){ preSelect = 3;}
					else if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}else if(powerData[1] == 1){
					items = R.array.power_merisiel_1_2;
					if(powerData[8] > 0){ preSelect = 3;}
					else if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}else{
					items = R.array.power_merisiel_0_2;
					if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				} break;
			case R.id.power3: 
				if(powerData[1] == 1){
					items = R.array.power_merisiel_1_3;
					if(powerData[9] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				} else{
					items = R.array.power_merisiel_2_3;
					if(powerData[8] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power4:
				if(powerData[1] == 1){ 
					items = R.array.power_merisiel_1_4;
					if(powerData[12] > 0){ preSelect = 2;}
					else if(powerData[11] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				else if(powerData[1] == 2){ 
					items = R.array.power_merisiel_2_4;
					if(powerData[11] > 0){ preSelect = 3;}
					else if(powerData[10] > 0){ preSelect = 2;}
					else if(powerData[9] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power5:
				if(powerData[1] == 1){
					items = R.array.power_merisiel_1_5;
					if(powerData[13] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				else{
					items = R.array.power_merisiel_2_5;
					if(powerData[12] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			default: 
				items = R.array.power_merisiel_2_6;
				if(powerData[13] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
			builder.setTitle(R.string.select_feat);
			builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					updatePowerMerisiel(id, which);
					dialog.dismiss();
					refresh();
				}				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};

	public void updatePowerMerisiel(int power, int which){
		switch(power){
		case R.id.power1:
			switch(which){
			case 0:
				db.updatePower(powers_id, 8, 0); break;
			case 1:
				db.updatePower(powers_id, 8, 1); break;
			} break;
		case R.id.power2:
			if(powerData[1] == 0){
				switch(which){
				case 0:
					db.updatePower(powers_id, 5, 0);
					db.updatePower(powers_id, 6, 0); break;
				case 1:
					db.updatePower(powers_id, 5, 1);
				case 2:
					db.updatePower(powers_id, 6, 1); break;
				}
			}else if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 5, 0);
					db.updatePower(powers_id, 6, 0);
					db.updatePower(powers_id, 9, 0); break;
				case 1:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 0);
					db.updatePower(powers_id, 9, 0); break;
				case 2:
					db.updatePower(powers_id, 6, 1);
					db.updatePower(powers_id, 9, 0); break;
				case 3:
					db.updatePower(powers_id, 9, 1); break;
				}
			}else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 5, 0);
					db.updatePower(powers_id, 6, 0);
					db.updatePower(powers_id, 7, 0);
					db.updatePower(powers_id, 8, 0); break;
				case 1:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 0);
					db.updatePower(powers_id, 7, 0);
					db.updatePower(powers_id, 8, 0); break;
				case 2:
					db.updatePower(powers_id, 6, 1);
					db.updatePower(powers_id, 7, 0);
					db.updatePower(powers_id, 8, 0); break;
				case 3:
					db.updatePower(powers_id, 7, 1);
					db.updatePower(powers_id, 8, 0); break;
				case 4:
					db.updatePower(powers_id, 8, 1); break;
				}
			}break;
		case R.id.power3:
			if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 10, 0);
					db.updatePower(powers_id, 11, 0); break;
				case 1:
					db.updatePower(powers_id, 10, 1);
					db.updatePower(powers_id, 11, 0); break;
				case 2:
					db.updatePower(powers_id, 11, 1); break;
				}
			}else{
				switch(which){
				case 0: 
					db.updatePower(powers_id, 9, 0); break;
				case 1:
					db.updatePower(powers_id, 9, 1); break;
				}
			}break;
		case R.id.power4:
			if(powerData[1] == 2){
				switch(which){
				case 0:
					db.updatePower(powers_id, 10, 0);
					db.updatePower(powers_id, 11, 0);
					db.updatePower(powers_id, 12, 0); break;
				case 1:
					db.updatePower(powers_id, 10, 1);
					db.updatePower(powers_id, 11, 0);
					db.updatePower(powers_id, 12, 0); break;
				case 2:
					db.updatePower(powers_id, 10, 1);
					db.updatePower(powers_id, 11, 1);
					db.updatePower(powers_id, 12, 0); break;
				case 3:
					db.updatePower(powers_id, 10, 1);
					db.updatePower(powers_id, 11, 1);
					db.updatePower(powers_id, 12, 1); break;
				}
			} else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 12, 0);
					db.updatePower(powers_id, 13, 0); break;
				case 1:
					db.updatePower(powers_id, 12, 1);
					db.updatePower(powers_id, 13, 0); break;
				case 2:
					db.updatePower(powers_id, 13, 1); break;
				}
			} break;
		case R.id.power5:
			if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 14, 0); break;
				case 1:
					db.updatePower(powers_id, 14, 1); break;
				}
			} else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 13, 0); break;
				case 1:
					db.updatePower(powers_id, 13, 1); break;
				}
			} break;
		case R.id.power6:
			if(which == 0){ db.updatePower(powers_id, 14, 0);}
			else{ db.updatePower(powers_id, 14, 1);}
			break;
		}
	}
	
	private void addPowersSajan(){	
		// Set Proficiencies
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(40, 0, 0, 0);
		// Proficiencies Word
		TextView button = (TextView) findViewById(R.id.prof_word);
		if(powerData[1] > 0){ button.setVisibility(View.VISIBLE);}
		// Weapons
		button = (Button) findViewById(R.id.prof_weapon);
		button.setLayoutParams(params);
		View view = (View) findViewById(R.id.hr2);
		if(powerData[1] > 0){
			if(this.powerData[16] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			button.setVisibility(View.VISIBLE);
			view.setVisibility(View.VISIBLE);
		}
		
		// Set Class/Role Name
		String className = null;
		switch(powerData[1]){
		case 0:
			className = getString(R.string.class_sajan_base); break;
		case 1:
			className = getString(R.string.class_sajan_1); break;
		case 2:
			className = getString(R.string.class_sajan_2); break;
		}
		button = (Button) findViewById(R.id.name_and_class);
		button.setText(className);
		
		// Get Base Powers
		String power1 = getString(R.string.power_sajan_012_1_base_1);
		if(powerData[4] > 0){ power1 += " " + getString(R.string.power_sajan_012_1_upg_3);}
		if(powerData[5] > 0){ power1 += " " + getString(R.string.power_sajan_012_1_upg_4);}
		power1 += " " + getString(R.string.power_sajan_012_1_base_2);
		button = (Button) findViewById(R.id.power1);
		button.setVisibility(View.VISIBLE);
		button.setText(power1);
		button.setOnClickListener(saj_listener);

		String power2 = getString(R.string.power_sajan_012_2);
		button = (Button) findViewById(R.id.power2);
		button.setVisibility(View.VISIBLE);
		button.setText(power2);

		// Check for Role 1 - Drunken Master
		if(this.powerData[1] == 1){
			String power3 = getString(R.string.power_sajan_12_3_upg_7_base_1);
			if(powerData[10] > 0){ power3 += " " + getString(R.string.power_sajan_1_3_upg_9);}
			else if(powerData[9] > 0){ power3 += " " + getString(R.string.power_sajan_12_3_upg_8);}
			else{ power3 += " " + getString(R.string.power_sajan_12_3_upg_7_base_2);}
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(saj_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[8] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			//Power 4
			String power4 = getString(R.string.power_sajan_1_4_upg_10);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(saj_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[11] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 5
			String power5 = getString(R.string.power_sajan_1_5_upg_11);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(saj_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[12] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 6
			String power6 = getString(R.string.power_sajan_1_6_upg_12);
			button = (Button) findViewById(R.id.power6);
			button.setVisibility(View.VISIBLE);
			button.setText(power6);
			button.setOnClickListener(saj_listener);
			view = (View) findViewById(R.id.hr8);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
		}
		// Check for Role 2 - Zen Archer
		else if(this.powerData[1] == 2) {	
			String power3 = getString(R.string.power_sajan_12_3_upg_7_base_1);
			if(powerData[9] > 0){ power3 += " " + getString(R.string.power_sajan_12_3_upg_8);}
			else{ power3 += " " + getString(R.string.power_sajan_12_3_upg_7_base_2);}
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(saj_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[8] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			//Power 4
			String power4 = getString(R.string.power_sajan_2_4_upg_9);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(saj_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[10] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 5
			String power5 = getString(R.string.power_sajan_2_5_upg_10_base_1);
			if(powerData[12] > 0){ power5 += " " + getString(R.string.power_sajan_2_5_upg_11);}
			else{ power5 += " " + getString(R.string.power_sajan_2_5_upg_10_base_2);}
			power5 += " " + getString(R.string.power_sajan_2_5_upg_10_base_3);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(saj_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[11] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 6
			String power6 = getString(R.string.power_sajan_2_6_upg_12);
			button = (Button) findViewById(R.id.power6);
			button.setVisibility(View.VISIBLE);
			button.setText(power6);
			button.setOnClickListener(saj_listener);
			view = (View) findViewById(R.id.hr8);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
		}
	}
	
	private OnClickListener saj_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final int id = v.getId();
			int preSelect = 0;
			int items = 0;
			switch(id){
			case R.id.power1: 
				items = R.array.power_sajan_012_1;
				if(powerData[5] > 0){ preSelect = 2;}
				else if(powerData[4] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			case R.id.power3: 
				if(powerData[1] == 1){
					items = R.array.power_sajan_1_3;
					if(powerData[10] > 0){ preSelect = 3;}
					else if(powerData[9] > 0){ preSelect = 2;}
					else if(powerData[8] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				} else{
					items = R.array.power_sajan_2_3;
					if(powerData[9] > 0){ preSelect = 2;}
					else if(powerData[8] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power4:
				if(powerData[1] == 1){
					items = R.array.power_sajan_1_4;
					if(powerData[11] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}else{
					items = R.array.power_sajan_2_4;
					if(powerData[10] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power5:
				if(powerData[1] == 1){
					items = R.array.power_sajan_1_5;
					if(powerData[12] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}else{
					items = R.array.power_sajan_2_5;
					if(powerData[12] > 0){ preSelect = 2;}
					else if(powerData[11] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			default:
				if(powerData[1] == 1){ items = R.array.power_sajan_1_6;}
				else{ items = R.array.power_sajan_2_6;}
				if(powerData[13] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
			builder.setTitle(R.string.select_feat);
			builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					updatePowerSajan(id, which);
					dialog.dismiss();
					refresh();
				}				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	public void updatePowerSajan(int power, int which){
		switch(power){
		case R.id.power1:
			switch(which){
			case 0:
				db.updatePower(powers_id, 5, 0);
				db.updatePower(powers_id, 6, 0); break;
			case 1:
				db.updatePower(powers_id, 5, 1);
				db.updatePower(powers_id, 6, 0); break;
			case 2:
				db.updatePower(powers_id, 5, 1);
				db.updatePower(powers_id, 6, 1); break;
			}
			break;
		case R.id.power3:
			switch(which){
			case 0:
				db.updatePower(powers_id, 9, 0);
				db.updatePower(powers_id, 10, 0);
				if(powerData[1] == 1){ db.updatePower(powers_id, 11, 0);} break;
			case 1:
				db.updatePower(powers_id, 9, 1);
				db.updatePower(powers_id, 10, 0);
				if(powerData[1] == 1){ db.updatePower(powers_id, 11, 0);} break;
			case 2:
				db.updatePower(powers_id, 9, 1);
				db.updatePower(powers_id, 10, 1);
				if(powerData[1] == 1){ db.updatePower(powers_id, 11, 0);} break;
			case 3:
				db.updatePower(powers_id, 9, 1);
				db.updatePower(powers_id, 10, 1);
				db.updatePower(powers_id, 11, 1); break;
			}
			break;
		case R.id.power4:
			if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 12, 0); break;
				case 1:
					db.updatePower(powers_id, 12, 1);; break;
				}
			}else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 11, 0); break;
				case 1:
					db.updatePower(powers_id, 11, 1); break;
				}
			} break;
		case R.id.power5:
			if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 13, 0); break;
				case 1:
					db.updatePower(powers_id, 13, 1); break;
				}
			}else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 12, 0);
					db.updatePower(powers_id, 13, 0); break;
				case 1:
					db.updatePower(powers_id, 12, 1);
					db.updatePower(powers_id, 13, 0); break;
				case 2:
					db.updatePower(powers_id, 12, 1);
					db.updatePower(powers_id, 13, 1); break;
				}
			} break;
		case R.id.power6:
			switch(which){
			case 0:
				db.updatePower(powers_id, 14, 0); break;
			case 1:
				db.updatePower(powers_id, 14, 1); break;
			} 
			break;
		}
	}
	
	private void addPowersSeelah(){	
		// Set Proficiencies
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(40, 0, 0, 0);
		// Proficiencies Word
		TextView button = (TextView) findViewById(R.id.prof_word);
		button.setVisibility(View.VISIBLE);
		// Light Armors
		button = (Button) findViewById(R.id.prof_light);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		button.setOnClickListener(null);
		// Heavy Armors
		button = (Button) findViewById(R.id.prof_heavy);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		button.setOnClickListener(null);
		// Weapons
		button = (Button) findViewById(R.id.prof_weapon);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		button.setOnClickListener(null);
		View view = (View) findViewById(R.id.hr2);
		view.setVisibility(View.VISIBLE);
		
		// Set Class/Role Name
				String className = null;
				switch(powerData[1]){
				case 0:
					className = getString(R.string.class_seelah_base); break;
				case 1:
					className = getString(R.string.class_seelah_1); break;
				case 2:
					className = getString(R.string.class_seelah_2); break;
				}
				button = (Button) findViewById(R.id.name_and_class);
				button.setText(className);
		
		// Get Base Powers
		String power1 = getString(R.string.power_seelah_012_1_base_1);
		if(powerData[1] == 1){
			if(powerData[8] > 0){ power1 += " " + getString(R.string.power_seelah_12_1_upg_78);}
			else if(powerData[7] > 0){ power1 += " " + getString(R.string.power_seelah_12_1_upg_67);}
			else if(powerData[3] > 0){ power1 += " " + getString(R.string.power_seelah_012_1_upg_2);}
		}else if(powerData[1] == 2){
			if(powerData[9] > 0){ power1 += " " + getString(R.string.power_seelah_12_1_upg_78);}
			else if(powerData[8] > 0){ power1 += " " + getString(R.string.power_seelah_12_1_upg_67);}
			else if(powerData[3] > 0){ power1 += " " + getString(R.string.power_seelah_012_1_upg_2);}
		}else if(powerData[3] > 0){ power1 += " " + getString(R.string.power_seelah_012_1_upg_2);}
		power1 += " " + getString(R.string.power_seelah_012_1_base_2);
		if(powerData[4] > 0){ power1 += " " + getString(R.string.power_seelah_012_1_upg_3);}
		power1 += getString(R.string.power_seelah_012_1_base_3);
		button = (Button) findViewById(R.id.power1);
		button.setVisibility(View.VISIBLE);
		button.setText(power1);
		button.setOnClickListener(see_listener);

		String power2 = getString(R.string.power_seelah_012_2_base_1);
		if(powerData[5] > 0){ power2 += " " + getString(R.string.power_seelah_012_2_upg_4);}
		power2 += " " + getString(R.string.power_seelah_012_2_base_2);
		if(powerData[1] == 1){
			if(powerData[9] > 0){ power2 += " " + getString(R.string.power_seelah_12_2_upg_89);}
		}else if(powerData[1] == 2){
			if(powerData[10] > 0){ power2 += " " + getString(R.string.power_seelah_12_2_upg_89);}
		}
		power2 += " " + getString(R.string.power_seelah_012_2_base_3);
		button = (Button) findViewById(R.id.power2);
		button.setVisibility(View.VISIBLE);
		button.setText(power2);
		button.setOnClickListener(see_listener);

		// Check for Role 1 - Crusader
		if(this.powerData[1] == 1){
			String power3 = getString(R.string.power_seelah_1_3_upg_9);
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(see_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[10] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			//Power 4
			String power4 = getString(R.string.power_seelah_1_4_upg_10);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(see_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[11] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 5
			String power5 = getString(R.string.power_seelah_12_54_upg_11_base_1);
			if(powerData[13] > 0){ power5 += " " + getString(R.string.power_seelah_12_5_upg_12);}
			power5 += " " + getString(R.string.power_seelah_12_54_upg_11_base_2);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(see_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[12] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
		}
		// Check for Role 2 - Hospitaler
		else if(this.powerData[1] == 2) {	
			String power3 = getString(R.string.power_seelah_2_3_upg_10);
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(see_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[11] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			//Power 4
			String power4 = getString(R.string.power_seelah_12_54_upg_11_base_1);
			if(powerData[13] > 0){ power4 += " " + getString(R.string.power_seelah_12_5_upg_12);}
			power4 += " " + getString(R.string.power_seelah_12_54_upg_11_base_2);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(see_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[12] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
		}
	}
	
	private OnClickListener see_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final int id = v.getId();
			int preSelect = 0;
			int items = 0;
			switch(id){
			case R.id.power1: 
				if(powerData[1] == 0){
					items = R.array.power_seelah_0_1;
					if(powerData[3] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}else if(powerData[1] == 1){
					items = R.array.power_seelah_12_1;
					if(powerData[8] > 0){ preSelect = 3;}
					else if(powerData[7] > 0){ preSelect = 2;}
					else if(powerData[3] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}else{
					items = R.array.power_seelah_12_1;
					if(powerData[9] > 0){ preSelect = 3;}
					else if(powerData[8] > 0){ preSelect = 2;}
					else if(powerData[3] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power2:
				if(powerData[1] == 0){
					items = R.array.power_seelah_0_2;
					if(powerData[5] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}else{
					items = R.array.power_seelah_12_2;
					if(powerData[1] == 1 && powerData[9] > 0){ preSelect = 1;}
					if(powerData[1] == 2 && powerData[10] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power3: 
				if(powerData[1] == 1){
					items = R.array.power_seelah_1_3;
					if(powerData[10] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				} else{
					items = R.array.power_seelah_2_3;
					if(powerData[11] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power4:
				if(powerData[1] == 1){
					items = R.array.power_seelah_1_4;
					if(powerData[11] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				} else{
					items = R.array.power_seelah_12_54;
					if(powerData[13] > 0){ preSelect = 2;}
					else if(powerData[12] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			default:
				items = R.array.power_seelah_12_54;
				if(powerData[13] > 0){ preSelect = 2;}
				else if(powerData[12] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
			builder.setTitle(R.string.select_feat);
			builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					updatePowerSeelah(id, which);
					dialog.dismiss();
					refresh();
				}				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	public void updatePowerSeelah(int power, int which){
		switch(power){
		case R.id.power1:
			if(powerData[1] == 0){
				switch(which){
				case 0:
					db.updatePower(powers_id, 4, 0); break;
				case 1:
					db.updatePower(powers_id, 4, 1); break;
				case 2:
					db.updatePower(powers_id, 5, 0); break;
				case 3:
					db.updatePower(powers_id, 5, 1); break;
				}
			}else if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 4, 0);
					db.updatePower(powers_id, 8, 0);
					db.updatePower(powers_id, 9, 0); break;
				case 1:
					db.updatePower(powers_id, 4, 1);
					db.updatePower(powers_id, 8, 0);
					db.updatePower(powers_id, 9, 0); break;
				case 2:
					db.updatePower(powers_id, 4, 1);
					db.updatePower(powers_id, 8, 1);
					db.updatePower(powers_id, 9, 0); break;
				case 3:
					db.updatePower(powers_id, 4, 1);
					db.updatePower(powers_id, 8, 1);
					db.updatePower(powers_id, 9, 1); break;	
				case 4:
					db.updatePower(powers_id, 5, 0); break;
				case 5:
					db.updatePower(powers_id, 5, 1); break;
				}
			}else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 4, 0);
					db.updatePower(powers_id, 9, 0);
					db.updatePower(powers_id, 10, 0); break;
				case 1:
					db.updatePower(powers_id, 4, 1);
					db.updatePower(powers_id, 9, 0);
					db.updatePower(powers_id, 10, 0); break;
				case 2:
					db.updatePower(powers_id, 4, 1);
					db.updatePower(powers_id, 9, 1);
					db.updatePower(powers_id, 10, 0); break;
				case 3:
					db.updatePower(powers_id, 4, 1);
					db.updatePower(powers_id, 9, 1);
					db.updatePower(powers_id, 10, 1); break;
				case 4:
					db.updatePower(powers_id, 5, 0); break;
				case 5:
					db.updatePower(powers_id, 5, 1); break;
				}
			}
			break;
		case R.id.power2:
			if(powerData[1] == 0){
				switch(which){
				case 0:
					db.updatePower(powers_id, 6, 0); break;
				case 1:
					db.updatePower(powers_id, 6, 1); break;
				}				
			}else if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 6, 0); break;
				case 1:
					db.updatePower(powers_id, 6, 1); break;
				case 2:
					db.updatePower(powers_id, 10, 0); break;
				case 3:
					db.updatePower(powers_id, 10, 1); break;
				}
			}else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 6, 0); break;
				case 1:
					db.updatePower(powers_id, 6, 1); break;
				case 2:
					db.updatePower(powers_id, 11, 0); break;
				case 3:
					db.updatePower(powers_id, 11, 1); break;
				}
			}
			break;
		case R.id.power3:
			if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 11, 0); break;
				case 1:
					db.updatePower(powers_id, 11, 1); break;
				}
			}else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 12, 0); break;
				case 1:
					db.updatePower(powers_id, 12, 1); break;
				}
			}
			break;
		case R.id.power4:
			if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 12, 0); break;
				case 1:
					db.updatePower(powers_id, 12, 1); break;
				}
			}else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 13, 0);
					db.updatePower(powers_id, 14, 0); break;
				case 1:
					db.updatePower(powers_id, 13, 1);
					db.updatePower(powers_id, 14, 0); break;
				case 2:
					db.updatePower(powers_id, 13, 1);
					db.updatePower(powers_id, 14, 1); break;
				}
			}
			break;
		case R.id.power5:
			switch(which){
			case 0:
				db.updatePower(powers_id, 13, 0);
				db.updatePower(powers_id, 14, 0); break;
			case 1:
				db.updatePower(powers_id, 13, 1);
				db.updatePower(powers_id, 14, 0); break;
			case 2:
				db.updatePower(powers_id, 13, 1);
				db.updatePower(powers_id, 14, 1); break;
			}
			break;
		}
	}
	
	private void addPowersSeoni(){		
		// Get Base Powers
		String power1 = getString(R.string.power_seoni_0_1_base_1);
		if (this.powerData[7] > 0){ power1 += " " + getString(R.string.power_seoni_12_1_upg_6);}
		else if (this.powerData[6] > 0){ power1 += " " + getString(R.string.power_seoni_12_1_upg_5);}
		else if (this.powerData[4] > 0){ power1 += " " + getString(R.string.power_seoni_0_1_upg_3);}
		else if(this.powerData[3] > 0){ power1 += " " + getString(R.string.power_seoni_0_1_upg_2);}
		// If she has a role, alternate phrasing to 1st power
		if (this.powerData[1] > 0){ 
			power1 += " " + getString(R.string.power_seoni_1_1_base_2);
			if (this.powerData[8] > 0){ power1 += " " + getString(R.string.power_seoni_1_1_upg_7);}
			power1 += " " + getString(R.string.power_seoni_1_1_base_3);
		} else{
			power1 += " " + getString(R.string.power_seoni_1_1_base_2);
			power1 += " " + getString(R.string.power_seoni_1_1_base_3);
		}

		String power2 = getString(R.string.power_seoni_012_2_base_1);
		if (this.powerData[5] > 0){ power2 += " " + getString(R.string.power_seoni_012_2_upg_4);}
		power2 += " " + getString(R.string.power_seoni_012_2_base_2);
		
		Button button = (Button) findViewById(R.id.power1);
		button.setVisibility(View.VISIBLE);
		button.setText(power1);
		button.setOnClickListener(seo_listener);
		button = (Button) findViewById(R.id.power2);
		button.setVisibility(View.VISIBLE);
		button.setText(power2);
		button.setOnClickListener(seo_listener);
		
		// Set Class/Role Name
				String className = null;
				switch(powerData[1]){
				case 0:
					className = getString(R.string.class_seoni_base); break;
				case 1:
					className = getString(R.string.class_seoni_1); break;
				case 2:
					className = getString(R.string.class_seoni_2); break;
				}
				button = (Button) findViewById(R.id.name_and_class);
				button.setText(className);

		// Check for Role 1 - Abyssal Sorcerer 
		if(this.powerData[1] == 1){	
			//Power 3
			String power3 = getString(R.string.power_seoni_1_3_upg_8_base_1);
			if(this.powerData[10] > 0){ power3 += " " + getString(R.string.power_seoni_1_3_upg_9);}
			power3 += " " + getString(R.string.power_seoni_1_3_upg_8_base_2);
			if(this.powerData[11] > 0){ power3 += " " + getString(R.string.power_seoni_1_3_upg_10);}
			else{ power3 += " " + getString(R.string.power_seoni_1_3_upg_8_base_3);}
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(seo_listener);
			View view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[9] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			String power4 = getString(R.string.power_seoni_1_4_upg_11);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(seo_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[12] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			String power5 = getString(R.string.power_seoni_1_5_upg_12);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(seo_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
		}
		// Check for Role 2 - Celestial Sorcerer
		else if(this.powerData[1] == 2) {
			// Power 3
			String power3 = getString(R.string.power_seoni_2_3_upg_8);
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(seo_listener);
			View view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[9] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);
			}
			
			// Power 4
			String power4 = getString(R.string.power_seoni_2_4_upg_9_base_1);
			if(this.powerData[11] > 0){ power4 += " " + getString(R.string.power_seoni_2_4_upg_10);}
			power4 += " " + getString(R.string.power_seoni_2_4_upg_9_base_2);
			if(this.powerData[12] > 0){ power4 += " " + getString(R.string.power_seoni_2_4_upg_11);}
			else{ power4 += " " + getString(R.string.power_seoni_2_4_upg_9_base_3);}
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(seo_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[10] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 5
			String power5 = getString(R.string.power_seoni_2_5_upg_12);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(seo_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);
			}
		}
	}
	
	private OnClickListener seo_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final int id = v.getId();
			int preSelect = 0;
			int items = 0;
			switch(id){
			case R.id.power1: 
				if(powerData[1] > 0){ items = R.array.power_seoni_12_1;}
				else{ items = R.array.power_seoni_0_1_base;}
				if(powerData[7] > 0){ preSelect = 4;}
				else if(powerData[6] > 0){ preSelect = 3;}
				else if(powerData[4] > 0){ preSelect = 2;}
				else if(powerData[3] > 0){ preSelect = 1;}
				break;
			case R.id.power2: 
				items = R.array.power_seoni_012_2_base; 
				if(powerData[5] > 0){ preSelect = 1;}
				break;
			case R.id.power3: 
				if(powerData[1] == 1){ items = R.array.power_seoni_1_3;
					if(powerData[10] > 0){ preSelect = 2;}
					else if(powerData[9] > 0){ preSelect = 1;}
					else{ preSelect = 0;}}
				else{ 
					items = R.array.power_seoni_2_3;
					if(powerData[9] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}break;
			case R.id.power4:
				if(powerData[1] == 1){ items = R.array.power_seoni_1_4;
					if(powerData[12] > 0){ preSelect = 2;}
					else{ preSelect = 0;}}
				else{ 
					items = R.array.power_seoni_2_4;
					if(powerData[11] > 0){ preSelect = 2;}
					else if(powerData[10] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			default: 
				if(powerData[1] == 1){ items = R.array.power_seoni_1_5;}
				else{ items = R.array.power_seoni_2_5;}
				if(powerData[13] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
			builder.setTitle(R.string.select_feat);
			builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					updatePowerSeoni(id, which);
					dialog.dismiss();
					refresh();
				}				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	private void updatePowerSeoni(int power, int which){
		switch(power){
		case R.id.power1: 
			switch(which){
			case 0: 
				db.updatePower(powers_id, 4, 0);
				db.updatePower(powers_id, 5, 0);
				db.updatePower(powers_id, 7, 0);
				db.updatePower(powers_id, 8, 0); break;
			case 1: db.updatePower(powers_id, 4, 1); break;
			case 2: db.updatePower(powers_id, 5, 1); break;
			case 3: db.updatePower(powers_id, 7, 1); break;
			case 4: db.updatePower(powers_id, 8, 1); break;
			case 5:	db.updatePower(powers_id, 9, 0); break;
			case 6: db.updatePower(powers_id, 9, 1);} break;
		case R.id.power2: 
			switch(which){
			case 0: db.updatePower(powers_id, 6, 0); break;
			case 1: db.updatePower(powers_id, 6, 1); break;
			} break;
		case R.id.power3:
			if(this.powerData[1] == 1){
				switch(which){
				case 0: 
					db.updatePower(powers_id, 10, 0);
					db.updatePower(powers_id, 11, 0);
					db.updatePower(powers_id, 12, 0); break;
				case 1:
					db.updatePower(powers_id, 10, 1);
					db.updatePower(powers_id, 11, 0); break;
				case 2:
					db.updatePower(powers_id, 10, 1);
					db.updatePower(powers_id, 11, 1); break;
				case 3:
					db.updatePower(powers_id, 12, 0); break;
				case 4:
					db.updatePower(powers_id, 12, 1); break;
				}
			}else{
				switch(which){
				case 0: 
					db.updatePower(powers_id, 10, 0); break;
				case 1:
					db.updatePower(powers_id, 10, 1); break;
				}
			} break;
		case R.id.power4:
			if(this.powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 13, 0); break;
				case 1:
					db.updatePower(powers_id, 13, 1); break;
				}
			} else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 11, 0);
					db.updatePower(powers_id, 12, 0);
					db.updatePower(powers_id, 13, 0); break;
				case 1:
					db.updatePower(powers_id, 11, 1); break;
				case 2:
					db.updatePower(powers_id, 12, 1); break;
				case 3:
					db.updatePower(powers_id, 13, 0); break;
				case 4:
					db.updatePower(powers_id, 13, 1); break;
				}
			} break;
		case R.id.power5:
			switch(which){
			case 0:
				db.updatePower(powers_id, 14, 0); break;
			case 1:
				db.updatePower(powers_id, 14, 1); break;
			}
			break;
		}
	}

	private void addPowersValeros(){	
		// Set Proficiencies
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(40, 0, 0, 0);
		// Proficiencies Word
		TextView button = (TextView) findViewById(R.id.prof_word);
		button.setVisibility(View.VISIBLE);
		// Light Armors
		button = (Button) findViewById(R.id.prof_light);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		button.setOnClickListener(null);
		// Heavy Armors
		button = (Button) findViewById(R.id.prof_heavy);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		button.setOnClickListener(null);
		// Weapons
		button = (Button) findViewById(R.id.prof_weapon);
		button.setLayoutParams(params);
		button.setVisibility(View.VISIBLE);
		button.setOnClickListener(null);
		View view = (View) findViewById(R.id.hr2);
		view.setVisibility(View.VISIBLE);
		
		// Set Class/Role Name
				String className = null;
				switch(powerData[1]){
				case 0:
					className = getString(R.string.class_valeros_base); break;
				case 1:
					className = getString(R.string.class_valeros_1); break;
				case 2:
					className = getString(R.string.class_valeros_2); break;
				}
				button = (Button) findViewById(R.id.name_and_class);
				button.setText(className);
		
		// Get Base Powers
		String power1 = getString(R.string.power_valeros_012_1_base_1);
		if(powerData[1] == 1){
			if(powerData[7] > 0){ power1 += " " + getString(R.string.power_valeros_12_1_upg_65);}
			else if(powerData[5] > 0){ power1 += " " + getString(R.string.power_valeros_012_1_upg_4);}
			else if(powerData[4] > 0){ power1 += " " + getString(R.string.power_valeros_012_1_upg_3);}
		}else if(powerData[1] == 2){
			if(powerData[8] > 0){ power1 += " " + getString(R.string.power_valeros_2_1_upg_7);}
			else if(powerData[7] > 0){ power1 += " " + getString(R.string.power_valeros_2_1_upg_6);}
			else if(powerData[6] > 0){ power1 += " " + getString(R.string.power_valeros_12_1_upg_65);}
			else if(powerData[5] > 0){ power1 += " " + getString(R.string.power_valeros_012_1_upg_4);}
			else if(powerData[4] > 0){ power1 += " " + getString(R.string.power_valeros_012_1_upg_3);}
		}else{ 
			if(powerData[5] > 0){ power1 += " " + getString(R.string.power_valeros_012_1_upg_4);}
			else if(powerData[4] > 0){ power1 += " " + getString(R.string.power_valeros_012_1_upg_3);}}
		power1 += " " + getString(R.string.power_valeros_012_1_base_2);
		button = (Button) findViewById(R.id.power1);
		button.setVisibility(View.VISIBLE);
		button.setText(power1);
		button.setOnClickListener(val_listener);

		String power2 = getString(R.string.power_valeros_012_2_base_1);
		if(powerData[1] == 1 && powerData[8] > 0){ power2 += " " + getString(R.string.power_valeros_1_2_upg_7);}
		else{ power2 += " " + getString(R.string.power_valeros_012_2_base_2);}
		power2 += " " + getString(R.string.power_valeros_012_2_base_3);
		if(powerData[1] == 2 && powerData[9] > 0){ power2 += " " + getString(R.string.power_valeros_2_2_upg_8);}
		power2 += " " + getString(R.string.power_valeros_012_2_base_4);
		button = (Button) findViewById(R.id.power2);
		button.setVisibility(View.VISIBLE);
		button.setText(power2);
		if(powerData[1] > 0){ button.setOnClickListener(val_listener);}

		// Check for Role 1 - Guardian
		if(this.powerData[1] == 1){
			String power3 = getString(R.string.power_valeros_1_3_upg_8_base_1);
			if(powerData[10] > 0){ power3 += " " + getString(R.string.power_valeros_1_3_upg_9);}
			else{ power3 += " " + getString(R.string.power_valeros_1_3_upg_8_base_2);}
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(val_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[9] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			//Power 4
			String power4 = getString(R.string.power_valeros_1_4_upg_10_base_1);
			if(powerData[12] > 0){ power4 += " " + getString(R.string.power_valeros_1_4_upg_11);}
			else{ power4 += " " + getString(R.string.power_valeros_1_4_upg_10_base_2);}
			power4 += " " + getString(R.string.power_valeros_1_4_upg_10_base_3);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(val_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[11] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 5
			String power5 = getString(R.string.power_valeros_1_5_upg_12);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(val_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
		}
		// Check for Role 2 - Weapon Master
		else if(this.powerData[1] == 2) {	
			String power3 = getString(R.string.power_valeros_2_3_upg_9);
			button = (Button) findViewById(R.id.power3);
			button.setVisibility(View.VISIBLE);
			button.setText(power3);
			button.setOnClickListener(val_listener);
			view = (View) findViewById(R.id.hr5);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[10] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}

			//Power 4
			String power4 = getString(R.string.power_valeros_2_4_upg_10_base_1);
			if(powerData[12] > 0){ power4 += " " + getString(R.string.power_valeros_2_4_upg_11);}
			else{ power4 += " " + getString(R.string.power_valeros_2_4_upg_10_base_2);}
			power4 += " " + getString(R.string.power_valeros_2_4_upg_10_base_3);
			button = (Button) findViewById(R.id.power4);
			button.setVisibility(View.VISIBLE);
			button.setText(power4);
			button.setOnClickListener(val_listener);
			view = (View) findViewById(R.id.hr6);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[11] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
			
			// Power 5
			String power5 = getString(R.string.power_valeros_2_5_upg_12);
			button = (Button) findViewById(R.id.power5);
			button.setVisibility(View.VISIBLE);
			button.setText(power5);
			button.setOnClickListener(val_listener);
			view = (View) findViewById(R.id.hr7);
			view.setVisibility(View.VISIBLE);
			if(this.powerData[13] == 0){ 
				button.setTextColor(Color.GRAY);
				button.setTypeface(null, Typeface.ITALIC);
				button.setTextSize(this.smallText);}
		}
	}
	
	private OnClickListener val_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final int id = v.getId();
			int preSelect = 0;
			int items = 0;
			switch(id){
			case R.id.power1: 
				if(powerData[1] == 0){
					items = R.array.power_valeros_0_1;
					if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}else if(powerData[1] == 1){
					items = R.array.power_valeros_1_1;
					if(powerData[7] > 0){ preSelect = 3;}
					else if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}else{
					items = R.array.power_valeros_2_1;
					if(powerData[8] > 0){ preSelect = 5;}
					else if(powerData[7] > 0){ preSelect = 4;}
					else if(powerData[6] > 0){ preSelect = 3;}
					else if(powerData[5] > 0){ preSelect = 2;}
					else if(powerData[4] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power2:
				if(powerData[1] == 1){
					items = R.array.power_valeros_1_2;
					if(powerData[8] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}else{
					items = R.array.power_valeros_2_2;
					if(powerData[9] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power3: 
				if(powerData[1] == 1){
					items = R.array.power_valeros_1_3;
					if(powerData[10] > 0){ preSelect = 2;}
					else if(powerData[9] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				} else{
					items = R.array.power_valeros_2_3;
					if(powerData[10] > 0){ preSelect = 1;}
					else{ preSelect = 0;}
				}
				break;
			case R.id.power4:
				items = R.array.power_valeros_12_4;
				if(powerData[12] > 0){ preSelect = 2;}
				else if(powerData[11] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			default:
				items = R.array.power_valeros_12_5;
				if(powerData[13] > 0){ preSelect = 1;}
				else{ preSelect = 0;}
				break;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
			builder.setTitle(R.string.select_feat);
			builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					updatePowerValeros(id, which);
					dialog.dismiss();
					refresh();
				}				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	public void updatePowerValeros(int power, int which){
		switch(power){
		case R.id.power1:
			if(powerData[1] == 0){
				switch(which){
				case 0:
					db.updatePower(powers_id, 5, 0);
					db.updatePower(powers_id, 6, 0); break;
				case 1:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 0); break;
				case 2:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 1); break;
				}
			}else if(powerData[1] == 1){
				switch(which){
				case 0: 
					db.updatePower(powers_id, 5, 0);
					db.updatePower(powers_id, 6, 0);
					db.updatePower(powers_id, 8, 0); break;
				case 1:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 0);
					db.updatePower(powers_id, 8, 0); break;
				case 2:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 1);
					db.updatePower(powers_id, 8, 0); break;
				case 3:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 1);
					db.updatePower(powers_id, 8, 1); break;			
				}
			}else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 5, 0);
					db.updatePower(powers_id, 6, 0);
					db.updatePower(powers_id, 7, 0);
					db.updatePower(powers_id, 8, 0);
					db.updatePower(powers_id, 9, 0); break;
				case 1:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 0);
					db.updatePower(powers_id, 7, 0);
					db.updatePower(powers_id, 8, 0);
					db.updatePower(powers_id, 9, 0); break;
				case 2:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 1);
					db.updatePower(powers_id, 7, 0);
					db.updatePower(powers_id, 8, 0);
					db.updatePower(powers_id, 9, 0); break;
				case 3:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 1);
					db.updatePower(powers_id, 7, 1);
					db.updatePower(powers_id, 8, 0);
					db.updatePower(powers_id, 9, 0); break;
				case 4:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 1);
					db.updatePower(powers_id, 7, 1);
					db.updatePower(powers_id, 8, 1);
					db.updatePower(powers_id, 9, 0); break;
				case 5:
					db.updatePower(powers_id, 5, 1);
					db.updatePower(powers_id, 6, 1);
					db.updatePower(powers_id, 7, 1);
					db.updatePower(powers_id, 8, 1);
					db.updatePower(powers_id, 9, 1); break;
				}
			}
			break;
		case R.id.power2:
			if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 9, 0);
				case 1:
					db.updatePower(powers_id, 9, 1); break;
				}				
			}else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 10, 0); break;
				case 1:
					db.updatePower(powers_id, 10, 1); break;
				}
			}
			break;
		case R.id.power3:
			if(powerData[1] == 1){
				switch(which){
				case 0:
					db.updatePower(powers_id, 10, 0);
					db.updatePower(powers_id, 11, 0); break;
				case 1:
					db.updatePower(powers_id, 10, 1);
					db.updatePower(powers_id, 11, 0); break;
				case 2:
					db.updatePower(powers_id, 10, 1);
					db.updatePower(powers_id, 11, 1); break;
				}
			}else{
				switch(which){
				case 0:
					db.updatePower(powers_id, 11, 0); break;
				case 1:
					db.updatePower(powers_id, 11, 1); break;
				}
			}
			break;
		case R.id.power4:
			switch(which){
			case 0:
				db.updatePower(powers_id, 12, 0);
				db.updatePower(powers_id, 13, 0); break;
			case 1:
				db.updatePower(powers_id, 12, 1);
				db.updatePower(powers_id, 13, 0); break;
			case 2:
				db.updatePower(powers_id, 12, 1);
				db.updatePower(powers_id, 13, 1); break;
			}
			break;
		case R.id.power5:
			switch(which){
			case 0:
				db.updatePower(powers_id, 14, 0); break;
			case 1:
				db.updatePower(powers_id, 14, 1); break;
			} 
			break;
		}
	}
	
	public void setLightArmor(View v){
		int preSelect = 0;
		int items = R.array.prof_light;
		if(powerData[14] > 0){ preSelect = 1;}
		else{ preSelect = 0;}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
		builder.setTitle(R.string.select_prof);
		builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				db.updatePower(powers_id, 15, which); 
				dialog.dismiss();
				refresh();
			}				
		});	
		AlertDialog dialog = builder.create();
		dialog.show();		
	}
	
	public void setHeavyArmor(View v){
		int preSelect = 0;
		int items = R.array.prof_heavy;
		if(powerData[15] > 0){ preSelect = 1;}
		else{ preSelect = 0;}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
		builder.setTitle(R.string.select_prof);
		builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				db.updatePower(powers_id, 16, which); 
				dialog.dismiss();
				refresh();
			}				
		});	
		AlertDialog dialog = builder.create();
		dialog.show();		
	}
	
	public void setWeapon(View v){
		int preSelect = 0;
		int items = R.array.prof_weapon;
		if(powerData[16] > 0){ preSelect = 1;}
		else{ preSelect = 0;}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
		builder.setTitle(R.string.select_prof);
		builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				db.updatePower(powers_id, 17, which); 
				dialog.dismiss();
				refresh();
			}				
		});	
		AlertDialog dialog = builder.create();
		dialog.show();		
	}
	
	public void updateHandCount(View v){
		int items = 0;
		int preSelect = 0;
		if(this.char_id == 1){ 
			if(this.powerData[1] > 0){ items = R.array.four_thru_six;}
			else{ items = R.array.four_thru_five;}
			switch(this.powerData[0]){
			case 4: preSelect = 0; break;
			case 5: preSelect = 1; break;
			case 6: preSelect = 2; break;
			}
		}
		else if(this.char_id == 2){ 
			if(this.powerData[1] == 1){ items = R.array.six_thru_nine;}
			else{ items = R.array.six_thru_eight;}
			switch(this.powerData[0]){
			case 6: preSelect = 0; break;
			case 7: preSelect = 1; break;
			case 8: preSelect = 2; break;
			case 9: preSelect = 3; break;
			}
		}
		else if(this.char_id == 3){ 
			if(this.powerData[1] > 0){ items = R.array.five_thru_seven;}
			else{ items = R.array.five_thru_six;}
			switch(this.powerData[0]){
			case 5: preSelect = 0; break;
			case 6: preSelect = 1; break;
			case 7: preSelect = 2; break;
			}
		}
		else if(char_id == 4){ 
			if(this.powerData[1] == 2){ items = R.array.five_thru_seven;}
			else if(this.powerData[1] == 1){ items = R.array.five_thru_eight;}
			else{ items = R.array.five_thru_six;}	
			switch(this.powerData[0]){
			case 5: preSelect = 0; break;
			case 6: preSelect = 1; break;
			case 7: preSelect = 2; break;
			case 8: preSelect = 3; break;
			}		
		}
		else if(this.char_id == 5){ 
			if(this.powerData[1] == 2){	items = R.array.six_thru_eight;}
			else if(this.powerData[1] == 1){ items = R.array.six_thru_seven;}
			else{ items = R.array.six_thru_six;}
			switch(this.powerData[0]){
			case 6: preSelect = 0; break;
			case 7: preSelect = 1; break;
			case 8: preSelect = 2; break;
			}
		}
		else if(this.char_id == 6){ 
			if(this.powerData[1] == 2){ items = R.array.five_thru_seven;}
			else if(this.powerData[1] == 1){ items = R.array.five_thru_six;}
			else{ items = R.array.five_thru_five;}
			switch(this.powerData[0]){
			case 5: preSelect = 0; break;
			case 6: preSelect = 1; break;
			case 7: preSelect = 2; break;
			}
		}
		else if(this.char_id == 7){ 
			if(this.powerData[1] == 1){ items = R.array.five_thru_seven;}
			else{ items = R.array.five_thru_six;}
			switch(this.powerData[0]){
			case 5: preSelect = 0; break;
			case 6: preSelect = 1; break;
			case 7: preSelect = 2; break;
			}
		}
		else if(this.char_id == 8){ 
			if(this.powerData[1] > 0){ items = R.array.four_thru_seven;}
			else{ items = R.array.four_thru_six;}
			switch(this.powerData[0]){
			case 4: preSelect = 0; break;
			case 5: preSelect = 1; break;
			case 6: preSelect = 2; break;
			case 7: preSelect = 3; break;
			}
		}
		else if(this.char_id == 9){ 
			if(this.powerData[1] == 2){ items = R.array.four_thru_seven;}
			else if(this.powerData[1] == 1){ items = R.array.four_thru_six;}
			else{ items = R.array.four_thru_five;}
			switch(this.powerData[0]){
			case 4: preSelect = 0; break;
			case 5: preSelect = 1; break;
			case 6: preSelect = 2; break;
			case 7: preSelect = 3; break;
			}
		}
		else if(this.char_id == 10){ 
			items = R.array.six_thru_seven;
			switch(this.powerData[0]){
			case 6: preSelect = 0; break;
			case 7: preSelect = 1; break;
			}
		}
		else if(this.char_id == 11){ 
			if(this.powerData[1] == 1){ items = R.array.four_thru_seven;}
			else{ items = R.array.four_thru_six;}
			switch(this.powerData[0]){
			case 4: preSelect = 0; break;
			case 5: preSelect = 1; break;
			case 6: preSelect = 2; break;
			case 7: preSelect = 3; break;
			}
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(PowersActivity.this);
		builder.setTitle(R.string.select_hand_size);
		builder.setSingleChoiceItems(items, preSelect, new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(char_id == 1){ 
					switch(which){
					case 0: db.updatePower(powers_id, 1, 4); break;
					case 1: db.updatePower(powers_id, 1, 5); break;
					case 2: db.updatePower(powers_id, 1, 6); break;
					}
				}
				else if(char_id == 2){ 
					switch(which){
					case 0: db.updatePower(powers_id, 1, 6); break;
					case 1: db.updatePower(powers_id, 1, 7); break;
					case 2: db.updatePower(powers_id, 1, 8); break;
					case 3: db.updatePower(powers_id, 1, 9); break;
					}
				}
				else if(char_id == 3){ 
					switch(which){
					case 0: db.updatePower(powers_id, 1, 5); break;
					case 1: db.updatePower(powers_id, 1, 6); break;
					case 2: db.updatePower(powers_id, 1, 7); break;
					}
				}
				else if(char_id == 4){ 
					switch(which){
					case 0: db.updatePower(powers_id, 1, 5); break;
					case 1: db.updatePower(powers_id, 1, 6); break;
					case 2: db.updatePower(powers_id, 1, 7); break;
					case 3: db.updatePower(powers_id, 1, 8); break;
					}
				}
				else if(char_id == 5){ 
					switch(which){
					case 0: db.updatePower(powers_id, 1, 6); break;
					case 1: db.updatePower(powers_id, 1, 7); break;
					case 2: db.updatePower(powers_id, 1, 8); break;
					}
				}
				else if(char_id == 6){ 
					switch(which){
					case 0: db.updatePower(powers_id, 1, 5); break;
					case 1: db.updatePower(powers_id, 1, 6); break;
					case 2: db.updatePower(powers_id, 1, 7); break;
					}
				}
				else if(char_id == 7){ 
					switch(which){
					case 0: db.updatePower(powers_id, 1, 5); break;
					case 1: db.updatePower(powers_id, 1, 6); break;
					case 2: db.updatePower(powers_id, 1, 7); break;
					}
				}
				else if(char_id == 8){ 
					switch(which){
					case 0: db.updatePower(powers_id, 1, 4); break;
					case 1: db.updatePower(powers_id, 1, 5); break;
					case 2: db.updatePower(powers_id, 1, 6); break;
					case 3: db.updatePower(powers_id, 1, 7); break;
					}
				}
				else if(char_id == 9){ 
					switch(which){
					case 0: db.updatePower(powers_id, 1, 4); break;
					case 1: db.updatePower(powers_id, 1, 5); break;
					case 2: db.updatePower(powers_id, 1, 6); break;
					case 3: db.updatePower(powers_id, 1, 7); break;
					}
				}
				else if(char_id == 10){ 
					switch(which){
					case 0: db.updatePower(powers_id, 1, 6); break;
					case 1: db.updatePower(powers_id, 1, 7); break;
					}
				}
				else if(char_id == 11){ 
					switch(which){
					case 0: db.updatePower(powers_id, 1, 4); break;
					case 1: db.updatePower(powers_id, 1, 5); break;
					case 2: db.updatePower(powers_id, 1, 6); break;
					case 3: db.updatePower(powers_id, 1, 7); break;
					}
				}
				dialog.dismiss();
				refresh();
			}				
		});	
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public void refresh(){
		Intent intent = getIntent();
		overridePendingTransition(0, 0);
	    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();
		overridePendingTransition(0, 0);
		startActivity(intent);
	}

}
