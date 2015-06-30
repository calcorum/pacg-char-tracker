package com.corum.manticore.pacgcharactertracker;

import java.util.ArrayList;

import com.corum.manticore.sqlite.helper.DatabaseHelper;
import com.corum.manticore.sqlite.model.Adventurers;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.util.Log;

public class AdventurersActivity extends ListActivity {
	ArrayList<Adventurers> adventurers	= new ArrayList<Adventurers>();
	ArrayList<String> adventurerNames = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	int party_id;
	DatabaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adventurers);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		if(adapter != null){
			adapter.clear();
		}
		
		db = new DatabaseHelper(getApplicationContext());
		Intent intent = getIntent();
		this.party_id = intent.getIntExtra("PARTY_ID", 0);
		
		adventurers = db.getAllAdventurers(this.party_id);
		setTitle(db.getParty(this.party_id).getName());
		
		for (Adventurers adv : adventurers){
			if(adv.getDescription() == null || adv.getDescription().length() == 0){
				adventurerNames.add(db.getCharName(adv.getCharacterId()));
			} else{
				adventurerNames.add(db.getCharName(adv.getCharacterId()) + " - " + adv.getDescription());				
			}
		}
		
		adapter = new ArrayAdapter<String>(this, R.layout.list_item_pacg, adventurerNames);
		setListAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.adventurers, menu);
		return true;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    super.onListItemClick(l, v, position, id);
	    
	    Intent intent = new Intent(this, SingleAdventurer.class);
	    intent.putExtra("ADVENTURER_ID", adventurers.get(position).getId());
	    intent.putExtra("CHARACTER_ID", adventurers.get(position).getCharacterId());
	    intent.putExtra("CHARACTER_DESC", adventurers.get(position).getDescription());
	    startActivity(intent);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.menu_add_adventurer:
			AlertDialog.Builder builder = new AlertDialog.Builder(AdventurersActivity.this);
			builder.setTitle(R.string.select_character);
			builder.setItems(R.array.characters, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which){
					db.createAdventurer(party_id, which + 1, db.getCharName(which + 1));
					dialog.dismiss();
					refresh();
				}
			});
			AlertDialog dialog = builder.create();
			dialog.show(); return true;
		
		case R.id.menu_delete_party:
			AlertDialog.Builder builder2 = new AlertDialog.Builder(AdventurersActivity.this);
        	builder2.setTitle(R.string.character_delete_character)
        		   .setMessage(R.string.character_delete_confirm)
        	       .setCancelable(true)
        	       .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	        	   // do nothing
        	           }
        	       })
        	       .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
			        	   db.deleteParty(party_id);
			        	   finish();
			        	   Intent intent = new Intent(getApplicationContext(), PartyActivity.class);
			        	   startActivity(intent);
        	           }
        	       });
        	AlertDialog alert3 = builder2.create();
        	alert3.show(); return true;
		
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
		default:
			return true;
		}
		
	}
	
//	public void addAdventurer(View v){
//		AlertDialog.Builder builder = new AlertDialog.Builder(AdventurersActivity.this);
//		builder.setTitle(R.string.select_character);
//		builder.setItems(R.array.characters, new DialogInterface.OnClickListener(){
//			@Override
//			public void onClick(DialogInterface dialog, int which){
//				db.createAdventurer(party_id, which + 1, db.getCharName(which + 1));
//				dialog.dismiss();
//				refresh();
//			}
//		});
//		AlertDialog dialog = builder.create();
//		dialog.show();
//	}
	
	public void refresh(){
		Intent intent = getIntent();
		overridePendingTransition(0, 0);
	    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();
		overridePendingTransition(0, 0);
		startActivity(intent);
	}

}
