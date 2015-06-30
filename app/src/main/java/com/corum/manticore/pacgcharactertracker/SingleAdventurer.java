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
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class SingleAdventurer extends ListActivity {
	ArrayList<String> buttons = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	DatabaseHelper db;
	int adventurer_id;
	int character_id;
	String charName;
	String description;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new DatabaseHelper(getApplicationContext());
		Intent intent = getIntent();
		setContentView(R.layout.activity_single_adventurer);
		
		this.adventurer_id = intent.getIntExtra("ADVENTURER_ID", 0);
		this.character_id = intent.getIntExtra("CHARACTER_ID", 0);
		this.description = intent.getStringExtra("CHARACTER_DESC");
		this.charName = db.getCharName(character_id);
		
		buttons.add("Skills");
		buttons.add("Powers");
		buttons.add("Deck List");
		buttons.add("Scenario List");
		buttons.add("Removed Cards");
		
		adapter = new ArrayAdapter<String>(this, R.layout.list_item_pacg, buttons);
		setListAdapter(adapter);
		
	}
	
	@Override
	public void onResume(){
		super.onResume();
		onNewIntent(getIntent());
		
		if(this.description == null || this.description.length() == 0){
			setTitle(this.charName);
		}else{
			setTitle(this.charName + " - " + this.description);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_adventurer, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){  
		case R.id.character_add_description:
			final EditText input = new EditText(this);
			new AlertDialog.Builder(this)
		    .setTitle(R.string.character_add_description)
		    .setView(input)
		    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int whichButton) {
		            Editable value = input.getText(); 
		            db.updateAdventurerDescription(adventurer_id, value.toString());
		        }
		    }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int whichButton) {
		            // Do nothing.
		        }
		    }).show(); break;
			
		case R.id.character_delete_character:
			AlertDialog.Builder builder = new AlertDialog.Builder(SingleAdventurer.this);
        	builder.setTitle(R.string.character_delete_character)
        		   .setMessage(R.string.character_delete_confirm)
        	       .setCancelable(true)
        	       .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	        	   // do nothing
        	           }
        	       })
        	       .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	        	   Adventurers adv = db.getAdventurer(adventurer_id);
        	        	   int party_id = adv.getPartyId();
			        	   db.deleteAdventurer(adventurer_id, adv.getPowersId(), 
			        			   adv.getSkillsId(), adv.getDeckContentId());
			        	   Intent intent = new Intent(getApplicationContext(), AdventurersActivity.class);
			        	   intent.putExtra("PARTY_ID", party_id); 
			        	   finish();
			        	   startActivity(intent);
        	           }
        	       });
        	AlertDialog alert = builder.create();
        	alert.show(); break;
        	
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
        	AlertDialog alert1 = builder1.create();
        	alert1.show();
            return true;
		}

		return true;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    super.onListItemClick(l, v, position, id);
	    Intent intent = null;
	    
	    switch(position){
	    case 0:
	    	// Skills Page
	    	intent = new Intent(this, SkillsActivity.class); 
	    	intent.putExtra("SKILLS_ID", db.getAdventurer(adventurer_id).getSkillsId()); break;
	    case 1:
	    	// Powers Page
	    	intent = new Intent(this, PowersActivity.class);
	    	intent.putExtra("CHARACTER_ID", this.character_id);
	    	intent.putExtra("SKILLS_ID", db.getAdventurer(adventurer_id).getSkillsId());
	    	intent.putExtra("POWERS_ID", db.getAdventurer(adventurer_id).getPowersId()); break;
	    case 2:
	    	// Deck List Page
	    	intent = new Intent(this, DeckListActivity.class); 
	    	intent.putExtra("DECK_ID", db.getAdventurer(adventurer_id).getDeckContentId()); break;
	    case 3:
	    	// Scenario Page
	    	intent = new Intent(this, ScenarioActivity.class);
	    	intent.putExtra("SCEN_ID", db.getAdventurer(adventurer_id).getScenariosId()); break;
	    case 4:
	    	// Removed Cards Page
	    	intent = new Intent(this, RemovedCardsActivity.class);
	    	intent.putExtra("PARTY_ID", db.getAdventurer(adventurer_id).getPartyId()); break;
	    }
    	intent.putExtra("ADVENTURER_ID", db.getAdventurer(this.adventurer_id).getId());
    	intent.putExtra("CHAR_NAME", this.charName);
	    startActivity(intent);
	}

}
