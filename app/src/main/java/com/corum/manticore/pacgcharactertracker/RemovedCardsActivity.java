package com.corum.manticore.pacgcharactertracker;

import java.util.ArrayList;
import java.util.Collections;

import com.corum.manticore.sqlite.helper.DatabaseHelper;
import com.corum.manticore.sqlite.model.RemovedCards;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RemovedCardsActivity extends Activity {
	DatabaseHelper db;
	private int adventurer_id;
	private int party_id;
	private String charName;
	private RemovedCards cards;
	private final int textSize = 20;

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
		setContentView(R.layout.activity_removed_cards);
		Intent intent = getIntent();
		
		this.adventurer_id = intent.getIntExtra("ADVENTURER_ID", 0);
		this.party_id = intent.getIntExtra("PARTY_ID", 0);
		this.charName = intent.getStringExtra("CHAR_NAME");
		this.cards = db.getRemovedCards(getApplicationContext(), this.party_id);
		
		getCards();
		displayCards();
		
		setTitle(db.getParty(this.party_id).getName() + " - Removed Cards");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.removed_cards, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.menu_add_removed_card:
			AlertDialog.Builder builder = new AlertDialog.Builder(RemovedCardsActivity.this);
			builder.setTitle(R.string.choose_removed_type);
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
		AlertDialog.Builder builder = new AlertDialog.Builder(RemovedCardsActivity.this);
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
				cards.addCard(which + start[card_type]);
				db.addRemovedCards(getApplicationContext(), party_id, cards.getCards());
				refresh();
			}
			
		});	
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	private void getCards(){
		for ( int i : cards.getCards()){
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
			text.setTag(i);
			text.setTextSize(this.textSize);
			text.setOnClickListener(card_listener);
			text.setLayoutParams(params);
			((LinearLayout) layout).addView(text);
			count++;
		}
		count = 0;
		for (int i : this.spells){
			layout = findViewById(R.id.spe_list);
			text = new TextView(this);
			text.setText(db.getCard(i).getName());
			text.setTag(i);
			text.setTextSize(this.textSize);
			text.setOnClickListener(card_listener);
			text.setLayoutParams(params);
			((LinearLayout) layout).addView(text);
			count++;
		}
		count = 0;
		for (int i : this.armor){
			layout = findViewById(R.id.arm_list);
			text = new TextView(this);
			text.setText(db.getCard(i).getName());
			text.setTag(i);
			text.setTextSize(this.textSize);
			text.setOnClickListener(card_listener);
			text.setLayoutParams(params);
			((LinearLayout) layout).addView(text);
			count++;
		}
		count = 0;
		for (int i : this.items){
			layout = findViewById(R.id.ite_list);
			text = new TextView(this);
			text.setText(db.getCard(i).getName());
			text.setTag(i);
			text.setTextSize(this.textSize);
			text.setOnClickListener(card_listener);
			text.setLayoutParams(params);
			((LinearLayout) layout).addView(text);
			count++;
		}
		count = 0;
		for (int i : this.allies){
			layout = findViewById(R.id.all_list);
			text = new TextView(this);
			text.setText(db.getCard(i).getName());
			text.setTag(i);
			text.setTextSize(this.textSize);
			text.setOnClickListener(card_listener);
			text.setLayoutParams(params);
			((LinearLayout) layout).addView(text);
			count++;
		}
		count = 0;
		for (int i : this.blessings){
			layout = findViewById(R.id.ble_list);
			text = new TextView(this);
			text.setText(db.getCard(i).getName());
			text.setTag(i);
			text.setTextSize(this.textSize);
			text.setOnClickListener(card_listener);
			text.setLayoutParams(params);
			((LinearLayout) layout).addView(text);
			count++;
		}
	}
	
	private OnClickListener card_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			final View button = v;
			AlertDialog.Builder builder = new AlertDialog.Builder(RemovedCardsActivity.this);
        	builder.setTitle(R.string.reinsert_card)
        		   .setMessage(R.string.message_reinsert_card)
        	       .setCancelable(true)
        	       .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	        	   // do nothing
        	           }
        	       })
        	       .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	        	   int tag = (Integer) button.getTag();
			        	   cards.removeCard(tag);
			        	   db.addRemovedCards(getApplicationContext(), party_id, cards.getCards());
			        	   refresh();
        	           }
        	       });
        	AlertDialog alert = builder.create();
        	alert.show();	
		}
	};
	
