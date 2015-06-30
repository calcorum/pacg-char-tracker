package com.corum.manticore.pacgcharactertracker;

import com.corum.manticore.sqlite.helper.DatabaseHelper;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ScenarioActivity extends Activity {
	private DatabaseHelper db;
	private int[] scenario;
	private int scenario_id;
	private String character_name;
	private int adventurer_id;
	private int[] scenList = {R.id.scenario_0_1,R.id.scenario_0_2,R.id.scenario_0_3,R.id.scenario_1_1,R.id.scenario_1_2,R.id.scenario_1_3,R.id.scenario_1_4,
			R.id.scenario_1_5,R.id.scenario_2_1,R.id.scenario_2_2,R.id.scenario_2_3,R.id.scenario_2_4,R.id.scenario_2_5,R.id.scenario_3_1,R.id.scenario_3_2,
			R.id.scenario_3_3,R.id.scenario_3_4,R.id.scenario_3_5,R.id.scenario_4_1,R.id.scenario_4_2,R.id.scenario_4_3,R.id.scenario_4_4,R.id.scenario_4_5,
			R.id.scenario_5_1,R.id.scenario_5_2,R.id.scenario_5_3,R.id.scenario_5_4,R.id.scenario_5_5,R.id.scenario_6_1,R.id.scenario_6_2,R.id.scenario_6_3,
			R.id.scenario_6_4,R.id.scenario_6_5};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new DatabaseHelper(getApplicationContext());
		setContentView(R.layout.activity_scenario);
		
		Intent intent = getIntent();
		this.scenario_id = intent.getIntExtra("SCEN_ID", 0);
		this.adventurer_id = intent.getIntExtra("ADVENTURER_ID", 0);
		this.character_name = intent.getStringExtra("CHAR_NAME");
		this.scenario = db.getScenarios(this.scenario_id).getScenarios();
		
		setTitle(this.character_name + " - Scenarios");
		
		setBoxes();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scenario, menu);
		return true;
	}
	
	@Override
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
	
	public void setBoxes(){
		for(int i = 0; i < 33; i++){
			final CheckBox box = (CheckBox) findViewById(scenList[i]);
			if(scenario[i] == 1){ box.setChecked(true);}
			box.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
					int scenNum = 0;
					switch(box.getId()){
					case R.id.scenario_0_1: 
						scenNum = 0;
						break;
					case R.id.scenario_0_2:   
						scenNum = 1;
						break;
					case R.id.scenario_0_3:  
						scenNum = 2;
						break; 
					case R.id.scenario_1_1:  
						scenNum = 3;
						break; 
					case R.id.scenario_1_2:  
						scenNum = 4;
						break; 
					case R.id.scenario_1_3:  
						scenNum = 5;
						break; 
					case R.id.scenario_1_4:  
						scenNum = 6;
						break; 
					case R.id.scenario_1_5:  
						scenNum = 7;
						break; 
					case R.id.scenario_2_1:  
						scenNum = 8;
						break; 
					case R.id.scenario_2_2:  
						scenNum = 9;
						break; 
					case R.id.scenario_2_3:  
						scenNum = 10;
						break; 
					case R.id.scenario_2_4:  
						scenNum = 11;
						break; 
					case R.id.scenario_2_5:  
						scenNum = 12;
						break; 
					case R.id.scenario_3_1:  
						scenNum = 13;
						break; 
					case R.id.scenario_3_2:  
						scenNum = 14;
						break; 
					case R.id.scenario_3_3:  
						scenNum = 15;
						break; 
					case R.id.scenario_3_4:  
						scenNum = 16;
						break; 
					case R.id.scenario_3_5:  
						scenNum = 17;
						break; 
					case R.id.scenario_4_1:  
						scenNum = 18;
						break; 
					case R.id.scenario_4_2:  
						scenNum = 19;
						break; 
					case R.id.scenario_4_3:  
						scenNum = 20;
						break; 
					case R.id.scenario_4_4:  
						scenNum = 21;
						break; 
					case R.id.scenario_4_5:  
						scenNum = 22;
						break; 
					case R.id.scenario_5_1:  
						scenNum = 23;
						break; 
					case R.id.scenario_5_2:  
						scenNum = 24;
						break; 
					case R.id.scenario_5_3:  
						scenNum = 25;
						break; 
					case R.id.scenario_5_4:  
						scenNum = 26;
						break; 
					case R.id.scenario_5_5:  
						scenNum = 27;
						break; 
					case R.id.scenario_6_1:  
						scenNum = 28;
						break; 
					case R.id.scenario_6_2:  
						scenNum = 29;
						break; 
					case R.id.scenario_6_3:  
						scenNum = 30;
						break; 
					case R.id.scenario_6_4:  
						scenNum = 31;
						break; 
					case R.id.scenario_6_5:  
						scenNum = 32;
						break;
					}
					if(isChecked){ db.updateScenario(scenario_id, scenNum, 1);}
					else{ db.updateScenario(scenario_id, scenNum, 0);}
				}
			});
		}
	}

}
