package com.corum.manticore.pacgcharactertracker;

import java.util.ArrayList;
import com.corum.manticore.sqlite.helper.DatabaseHelper;
import com.corum.manticore.sqlite.model.Party;

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

public class PartyActivity extends ListActivity {
	
	ArrayList<Party> parties = new ArrayList<Party>();
	ArrayList<String> partyNames = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	DatabaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new DatabaseHelper(getApplicationContext());
		setContentView(R.layout.activity_party);
		
		parties = db.getAllParties();
		for (Party p : parties){
			partyNames.add(p.getName());
		}
		
		adapter = new ArrayAdapter<String>(this, R.layout.list_item_pacg, partyNames);
		setListAdapter(adapter);
	}
	
	@Override
	public void onRestart(){
		super.onRestart();
		onNewIntent(getIntent());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.party, menu);
		return true;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    super.onListItemClick(l, v, position, id);
	    
	    Intent intent = new Intent(this, AdventurersActivity.class);
	    intent.putExtra("PARTY_ID", parties.get(position).getId());
	    startActivity(intent);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.menu_add_party:
			final EditText input = new EditText(this);
			new AlertDialog.Builder(this)
		    .setTitle("Enter New Party Name")
		    .setView(input)
		    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int whichButton) {
		            Editable value = input.getText(); 
		            db.createParty(value.toString(), getApplicationContext());
		            refresh();
		        }
		    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int whichButton) {
		            // Do nothing.
		        }
		    }).show();
			return true;
		    
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
	
	public void refresh(){
		Intent intent = getIntent();
		overridePendingTransition(0, 0);
	    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();
		overridePendingTransition(0, 0);
		startActivity(intent);
	}

}