//	private OnClickListener spe_listener = new OnClickListener(){
//		@Override
//		public void onClick(View v) {
//
//	
//		AlertDialog.Builder builder = new AlertDialog.Builder(RemovedCardsActivity.this);
//		builder.setTitle(R.string.select_weapon);
//		builder.setItems(R.array.weapon_array, new DialogInterface.OnClickListener(){
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				if(which == 0){
//					cards.removeCard(tag);
//				}else{
//					cards.addCard(which + 1);
//				}
//				db.addRemovedCards(getApplicationContext(), party_id, cards.getCards());
//				refresh();
//			}
//			
//		});	
//		AlertDialog dialog = builder.create();
//		dialog.show();	
//	
//	
//			final View button = v;
//			final int[] start = {1, 62, 112, 142, 207, 260};
//			AlertDialog.Builder builder = new AlertDialog.Builder(RemovedCardsActivity.this);
//			builder.setTitle(R.string.select_spell);
//			builder.setItems(R.array.spell_array, new DialogInterface.OnClickListener(){
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					int tag = (Integer) button.getTag();
//					if(which == 0){
//						db.updateDeck(deck_id, spells.get(tag), 0);
//					}else{
//						db.updateDeck(deck_id, spells.get(tag), speStart + which);
//					}
//					refresh();
//				}
//				
//			});	
//			AlertDialog dialog = builder.create();
//			dialog.show();		
//		}
//	};
//	
//	private OnClickListener arm_listener = new OnClickListener(){
//		@Override
//		public void onClick(View v) {
//			final View button = v;
//			final int[] start = {1, 62, 112, 142, 207, 260};
//			AlertDialog.Builder builder = new AlertDialog.Builder(RemovedCardsActivity.this);
//			builder.setTitle(R.string.select_armor);
//			builder.setItems(R.array.armor_array, new DialogInterface.OnClickListener(){
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					int tag = (Integer) button.getTag();
//					if(which == 0){
//						db.updateDeck(deck_id, armor.get(tag), 0);
//					}else{
//						db.updateDeck(deck_id, armor.get(tag), armStart + which);
//					}
//					refresh();
//				}
//				
//			});	
//			AlertDialog dialog = builder.create();
//			dialog.show();		
//		}
//	};
//	
//	private OnClickListener ite_listener = new OnClickListener(){
//		@Override
//		public void onClick(View v) {
//			final View button = v;
//			final int[] start = {1, 62, 112, 142, 207, 260};
//			AlertDialog.Builder builder = new AlertDialog.Builder(RemovedCardsActivity.this);
//			builder.setTitle(R.string.select_item);
//			builder.setItems(R.array.item_array, new DialogInterface.OnClickListener(){
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					int tag = (Integer) button.getTag();
//					if(which == 0){
//						db.updateDeck(deck_id, items.get(tag), 0);
//					}else{
//						db.updateDeck(deck_id, items.get(tag), iteStart + which);
//					}
//					refresh();
//				}
//				
//			});	
//			AlertDialog dialog = builder.create();
//			dialog.show();		
//		}
//	};
//	
//	private OnClickListener all_listener = new OnClickListener(){
//		@Override
//		public void onClick(View v) {
//			final View button = v;
//			final int[] start = {1, 62, 112, 142, 207, 260};
//			AlertDialog.Builder builder = new AlertDialog.Builder(RemovedCardsActivity.this);
//			builder.setTitle(R.string.select_ally);
//			builder.setItems(R.array.ally_array, new DialogInterface.OnClickListener(){
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					int tag = (Integer) button.getTag();
//					if(which == 0){
//						db.updateDeck(deck_id, allies.get(tag), 0);
//					}else{
//						db.updateDeck(deck_id, allies.get(tag), allStart + which);
//					}
//					refresh();
//				}
//				
//			});	
//			AlertDialog dialog = builder.create();
//			dialog.show();		
//		}
//	};
//	
//	private OnClickListener ble_listener = new OnClickListener(){
//		@Override
//		public void onClick(View v) {
//			final View button = v;
//			final int[] start = {1, 62, 112, 142, 207, 260};
//			AlertDialog.Builder builder = new AlertDialog.Builder(RemovedCardsActivity.this);
//			builder.setTitle(R.string.select_blessing);
//			builder.setItems(R.array.blessing_array, new DialogInterface.OnClickListener(){
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					int tag = (Integer) button.getTag();
//					if(which == 0){
//						db.updateDeck(deck_id, blessings.get(tag), 0);
//					}else{
//						db.updateDeck(deck_id, blessings.get(tag), bleStart + which);
//					}
//					refresh();
//				}
//				
//			});	
//			AlertDialog dialog = builder.create();
//			dialog.show();		
//		}
//	};
	
	public void refresh(){
		Intent intent = getIntent();
		overridePendingTransition(0, 0);
	    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();
		overridePendingTransition(0, 0);
		startActivity(intent);
	}

}
