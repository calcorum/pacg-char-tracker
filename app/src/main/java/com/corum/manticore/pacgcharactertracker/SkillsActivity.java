package com.corum.manticore.pacgcharactertracker;

import com.corum.manticore.sqlite.helper.DatabaseHelper;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class SkillsActivity extends Activity {
	DatabaseHelper db;
	int adventurer_id;
	int skills_id;
	int[] skillData;
	String charName;
	private int[] skillUpgrades = new int[6];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new DatabaseHelper(getApplicationContext());
		setContentView(R.layout.activity_skills);
		
		Intent intent = getIntent();
		this.adventurer_id = intent.getIntExtra("ADVENTURER_ID", 0);
		this.skills_id = intent.getIntExtra("SKILLS_ID", 0);
		this.charName = intent.getStringExtra("CHAR_NAME");
		this.skillData = db.getSkills(this.skills_id).getSkills();
		getSkillUpgrades(db.getAdventurer(adventurer_id).getCharacterId());
		
		for (int i = 0; i < 6; i ++){
			addDie(i, this.skillData[i], this.skillData[i + 16]);	
		}
		addBonuses();
		setTitle(this.charName + " - Skills");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.skills, menu);
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
	
	public void addDie(int i, int die, int bonus){
		Button view;
		switch(i){
		case 0: view = (Button)findViewById(R.id.str_skill); break;
		case 1: view = (Button)findViewById(R.id.dex_skill); break;
		case 2: view = (Button)findViewById(R.id.con_skill); break;
		case 3: view = (Button)findViewById(R.id.int_skill); break;
		case 4: view = (Button)findViewById(R.id.wis_skill); break;
		default: view = (Button)findViewById(R.id.cha_skill); break;
		}
		view.setText(getDie(die, bonus));
	}
	
	public String getDie(int skill, int bonus){
		switch(skill){
			case 4: return getString(R.string.die_4) + " " + getDieBonus(bonus);
			case 6: return getString(R.string.die_6) + " " + getDieBonus(bonus);
			case 8: return getString(R.string.die_8) + " " + getDieBonus(bonus);
			case 10: return getString(R.string.die_10) + " " + getDieBonus(bonus);
			default: return getString(R.string.die_12) + " " + getDieBonus(bonus);
		}
	}
	
	public String getDieBonus(int bonus){
		switch(bonus){
		case 0: return "";
		case 1: return getString(R.string.plus_1);
		case 2: return getString(R.string.plus_2);
		case 3: return getString(R.string.plus_3);
		case 4: return getString(R.string.plus_4);
		default: return "";
		}
	}
	
	public void addBonuses(){
		int locations[] = {this.skillData[6], this.skillData[7], this.skillData[8], this.skillData[9], this.skillData[10]};
		int bonuses[] = {this.skillData[11], this.skillData[12], this.skillData[13], this.skillData[14], this.skillData[15]};
		int total;
		if(locations[0] == 0){ total = 0;}
		else if(locations[1] == 0){ total = 1;}
		else if(locations[2] == 0){ total = 2;}
		else if(locations[3] == 0){ total = 3;}
		else if(locations[4] == 0){ total = 4;}
		else{ total = 5;}
		
		for (int i=0; i<total;i++){
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.setMargins(20, -10, 0, 0);
			LinearLayout layout = null;
			Button button = null;

			switch(locations[i]){
				case 1: 
					layout = (LinearLayout)findViewById(R.id.str_bonus);
					button = new Button(this);
					button.setText(getBonusInfo(bonuses[i]));
					button.setTextSize(20);
					button.setBackgroundColor(Color.TRANSPARENT);
					button.setLayoutParams(params);
					layout.addView(button);	break;
				case 2: 
					layout = (LinearLayout)findViewById(R.id.dex_bonus);
					button = new Button(this);
					button.setText(getBonusInfo(bonuses[i]));
					button.setTextSize(20);
					button.setBackgroundColor(Color.TRANSPARENT);
					button.setLayoutParams(params);
					layout.addView(button);	break;		
				case 3: 
					layout = (LinearLayout)findViewById(R.id.con_bonus);
					button = new Button(this);
					button.setText(getBonusInfo(bonuses[i]));
					button.setTextSize(20);
					button.setBackgroundColor(Color.TRANSPARENT);
					button.setLayoutParams(params);
					layout.addView(button);	break;			
				case 4: 
					layout = (LinearLayout)findViewById(R.id.int_bonus);
					button = new Button(this);
					button.setText(getBonusInfo(bonuses[i]));
					button.setTextSize(20);
					button.setBackgroundColor(Color.TRANSPARENT);
					button.setLayoutParams(params);
					layout.addView(button);	break;		
				case 5: 
					layout = (LinearLayout)findViewById(R.id.wis_bonus);
					button = new Button(this);
					button.setText(getBonusInfo(bonuses[i]));
					button.setTextSize(20);
					button.setBackgroundColor(Color.TRANSPARENT);
					button.setLayoutParams(params);
					layout.addView(button);	break;			
				case 6: 
					layout = (LinearLayout)findViewById(R.id.cha_bonus);
					button = new Button(this);
					button.setText(getBonusInfo(bonuses[i]));	
					button.setTextSize(20);
					button.setBackgroundColor(Color.TRANSPARENT);
					button.setLayoutParams(params);
					layout.addView(button);	break;			
			}
		}
	}
	
	public String getBonusInfo(int code){
		switch(code){
		case 1: return getString(R.string.bonus_acr01);
		case 2: return getString(R.string.bonus_arc01);
		case 3: return getString(R.string.bonus_arc02);
		case 4: return getString(R.string.bonus_arc03);
		case 5: return getString(R.string.bonus_dip01);
		case 6: return getString(R.string.bonus_dip02);
		case 7: return getString(R.string.bonus_dis01);
		case 8: return getString(R.string.bonus_div01);
		case 9: return getString(R.string.bonus_div02);
		case 10: return getString(R.string.bonus_div03);
		case 11: return getString(R.string.bonus_for01);
		case 12: return getString(R.string.bonus_for02);
		case 13: return getString(R.string.bonus_kno01);
		case 14: return getString(R.string.bonus_kno02);
		case 15: return getString(R.string.bonus_mel01);
		case 16: return getString(R.string.bonus_mel02);
		case 17: return getString(R.string.bonus_per01);
		case 18: return getString(R.string.bonus_ran01);
		case 19: return getString(R.string.bonus_ste01);
		case 20: return getString(R.string.bonus_sur01);
		case 21: return getString(R.string.bonus_sur02);
		}
		return "";
	}
	
	public void getSkillUpgrades(int char_id){
		if(char_id == 1){ 
			this.skillUpgrades[0] = 4;
			this.skillUpgrades[1] = 3;
			this.skillUpgrades[2] = 4;
			this.skillUpgrades[3] = 1;
			this.skillUpgrades[4] = 1;
			this.skillUpgrades[5] = 2;
		}
		else if(char_id == 2){ 
			this.skillUpgrades[0] = 1;
			this.skillUpgrades[1] = 3;
			this.skillUpgrades[2] = 2;
			this.skillUpgrades[3] = 4;
			this.skillUpgrades[4] = 2;
			this.skillUpgrades[5] = 3;
		}
		else if(char_id == 3){ 
			this.skillUpgrades[0] = 3;
			this.skillUpgrades[1] = 4;
			this.skillUpgrades[2] = 3;
			this.skillUpgrades[3] = 1;
			this.skillUpgrades[4] = 3;
			this.skillUpgrades[5] = 1;
		}
		else if(char_id == 4){
			this.skillUpgrades[0] = 4;
			this.skillUpgrades[1] = 1;
			this.skillUpgrades[2] = 2;
			this.skillUpgrades[3] = 2;
			this.skillUpgrades[4] = 4;
			this.skillUpgrades[5] = 2;
		}
		else if(char_id == 5){
			this.skillUpgrades[0] = 2;
			this.skillUpgrades[1] = 3;
			this.skillUpgrades[2] = 2;
			this.skillUpgrades[3] = 2;
			this.skillUpgrades[4] = 2;
			this.skillUpgrades[5] = 4;
		}
		else if(char_id == 6){ 
			this.skillUpgrades[0] = 2;
			this.skillUpgrades[1] = 2;
			this.skillUpgrades[2] = 2;
			this.skillUpgrades[3] = 2;
			this.skillUpgrades[4] = 4;
			this.skillUpgrades[5] = 3;
		}
		else if(char_id == 7){
			this.skillUpgrades[0] = 3;
			this.skillUpgrades[1] = 4;
			this.skillUpgrades[2] = 2;
			this.skillUpgrades[3] = 3;
			this.skillUpgrades[4] = 1;
			this.skillUpgrades[5] = 2;
		}
		else if(char_id == 8){ 
			this.skillUpgrades[0] = 2;
			this.skillUpgrades[1] = 4;
			this.skillUpgrades[2] = 2;
			this.skillUpgrades[3] = 2;
			this.skillUpgrades[4] = 3;
			this.skillUpgrades[5] = 2;
		}
		else if(char_id == 9){ 
			this.skillUpgrades[0] = 4;
			this.skillUpgrades[1] = 1;
			this.skillUpgrades[2] = 3;
			this.skillUpgrades[3] = 2;
			this.skillUpgrades[4] = 3;
			this.skillUpgrades[5] = 2;
		}
		else if(char_id == 10){
			this.skillUpgrades[0] = 1;
			this.skillUpgrades[1] = 3;
			this.skillUpgrades[2] = 2;
			this.skillUpgrades[3] = 3;
			this.skillUpgrades[4] = 2;
			this.skillUpgrades[5] = 4;
		}
		else if(char_id == 11){
			this.skillUpgrades[0] = 4;
			this.skillUpgrades[1] = 2;
			this.skillUpgrades[2] = 4;
			this.skillUpgrades[3] = 1;
			this.skillUpgrades[4] = 2;
			this.skillUpgrades[5] = 2;
		}
	}

	public void strClick(View view){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.select_bonus);
		builder.setItems(getID(skillUpgrades[0]), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which){
				skillData[16] = which;
				db.updateSkill(skills_id, 16, which);
				addDie(0, skillData[0], which);
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public void dexClick(View view){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.select_bonus);
		builder.setItems(getID(skillUpgrades[1]), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which){
				skillData[17] = which;
				db.updateSkill(skills_id, 17, which);
				addDie(1, skillData[1], which);
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public void conClick(View view){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.select_bonus);
		builder.setItems(getID(skillUpgrades[2]), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which){
				skillData[18] = which;
				db.updateSkill(skills_id, 18, which);
				addDie(2, skillData[2], which);
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public void intClick(View view){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.select_bonus);
		builder.setItems(getID(skillUpgrades[3]), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which){
				skillData[19] = which;
				db.updateSkill(skills_id, 19, which);
				addDie(3, skillData[3], which);
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public void wisClick(View view){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.select_bonus);
		builder.setItems(getID(skillUpgrades[4]), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which){
				skillData[20] = which;
				db.updateSkill(skills_id, 20, which);
				addDie(4, skillData[4], which);
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public void chaClick(View view){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.select_bonus);
		builder.setItems(getID(skillUpgrades[5]), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which){
				skillData[21] = which;
				db.updateSkill(skills_id, 21, which);
				addDie(5, skillData[5], which);
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public int getID(int skill){		
		switch(skill){
		case 0: return R.array.none;
		case 1: return R.array.one_to_one;
		case 2: return R.array.one_to_two;
		case 3: return R.array.one_to_three;
		default: return R.array.one_to_four;
		}
	}

}
