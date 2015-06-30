package com.corum.manticore.pacgcharactertracker;

import java.util.ArrayList;
import java.util.Collections;

import com.corum.manticore.sqlite.helper.DatabaseHelper;
import com.corum.manticore.sqlite.model.Card;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DeckListActivity extends Activity {
	private DatabaseHelper db;
	@SuppressWarnings("unused")
	private int adventurer_id;
	private int deck_id;
	private String charName;
	private final int textSize = 19;
	private final int speStart = 61;
	private final int armStart = 111;
	private final int iteStart = 141;
	private final int allStart = 206;
	private final int bleStart = 259;
	private ArrayList<Integer> weapons = new ArrayList<Integer>();
	private ArrayList<Integer> spells = new ArrayList<Integer>();
	private ArrayList<Integer> armor = new ArrayList<Integer>();
	private ArrayList<Integer> items = new ArrayList<Integer>();
	private ArrayList<Integer> allies = new ArrayList<Integer>();
	private ArrayList<Integer> blessings = new ArrayList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new DatabaseHelper(getApplicationContext());
		setContentView(R.layout.activity_deck_list);
		
		Intent intent = getIntent();
		this.adventurer_id = intent.getIntExtra("ADVENTURER_ID", 0);
		this.charName = intent.getStringExtra("CHAR_NAME");
		this.deck_id = intent.getIntExtra("DECK_ID", 0);
		
		getCards();
		setCounts();
		displayCards();
		
		setTitle(charName + " - Deck");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deck_list, menu);
		return true;
	}
	
	private void getCards(){
		int[] deck = db.getDeck(this.deck_id).getAllCards();
		for ( int i : deck){
			if (i != 0){
				switch(db.getCard(i).getType()){
				case 1: this.weapons.add(i); break;
				case 2: this.spells.add(i); break;
				case 3: this.armor.add(i); break;
				case 4: this.items.add(i); break;
				case 5: this.allies.add(i); break;
				case 6: this.blessings.add(i); break;
				}
			}
			
		}
		Collections.sort(weapons); 
		Collections.sort(spells); 
		Collections.sort(armor); 
		Collections.sort(items); 
		Collections.sort(allies); 
		Collections.sort(blessings);
	}
	
	private void setCounts(){
		// Set Weapon Count
		TextView tv = (TextView) findViewById(R.id.wea_count);
		tv.setText(String.valueOf(weapons.size()));
		
		// Set Spell Count
		tv = (TextView) findViewById(R.id.spe_count);
		tv.setText(String.valueOf(spells.size()));
		
		// Set Armor Count
		tv = (TextView) findViewById(R.id.arm_count);
		tv.setText(String.valueOf(armor.size()));
		
		// Set Item Count
		tv = (TextView) findViewById(R.id.ite_count);
		tv.setText(String.valueOf(items.size()));
		
		// Set Ally Count
		tv = (TextView) findViewById(R.id.all_count);
		tv.setText(String.valueOf(allies.size()));
		
		// Set Blessing Count
		tv = (TextView) findViewById(R.id.ble_count);
		tv.setText(String.valueOf(blessings.size()));
	}
	
	private void displayCards(){
		View layout = null;
		TextView text = null;
		int count = 0;
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(0, 0, 0, 10);
		
		for (int i : this.weapons){
			layout = findViewById(R.id.wea_list);
			text = new TextView(this);
			text.setText(db.getCard(i).getName());
			text.setTag(count);
			text.setTextSize(this.textSize);
			text.setOnClickListener(wea_listener);
			text.setLayoutParams(params);
			((LinearLayout) layout).addView(text);
			count++;
		}
		count = 0;
		for (int i : this.spells){
			layout = findViewById(R.id.spe_list);
			text = new TextView(this);
			text.setText(db.getCard(i).getName());
			text.setTag(count);
			text.setTextSize(this.textSize);
			text.setOnClickListener(spe_listener);
			text.setLayoutParams(params);
			((LinearLayout) layout).addView(text);
			count++;
		}
		count = 0;
		for (int i : this.armor){
			layout = findViewById(R.id.arm_list);
			text = new TextView(this);
			text.setText(db.getCard(i).getName());
			text.setTag(count);
			text.setTextSize(this.textSize);
			text.setOnClickListener(arm_listener);
			text.setLayoutParams(params);
			((LinearLayout) layout).addView(text);
			count++;
		}
		count = 0;
		for (int i : this.items){
			layout = findViewById(R.id.ite_list);
			text = new TextView(this);
			text.setText(db.getCard(i).getName());
			text.setTag(count);
			text.setTextSize(this.textSize);
			text.setOnClickListener(ite_listener);
			text.setLayoutParams(params);
			((LinearLayout) layout).addView(text);
			count++;
		}
		count = 0;
		for (int i : this.allies){
			layout = findViewById(R.id.all_list);
			text = new TextView(this);
			text.setText(db.getCard(i).getName());
			text.setTag(count);
			text.setTextSize(this.textSize);
			text.setOnClickListener(all_listener);
			text.setLayoutParams(params);
			((LinearLayout) layout).addView(text);
			count++;
		}
		count = 0;
		for (int i : this.blessings){
			layout = findViewById(R.id.ble_list);
			text = new TextView(this);
			text.setText(db.getCard(i).getName());
			text.setTag(count);
			text.setTextSize(this.textSize);
			text.setOnClickListener(ble_listener);
			text.setLayoutParams(params);
			((LinearLayout) layout).addView(text);
			count++;
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.menu_add_card:
			AlertDialog.Builder builder = new AlertDialog.Builder(DeckListActivity.this);
			builder.setTitle(R.string.add_card);
			builder.setItems(R.array.choose_type, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					chooseCard(which);
				}
				
			});	
			AlertDialog dialog = builder.create();
			dialog.show(); break;
		    
		case R.id.menu_about:
        	ContextThemeWrapper cw = new ContextThemeWrapper(this, R.style.AlertDialogTheme);
        	AlertDialog.Builder builder1 = new AlertDialog.Builder(cw);
        	builder1.setTitle(R.string.comm_policy)
        		   .setMessage(R.string.community_use)
        	       .setCancelable(false)
        	       .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	                //do things
        	           }
        	       });
        	AlertDialog alert = builder1.create();
        	alert.show();
            return true;
		}

		return true;
	}
	
	public void chooseCard(int type){
		String title = null;
		int array = 0;
		final int[] start = {1, 62, 112, 142, 207, 260};
		final int card_type = type;
		AlertDialog.Builder builder = new AlertDialog.Builder(DeckListActivity.this);
		switch(type){
		case 0:
			title = getString(R.string.select_weapon);
			array = R.array.new_weapon_array; break;
		case 1: 
			title = getString(R.string.select_spell);
			array = R.array.new_spell_array; break;
		case 2:
			title = getString(R.string.select_armor);
			array = R.array.new_armor_array; break;
		case 3: 
			title = getString(R.string.select_item);
			array = R.array.new_item_array; break;
		case 4:
			title = getString(R.string.select_ally);
			array = R.array.new_ally_array; break;
		case 5: 
			title = getString(R.string.select_blessing);
			array = R.array.new_blessing_array; break;
		}
		builder.setTitle(title);
		builder.setItems(array, new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				db.updateDeck(deck_id, 0, start[card_type] + which);
				refresh();
			}
			
		});	
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	private OnClickListener wea_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final View button = v;
			AlertDialog.Builder builder = new AlertDialog.Builder(DeckListActivity.this);
			builder.setTitle(R.string.select_weapon);
			builder.setItems(R.array.weapon_array, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					int tag = (Integer) button.getTag();
					if(which == 0){
						db.updateDeck(deck_id, weapons.get(tag), 0);
					}else{
						db.updateDeck(deck_id, weapons.get(tag), which);
					}
					refresh();
				}
				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	private OnClickListener spe_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final View button = v;
			AlertDialog.Builder builder = new AlertDialog.Builder(DeckListActivity.this);
			builder.setTitle(R.string.select_spell);
			builder.setItems(R.array.spell_array, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					int tag = (Integer) button.getTag();
					if(which == 0){
						db.updateDeck(deck_id, spells.get(tag), 0);
					}else{
						db.updateDeck(deck_id, spells.get(tag), speStart + which);
					}
					refresh();
				}
				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	private OnClickListener arm_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final View button = v;
			AlertDialog.Builder builder = new AlertDialog.Builder(DeckListActivity.this);
			builder.setTitle(R.string.select_armor);
			builder.setItems(R.array.armor_array, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					int tag = (Integer) button.getTag();
					if(which == 0){
						db.updateDeck(deck_id, armor.get(tag), 0);
					}else{
						db.updateDeck(deck_id, armor.get(tag), armStart + which);
					}
					refresh();
				}
				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	private OnClickListener ite_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final View button = v;
			AlertDialog.Builder builder = new AlertDialog.Builder(DeckListActivity.this);
			builder.setTitle(R.string.select_item);
			builder.setItems(R.array.item_array, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					int tag = (Integer) button.getTag();
					if(which == 0){
						db.updateDeck(deck_id, items.get(tag), 0);
					}else{
						db.updateDeck(deck_id, items.get(tag), iteStart + which);
					}
					refresh();
				}
				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	private OnClickListener all_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final View button = v;
			AlertDialog.Builder builder = new AlertDialog.Builder(DeckListActivity.this);
			builder.setTitle(R.string.select_ally);
			builder.setItems(R.array.ally_array, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					int tag = (Integer) button.getTag();
					if(which == 0){
						db.updateDeck(deck_id, allies.get(tag), 0);
					}else{
						db.updateDeck(deck_id, allies.get(tag), allStart + which);
					}
					refresh();
				}
				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	private OnClickListener ble_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final View button = v;
			AlertDialog.Builder builder = new AlertDialog.Builder(DeckListActivity.this);
			builder.setTitle(R.string.select_blessing);
			builder.setItems(R.array.blessing_array, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					int tag = (Integer) button.getTag();
					if(which == 0){
						db.updateDeck(deck_id, blessings.get(tag), 0);
					}else{
						db.updateDeck(deck_id, blessings.get(tag), bleStart + which);
					}
					refresh();
				}
				
			});	
			AlertDialog dialog = builder.create();
			dialog.show();		
		}
	};
	
	public void refresh(){
		Intent intent = getIntent();
		overridePendingTransition(0, 0);
	    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();
		overridePendingTransition(0, 0);
		startActivity(intent);
	}

}
