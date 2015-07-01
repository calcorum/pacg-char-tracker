package com.corum.manticore.sqlite.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.corum.manticore.pacgcharactertracker.R;
import com.corum.manticore.sqlite.model.Adventurers;
import com.corum.manticore.sqlite.model.Card;
import com.corum.manticore.sqlite.model.DeckContent;
import com.corum.manticore.sqlite.model.Party;
import com.corum.manticore.sqlite.model.Powers;
import com.corum.manticore.sqlite.model.RemovedCards;
import com.corum.manticore.sqlite.model.Scenarios;
import com.corum.manticore.sqlite.model.Skills;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	private Context context;
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "pacgCharacterTracker";
	
	// Table names
	private static final String TABLE_PARTY = "party";
	private static final String TABLE_ADVEN = "adventurer";
	private static final String TABLE_CHAR = "character";
	private static final String TABLE_SKILL = "skills";
	private static final String TABLE_POWER = "powers";
	private static final String TABLE_SCEN = "scenarios";
	private static final String TABLE_CARD = "cards";
	private static final String TABLE_DECK = "deck";
	
	// Common column names
	private static final String KEY_ID = "_id";
	private static final String KEY_DESC = "desc";
	
	// Specific column names
	private static final String KEY_PARTY_NAME = "party_name"; 		// party
	private static final String KEY_PARTY_ID = "party_id";			// adv
	private static final String KEY_CHAR_ID = "character_id";		// adv
	private static final String KEY_POWERS_ID = "powers_id";		// adv
	private static final String KEY_SKILLS_ID = "skills_id";		// adv
	private static final String KEY_SCEN_ID = "scenarios_id";		// adv
	private static final String KEY_DECK_ID = "deckcon_id";			// adv
	private static final String KEY_SORT_ORDER = "sort_order";		// adv
	private static final String KEY_CHAR_NAME = "character_name";	// char
	private static final String KEY_SKILLS_1 = "skills_1";			// skill
	private static final String KEY_SKILLS_2 = "skills_2";			// skill
	private static final String KEY_SKILLS_3 = "skills_3";			// skill
	private static final String KEY_SKILLS_4 = "skills_4";			// skill
	private static final String KEY_SKILLS_5 = "skills_5";			// skill
	private static final String KEY_SKILLS_6 = "skills_6";			// skill
	private static final String KEY_BONUS_1 = "bonus_1";			// skill
	private static final String KEY_BONUS_2 = "bonus_2";			// skill
	private static final String KEY_BONUS_3 = "bonus_3";			// skill
	private static final String KEY_BONUS_4 = "bonus_4";			// skill
	private static final String KEY_BONUS_5 = "bonus_5";			// skill
	private static final String KEY_DATA_1 = "data_1";				// skill
	private static final String KEY_DATA_2 = "data_2";				// skill
	private static final String KEY_DATA_3 = "data_3";				// skill
	private static final String KEY_DATA_4 = "data_4";				// skill
	private static final String KEY_DATA_5 = "data_5";				// skill
	private static final String KEY_UPGR_1 = "upgr_1";				// skill
	private static final String KEY_UPGR_2 = "upgr_2";				// skill
	private static final String KEY_UPGR_3 = "upgr_3";				// skill
	private static final String KEY_UPGR_4 = "upgr_4";				// skill
	private static final String KEY_UPGR_5 = "upgr_5";				// skill
	private static final String KEY_UPGR_6 = "upgr_6";				// skill
	private static final String KEY_POWER_1 = "power_1";			// power
	private static final String KEY_POWER_2 = "power_2";			// power
	private static final String KEY_POWER_3 = "power_3";			// power
	private static final String KEY_POWER_4 = "power_4";			// power
	private static final String KEY_POWER_5 = "power_5";			// power
	private static final String KEY_POWER_6 = "power_6";			// power
	private static final String KEY_POWER_7 = "power_7";			// power
	private static final String KEY_POWER_8 = "power_8";			// power
	private static final String KEY_POWER_9 = "power_9";			// power
	private static final String KEY_POWER_10 = "power_10";			// power
	private static final String KEY_POWER_11 = "power_11";			// power
	private static final String KEY_POWER_12 = "power_12";			// power
	private static final String KEY_POWER_13 = "power_13";			// power
	private static final String KEY_POWER_14 = "power_14";			// power
	private static final String KEY_POWER_15 = "power_15";			// power
	private static final String KEY_PROF_1 = "prof_1";				// power
	private static final String KEY_PROF_2 = "prof_2";				// power
	private static final String KEY_PROF_3 = "prof_3";				// power
	private static final String KEY_SCEN_0_1 = "scenario_0_1";		// scen
	private static final String KEY_SCEN_0_2 = "scenario_0_2";		// scen
	private static final String KEY_SCEN_0_3 = "scenario_0_3";		// scen
	private static final String KEY_SCEN_1_1 = "scenario_1_1";		// scen
	private static final String KEY_SCEN_1_2 = "scenario_1_2";		// scen
	private static final String KEY_SCEN_1_3 = "scenario_1_3";		// scen
	private static final String KEY_SCEN_1_4 = "scenario_1_4";		// scen
	private static final String KEY_SCEN_1_5 = "scenario_1_5";		// scen
	private static final String KEY_SCEN_2_1 = "scenario_2_1";		// scen
	private static final String KEY_SCEN_2_2 = "scenario_2_2";		// scen
	private static final String KEY_SCEN_2_3 = "scenario_2_3";		// scen
	private static final String KEY_SCEN_2_4 = "scenario_2_4";		// scen
	private static final String KEY_SCEN_2_5 = "scenario_2_5";		// scen
	private static final String KEY_SCEN_3_1 = "scenario_3_1";		// scen
	private static final String KEY_SCEN_3_2 = "scenario_3_2";		// scen
	private static final String KEY_SCEN_3_3 = "scenario_3_3";		// scen
	private static final String KEY_SCEN_3_4 = "scenario_3_4";		// scen
	private static final String KEY_SCEN_3_5 = "scenario_3_5";		// scen
	private static final String KEY_SCEN_4_1 = "scenario_4_1";		// scen
	private static final String KEY_SCEN_4_2 = "scenario_4_2";		// scen
	private static final String KEY_SCEN_4_3 = "scenario_4_3";		// scen
	private static final String KEY_SCEN_4_4 = "scenario_4_4";		// scen
	private static final String KEY_SCEN_4_5 = "scenario_4_5";		// scen
	private static final String KEY_SCEN_5_1 = "scenario_5_1";		// scen
	private static final String KEY_SCEN_5_2 = "scenario_5_2";		// scen
	private static final String KEY_SCEN_5_3 = "scenario_5_3";		// scen
	private static final String KEY_SCEN_5_4 = "scenario_5_4";		// scen
	private static final String KEY_SCEN_5_5 = "scenario_5_5";		// scen
	private static final String KEY_SCEN_6_1 = "scenario_6_1";		// scen
	private static final String KEY_SCEN_6_2 = "scenario_6_2";		// scen
	private static final String KEY_SCEN_6_3 = "scenario_6_3";		// scen
	private static final String KEY_SCEN_6_4 = "scenario_6_4";		// scen
	private static final String KEY_SCEN_6_5 = "scenario_6_5";		// scen
	private static final String KEY_CARD_TYPE = "card_type";		// card
	private static final String KEY_CARD_EXPAN = "card_expan";		// card
	private static final String KEY_CARD_NAME = "card_name";		// card
	private static final String KEY_CARD_1 = "card_1";				// deck
	private static final String KEY_CARD_2 = "card_2";				// deck
	private static final String KEY_CARD_3 = "card_3";				// deck
	private static final String KEY_CARD_4 = "card_4";				// deck
	private static final String KEY_CARD_5 = "card_5";				// deck
	private static final String KEY_CARD_6 = "card_6";				// deck
	private static final String KEY_CARD_7 = "card_7";				// deck
	private static final String KEY_CARD_8 = "card_8";				// deck
	private static final String KEY_CARD_9 = "card_9";				// deck
	private static final String KEY_CARD_10 = "card_10";			// deck
	private static final String KEY_CARD_11 = "card_11";			// deck
	private static final String KEY_CARD_12 = "card_12";			// deck
	private static final String KEY_CARD_13 = "card_13";			// deck
	private static final String KEY_CARD_14 = "card_14";			// deck
	private static final String KEY_CARD_15 = "card_15";			// deck
	private static final String KEY_CARD_16 = "card_16";			// deck
	private static final String KEY_CARD_17 = "card_17";			// deck
	private static final String KEY_CARD_18 = "card_18";			// deck
	private static final String KEY_CARD_19 = "card_19";			// deck
	private static final String KEY_CARD_20 = "card_20";			// deck
	private static final String KEY_CARD_21 = "card_21";			// deck
	private static final String KEY_CARD_22 = "card_22";			// deck
	private static final String KEY_CARD_23 = "card_23";			// deck
	private static final String KEY_CARD_24 = "card_24";			// deck
	private static final String KEY_CARD_25 = "card_25";			// deck
			
	
	// Create table statements
	private static final String CREATE_TABLE_PARTY = "CREATE TABLE " + TABLE_PARTY
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PARTY_NAME + " TEXT," + KEY_DESC + " TEXT)";
	private static final String CREATE_TABLE_ADVEN = "CREATE TABLE " + TABLE_ADVEN
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PARTY_ID + " TEXT,"
			+ KEY_CHAR_ID + " TEXT," + KEY_POWERS_ID + " TEXT,"  + KEY_SKILLS_ID + " TEXT,"
			+ KEY_SCEN_ID + " TEXT," + KEY_DECK_ID + " TEXT," + KEY_DESC + " TEXT,"
			+ KEY_SORT_ORDER + " TEXT)";
	private static final String CREATE_TABLE_CHAR = "CREATE TABLE " + TABLE_CHAR
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CHAR_NAME + " TEXT)";
	private static final String CREATE_TABLE_SKILL = "CREATE TABLE " + TABLE_SKILL
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SKILLS_1 + " INTEGER,"
			+ KEY_SKILLS_2 + " INTEGER," + KEY_SKILLS_3 + " INTEGER," + KEY_SKILLS_4 + " INTEGER,"
			+ KEY_SKILLS_5 + " INTEGER," + KEY_SKILLS_6 + " INTEGER," + KEY_BONUS_1 + " INTEGER,"
			+ KEY_BONUS_2 + " INTEGER," + KEY_BONUS_3 + " INTEGER," + KEY_BONUS_4 + " INTEGER,"
			+ KEY_BONUS_5 + " INTEGER," + KEY_DATA_1 + " INTEGER," + KEY_DATA_2 + " INTEGER,"
			+ KEY_DATA_3 + " INTEGER," + KEY_DATA_4 + " INTEGER," + KEY_DATA_5 + " INTEGER,"
			+ KEY_UPGR_1 + " INTEGER," + KEY_UPGR_2 + " INTEGER," + KEY_UPGR_3 + " INTEGER,"
			+ KEY_UPGR_4 + " INTEGER," + KEY_UPGR_5 + " INTEGER," + KEY_UPGR_6 + " INTEGER)";
	private static final String CREATE_TABLE_POWER = "CREATE TABLE " + TABLE_POWER
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_POWER_1 + " INTEGER,"
			+ KEY_POWER_2 + " INTEGER," + KEY_POWER_3 + " INTEGER," + KEY_POWER_4 + " INTEGER,"
			+ KEY_POWER_5 + " INTEGER," + KEY_POWER_6 + " INTEGER," + KEY_POWER_7 + " INTEGER,"
			+ KEY_POWER_8 + " INTEGER," + KEY_POWER_9 + " INTEGER," + KEY_POWER_10 + " INTEGER,"
			+ KEY_POWER_11 + " INTEGER," + KEY_POWER_12 + " INTEGER," + KEY_POWER_13 + " INTEGER,"
			+ KEY_POWER_14 + " INTEGER," + KEY_POWER_15 + " INTEGER," + KEY_PROF_1 + " INTEGER,"
			+ KEY_PROF_2 + " INTEGER," + KEY_PROF_3 + " INTEGER)";
	private static final String CREATE_TABLE_SCENARIO = "CREATE TABLE " + TABLE_SCEN
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SCEN_0_1 + " INTEGER,"
			+ KEY_SCEN_0_2 + " INTEGER," + KEY_SCEN_0_3 + " INTEGER," + KEY_SCEN_1_1 + " INTEGER,"
			+ KEY_SCEN_1_2 + " INTEGER," + KEY_SCEN_1_3 + " INTEGER," + KEY_SCEN_1_4 + " INTEGER,"
			+ KEY_SCEN_1_5 + " INTEGER," + KEY_SCEN_2_1 + " INTEGER," + KEY_SCEN_2_2 + " INTEGER," 
			+ KEY_SCEN_2_3 + " INTEGER," + KEY_SCEN_2_4 + " INTEGER," + KEY_SCEN_2_5 + " INTEGER,"
			+ KEY_SCEN_3_1 + " INTEGER," + KEY_SCEN_3_2 + " INTEGER," + KEY_SCEN_3_3 + " INTEGER," 
			+ KEY_SCEN_3_4 + " INTEGER," + KEY_SCEN_3_5 + " INTEGER," + KEY_SCEN_4_1 + " INTEGER,"
			+ KEY_SCEN_4_2 + " INTEGER," + KEY_SCEN_4_3 + " INTEGER," + KEY_SCEN_4_4 + " INTEGER,"
			+ KEY_SCEN_4_5 + " INTEGER," + KEY_SCEN_5_1 + " INTEGER," + KEY_SCEN_5_2 + " INTEGER," 
			+ KEY_SCEN_5_3 + " INTEGER," + KEY_SCEN_5_4 + " INTEGER," + KEY_SCEN_5_5 + " INTEGER,"
			+ KEY_SCEN_6_1 + " INTEGER," + KEY_SCEN_6_2 + " INTEGER," + KEY_SCEN_6_3 + " INTEGER," 
			+ KEY_SCEN_6_4 + " INTEGER," + KEY_SCEN_6_5 + " INTEGER)";
	private static final String CREATE_TABLE_CARD = "CREATE TABLE " + TABLE_CARD
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CARD_TYPE + " INTEGER,"
			+ KEY_CARD_EXPAN + " TEXT," + KEY_CARD_NAME + " TEXT)";
	private static final String CREATE_TABLE_DECK = "CREATE TABLE " + TABLE_DECK
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CARD_1 + " INTEGER,"
			+ KEY_CARD_2 + " INTEGER,"+ KEY_CARD_3 + " INTEGER,"+ KEY_CARD_4 + " INTEGER,"
			+ KEY_CARD_5 + " INTEGER,"+ KEY_CARD_6 + " INTEGER,"+ KEY_CARD_7 + " INTEGER,"
			+ KEY_CARD_8 + " INTEGER,"+ KEY_CARD_9 + " INTEGER,"+ KEY_CARD_10 + " INTEGER,"
			+ KEY_CARD_11 + " INTEGER,"+ KEY_CARD_12 + " INTEGER,"+ KEY_CARD_13 + " INTEGER,"
			+ KEY_CARD_14 + " INTEGER,"+ KEY_CARD_15 + " INTEGER,"+ KEY_CARD_16 + " INTEGER,"
			+ KEY_CARD_17 + " INTEGER,"+ KEY_CARD_18 + " INTEGER,"+ KEY_CARD_19 + " INTEGER,"
			+ KEY_CARD_20 + " INTEGER,"+ KEY_CARD_21 + " INTEGER,"+ KEY_CARD_22 + " INTEGER,"
			+ KEY_CARD_23 + " INTEGER,"+ KEY_CARD_24 + " INTEGER,"+ KEY_CARD_25 + " INTEGER)";
	
	public DatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		// create tables here
		db.execSQL(CREATE_TABLE_PARTY);
		db.execSQL(CREATE_TABLE_ADVEN);
		db.execSQL(CREATE_TABLE_CHAR);
		populateCharTable(db);
		db.execSQL(CREATE_TABLE_SKILL);
		db.execSQL(CREATE_TABLE_POWER);
		db.execSQL(CREATE_TABLE_SCENARIO);
		db.execSQL(CREATE_TABLE_CARD);
		populateCardTable(db);
		db.execSQL(CREATE_TABLE_DECK);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		// on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADVEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKILL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POWER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DECK);
		
		onCreate(db);
	}
	
	// Create a Party
	public long createParty(String party_name, Context context){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_PARTY_NAME, party_name);
		
		long partyId = db.insert(TABLE_PARTY, null, values);
		addRemovedCards(context, partyId, new ArrayList<Integer>());
		
		return partyId;
	}
	
	// Get a Party
	public Party getParty(long party_id){
		SQLiteDatabase db = this.getWritableDatabase();
		
		String selectQuery = "SELECT * FROM " + TABLE_PARTY + " WHERE "
				+ KEY_ID + " = " + party_id;
		
		Cursor c = db.rawQuery(selectQuery, null);
		
		if (c != null)
			c.moveToFirst();
		
		Party p = new Party();
		p.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		p.setName(c.getString(c.getColumnIndex(KEY_PARTY_NAME)));
		
		c.close();
		return p;
	}
	
	// Get All Parties
	public ArrayList<Party> getAllParties(){
		ArrayList<Party> parties = new ArrayList<Party>();
		String selectQuery = "SELECT * FROM " + TABLE_PARTY;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		
		if(c.moveToFirst()){
			do{
				Party p = new Party();
				p.setId(c.getInt(c.getColumnIndex(KEY_ID)));
				p.setName(c.getString(c.getColumnIndex(KEY_PARTY_NAME)));
				
				parties.add(p);
			} while (c.moveToNext());
		}

		c.close();
		return parties;
	}
	
	// Delete a Party
	public void deleteParty(long party_id){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PARTY, KEY_ID + " = ?",
				new String[] { String.valueOf(party_id) });
		ArrayList<Adventurers> adv = new ArrayList<Adventurers>();
		adv = getAllAdventurers(party_id);
		for (Adventurers i : adv){
			deleteAdventurer(i.getId(), i.getPowersId(), i.getSkillsId(), i.getDeckContentId());
		}
		
	}
	
	// Create an Adventurer
	public long createAdventurer(long party_id, long character_id, String charName){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_PARTY_ID, party_id);
		values.put(KEY_CHAR_ID, character_id);
		values.put(KEY_SKILLS_ID, createSkills(db, character_id));
		values.put(KEY_POWERS_ID, createPowers(db, character_id));
		values.put(KEY_SCEN_ID, createScenarios(db));
		values.put(KEY_DECK_ID, createDeck(db, character_id));
		values.put(KEY_SORT_ORDER, charName);
		
		long adventurerId = db.insert(TABLE_ADVEN, null, values);
		return adventurerId;
	}
	
	// Get an Adventurer
	public Adventurers getAdventurer(long adventurer_id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM " + TABLE_ADVEN + " WHERE "
				+ KEY_ID + " = " + adventurer_id;
		
		Cursor c = db.rawQuery(selectQuery, null);
		
		if (c != null)
			c.moveToFirst();
				
		Adventurers adven = new Adventurers();
		adven.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		adven.setPartyId(c.getInt(c.getColumnIndex(KEY_PARTY_ID)));
		adven.setCharacterId(c.getInt(c.getColumnIndex(KEY_CHAR_ID)));
		adven.setPowersId(c.getInt(c.getColumnIndex(KEY_POWERS_ID)));
		adven.setSkillsId(c.getInt(c.getColumnIndex(KEY_SKILLS_ID)));
		adven.setScenariosId(c.getInt(c.getColumnIndex(KEY_SCEN_ID)));
		adven.setDeckContentId(c.getInt(c.getColumnIndex(KEY_DECK_ID)));
		adven.setSortOrder(c.getInt(c.getColumnIndex(KEY_SORT_ORDER)));

		c.close();
		return adven;
	}
	
	// Get all Adventurers in Party
	public ArrayList<Adventurers> getAllAdventurers(long party_id){
		ArrayList<Adventurers> advens = new ArrayList<Adventurers>();
		
		String selectQuery = "SELECT * FROM " + TABLE_ADVEN + " WHERE " 
				+ TABLE_ADVEN + "." + KEY_PARTY_ID + " = " + party_id + " ORDER BY " 
				+ KEY_SORT_ORDER + ", " + KEY_CHAR_ID + " ASC";
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		
		if (c.moveToFirst()){
			do{
				Adventurers adven = new Adventurers();
				adven.setId(c.getInt(c.getColumnIndex(KEY_ID)));
				adven.setPartyId(c.getInt(c.getColumnIndex(KEY_PARTY_ID)));
				adven.setCharacterId(c.getInt(c.getColumnIndex(KEY_CHAR_ID)));
				adven.setPowersId(c.getInt(c.getColumnIndex(KEY_POWERS_ID)));
				adven.setSkillsId(c.getInt(c.getColumnIndex(KEY_SKILLS_ID)));
				adven.setDeckContentId(c.getInt(c.getColumnIndex(KEY_DECK_ID)));
				adven.setSortOrder(c.getInt(c.getColumnIndex(KEY_SORT_ORDER)));
				adven.setDescription(c.getString(c.getColumnIndex(KEY_DESC)));
				
				advens.add(adven);
			} while (c.moveToNext());
		}

		c.close();
		return advens;
	}
	
	// Get an Adventurer
	public String getAdventurerDescription(long adventurer_id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM " + TABLE_ADVEN + " WHERE "
				+ KEY_ID + " = " + adventurer_id;
		
		Cursor c = db.rawQuery(selectQuery, null);
		
		if (c != null)
			c.moveToFirst();
				
		String desc = c.getString(c.getColumnIndex(KEY_DESC));
		
		c.close();
		return desc;
	}
	
	// Update Adventurer Description
		public void updateAdventurerDescription(long adventurer_id, String desc){
			SQLiteDatabase db = this.getReadableDatabase();
			ContentValues cv = new ContentValues();
			
			cv.put(KEY_DESC, desc);
			
			db.update(TABLE_ADVEN, cv, KEY_ID + " = ?", new String[] { String.valueOf(adventurer_id) });
		}
	
	// Delete Adventurer
	public void deleteAdventurer(long adventurer_id, long powers_id, long skills_id, long deck_id){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_ADVEN, KEY_ID + " = ?", 
				new String[] { String.valueOf(adventurer_id) });
		deletePowers(db, powers_id);
		deleteSkills(db, skills_id);
		deleteDeck(db, deck_id);
		
	}
	
	// Populate Character Table
	public void populateCharTable(SQLiteDatabase db){		
		charTableHelper(db, "Amiri");
		charTableHelper(db, "Ezren");
		charTableHelper(db, "Harsk");
		charTableHelper(db, "Kyra");
		charTableHelper(db, "Lem");
		charTableHelper(db, "Lini");
		charTableHelper(db, "Merisiel");
		charTableHelper(db, "Sajan");
		charTableHelper(db, "Seelah");
		charTableHelper(db, "Seoni");
		charTableHelper(db, "Valeros");
	}
	
	// Populate Character Table Helper
	public void charTableHelper(SQLiteDatabase db, String name){
		ContentValues values = new ContentValues();
		values.put(KEY_CHAR_NAME, name);
		db.insert(TABLE_CHAR, null, values);
	}
	
	// Get Character Name
	public String getCharName(long char_id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM " + TABLE_CHAR + " WHERE "
				+ KEY_ID + " = " + char_id;
		
		Cursor c = db.rawQuery(selectQuery, null);
		
		if (c != null)
			c.moveToFirst();
		
		String name = c.getString(c.getColumnIndex(KEY_CHAR_NAME));
		return name;
	}
	
	// Create Skills List
	public long createSkills(SQLiteDatabase db, long char_id){
		int[] values = null;
		
		int[] amiValues = {12,6,8,4,6,6,1,5,0,0,0,15,21,0,0,0,0,0,0,0,0,0};
		int[] ezrValues = {6,6,4,12,8,6,4,4,0,0,0,4,13,0,0,0,0,0,0,0,0,0};
		int[] harValues = {6,8,12,6,6,4,2,3,5,5,0,18,11,17,20,0,0,0,0,0,0,0};
		int[] kyrValues = {6,4,6,6,12,6,1,3,5,0,0,15,12,10,0,0,0,0,0,0,0,0};
		int[] lemValues = {4,8,6,6,6,10,4,6,6,6,0,14,2,6,8,0,0,0,0,0,0,0};
		int[] linValues = {4,6,8,6,10,8,4,5,5,0,0,14,9,20,0,0,0,0,0,0,0,0};
		int[] merValues = {8,12,6,4,6,6,2,2,2,5,0,1,7,19,17,0,0,0,0,0,0,0};
		int[] sajValues = {6,10,6,6,8,6,2,3,0,0,0,1,11,0,0,0,0,0,0,0,0,0};
		int[] seeValues = {8,4,8,4,8,10,1,5,0,0,0,15,10,0,0,0,0,0,0,0,0,0};
		int[] seoValues = {4,8,6,6,6,12,6,6,0,0,0,3,5,0,0,0,0,0,0,0,0,0};
		int[] valValues = {10,8,8,6,4,6,1,6,0,0,0,16,5,0,0,0,0,0,0,0,0,0};
		
		switch((int) char_id - 1){
		case 0:
			values = amiValues; break;
		case 1:
			values = ezrValues; break;
		case 2:
			values = harValues; break;
		case 3:
			values = kyrValues; break;
		case 4:
			values = lemValues; break;
		case 5:
			values = linValues; break;
		case 6:
			values = merValues; break;
		case 7:
			values = sajValues; break;
		case 8:
			values = seeValues; break;
		case 9:
			values = seoValues; break;
		case 10:
			values = valValues; break;
		}
		
		ContentValues cv = new ContentValues();
		
		cv.put(KEY_SKILLS_1, values[0]);
		cv.put(KEY_SKILLS_2, values[1]);
		cv.put(KEY_SKILLS_3, values[2]);
		cv.put(KEY_SKILLS_4, values[3]);
		cv.put(KEY_SKILLS_5, values[4]);
		cv.put(KEY_SKILLS_6, values[5]);
		cv.put(KEY_BONUS_1, values[6]);
		cv.put(KEY_BONUS_2, values[7]);
		cv.put(KEY_BONUS_3, values[8]);
		cv.put(KEY_BONUS_4, values[9]);
		cv.put(KEY_BONUS_5, values[10]);
		cv.put(KEY_DATA_1, values[11]);
		cv.put(KEY_DATA_2, values[12]);
		cv.put(KEY_DATA_3, values[13]);
		cv.put(KEY_DATA_4, values[14]);
		cv.put(KEY_DATA_5, values[15]);
		cv.put(KEY_UPGR_1, values[16]);
		cv.put(KEY_UPGR_2, values[17]);
		cv.put(KEY_UPGR_3, values[18]);
		cv.put(KEY_UPGR_4, values[19]);
		cv.put(KEY_UPGR_5, values[20]);
		cv.put(KEY_UPGR_6, values[21]);
		
		long skills_id = db.insert(TABLE_SKILL, null, cv);
		return skills_id;
	}
	
	// Update Skills
	public void updateSkill(long skills_id, int which, int value){
		SQLiteDatabase db = this.getReadableDatabase();
		ContentValues cv = new ContentValues();
		Log.e("Database Helper", "Skill ID: " + which + " New Value: " + value);
		switch(which){
		case 11:
			cv.put(KEY_BONUS_5, value); break;
		case 12:
			cv.put(KEY_DATA_5, value); break;
		case 16:
			cv.put(KEY_UPGR_1, value); break;
		case 17:
			cv.put(KEY_UPGR_2, value); break;
		case 18:
			cv.put(KEY_UPGR_3, value); break;
		case 19:
			cv.put(KEY_UPGR_4, value); break;
		case 20:
			cv.put(KEY_UPGR_5, value); break;
		case 21:
			cv.put(KEY_UPGR_6, value); break;
		}
		
		db.update(TABLE_SKILL, cv, KEY_ID + " = ?", new String[] { String.valueOf(skills_id) });
	}
	
	// Get a Skills Object
	public Skills getSkills(long skills_id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM " + TABLE_SKILL + " WHERE " 
				+ KEY_ID + " = " + skills_id;
		
		Cursor c = db.rawQuery(selectQuery, null);
		
		if(c != null)
			c.moveToFirst();
		
		Skills s = new Skills();
		s.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		s.setSkill(1,c.getInt(c.getColumnIndex(KEY_SKILLS_1)));
		s.setSkill(2,c.getInt(c.getColumnIndex(KEY_SKILLS_2)));
		s.setSkill(3,c.getInt(c.getColumnIndex(KEY_SKILLS_3)));
		s.setSkill(4,c.getInt(c.getColumnIndex(KEY_SKILLS_4)));
		s.setSkill(5,c.getInt(c.getColumnIndex(KEY_SKILLS_5)));
		s.setSkill(6,c.getInt(c.getColumnIndex(KEY_SKILLS_6)));
		s.setSkill(7,c.getInt(c.getColumnIndex(KEY_BONUS_1)));
		s.setSkill(8,c.getInt(c.getColumnIndex(KEY_BONUS_2)));
		s.setSkill(9,c.getInt(c.getColumnIndex(KEY_BONUS_3)));
		s.setSkill(10,c.getInt(c.getColumnIndex(KEY_BONUS_4)));
		s.setSkill(11,c.getInt(c.getColumnIndex(KEY_BONUS_5)));
		s.setSkill(12,c.getInt(c.getColumnIndex(KEY_DATA_1)));
		s.setSkill(13,c.getInt(c.getColumnIndex(KEY_DATA_2)));
		s.setSkill(14,c.getInt(c.getColumnIndex(KEY_DATA_3)));
		s.setSkill(15,c.getInt(c.getColumnIndex(KEY_DATA_4)));
		s.setSkill(16,c.getInt(c.getColumnIndex(KEY_DATA_5)));
		s.setSkill(17,c.getInt(c.getColumnIndex(KEY_UPGR_1)));
		s.setSkill(18,c.getInt(c.getColumnIndex(KEY_UPGR_2)));
		s.setSkill(19,c.getInt(c.getColumnIndex(KEY_UPGR_3)));
		s.setSkill(20,c.getInt(c.getColumnIndex(KEY_UPGR_4)));
		s.setSkill(21,c.getInt(c.getColumnIndex(KEY_UPGR_5)));
		s.setSkill(22,c.getInt(c.getColumnIndex(KEY_UPGR_6)));
		
		return s;
	}
	
	// Delete a Skills Object
	public void deleteSkills(SQLiteDatabase db, long skills_id){
		db.delete(TABLE_SKILL, KEY_ID + " = ?",
				new String[] { String.valueOf(skills_id) });
	}
	
	// Create a Powers Object
	public long createPowers(SQLiteDatabase db, long char_id){
		int[] values = null;
		
		int[] amiValues = {4,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1};
		int[] ezrValues = {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int[] harValues = {5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int[] kyrValues = {5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int[] lemValues = {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int[] linValues = {5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int[] merValues = {5,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0};
		int[] sajValues = {4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int[] seeValues = {4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int[] seoValues = {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int[] valValues = {4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		
		switch((int) char_id - 1){
		case 0:
			values = amiValues; break;
		case 1:
			values = ezrValues; break;
		case 2:
			values = harValues; break;
		case 3:
			values = kyrValues; break;
		case 4:
			values = lemValues; break;
		case 5:
			values = linValues; break;
		case 6:
			values = merValues; break;
		case 7:
			values = sajValues; break;
		case 8:
			values = seeValues; break;
		case 9:
			values = seoValues; break;
		case 10:
			values = valValues; break;
		}
		
		ContentValues cv = new ContentValues();
		
		cv.put(KEY_POWER_1, values[0]);
		cv.put(KEY_POWER_2, values[1]);
		cv.put(KEY_POWER_3, values[2]);
		cv.put(KEY_POWER_4, values[3]);
		cv.put(KEY_POWER_5, values[4]);
		cv.put(KEY_POWER_6, values[5]);
		cv.put(KEY_POWER_7, values[6]);
		cv.put(KEY_POWER_8, values[7]);
		cv.put(KEY_POWER_9, values[8]);
		cv.put(KEY_POWER_10, values[9]);
		cv.put(KEY_POWER_11, values[10]);
		cv.put(KEY_POWER_12, values[11]);
		cv.put(KEY_POWER_13, values[12]);
		cv.put(KEY_POWER_14, values[13]);
		cv.put(KEY_PROF_1, values[14]);
		cv.put(KEY_PROF_2, values[15]);
		cv.put(KEY_PROF_3, values[16]);
		
		long powers_id = db.insert(TABLE_POWER, null, cv);
		return powers_id;
	}
	
	// Update a Power
	public void updatePower(long powers_id, int which, int value){
		SQLiteDatabase db = this.getReadableDatabase();
		ContentValues cv = new ContentValues();
		String powerKey = null;
		
		switch((int) which){
		case 1:
			powerKey = KEY_POWER_1; break;
		case 2:
			powerKey = KEY_POWER_2; break;
		case 3:
			powerKey = KEY_POWER_3; break;
		case 4:
			powerKey = KEY_POWER_4; break;
		case 5:
			powerKey = KEY_POWER_5; break;
		case 6:
			powerKey = KEY_POWER_6; break;
		case 7:
			powerKey = KEY_POWER_7; break;
		case 8:
			powerKey = KEY_POWER_8; break;
		case 9:
			powerKey = KEY_POWER_9; break;
		case 10:
			powerKey = KEY_POWER_10; break;
		case 11:
			powerKey = KEY_POWER_11; break;
		case 12:
			powerKey = KEY_POWER_12; break;
		case 13:
			powerKey = KEY_POWER_13; break;
		case 14:
			powerKey = KEY_POWER_14; break;
		case 15:
			powerKey = KEY_PROF_1; break;
		case 16:
			powerKey = KEY_PROF_2; break;
		case 17:
			powerKey = KEY_PROF_3; break;
		}
		
		cv.put(powerKey, value);
		
		db.update(TABLE_POWER, cv, KEY_ID + " = ?", new String[] { String.valueOf(powers_id) });
	}
	
	// Get a Powers Object
	public Powers getPowers(long powers_id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM " + TABLE_POWER + " WHERE " 
				+ KEY_ID + " = " + powers_id;
		
		Cursor c = db.rawQuery(selectQuery, null);
		
		if(c != null)
			c.moveToFirst();
		
		Powers p = new Powers();
		p.setPower(1,c.getInt(c.getColumnIndex(KEY_POWER_1)));
		p.setPower(2,c.getInt(c.getColumnIndex(KEY_POWER_2)));
		p.setPower(3,c.getInt(c.getColumnIndex(KEY_POWER_3)));
		p.setPower(4,c.getInt(c.getColumnIndex(KEY_POWER_4)));
		p.setPower(5,c.getInt(c.getColumnIndex(KEY_POWER_5)));
		p.setPower(6,c.getInt(c.getColumnIndex(KEY_POWER_6)));
		p.setPower(7,c.getInt(c.getColumnIndex(KEY_POWER_7)));
		p.setPower(8,c.getInt(c.getColumnIndex(KEY_POWER_8)));
		p.setPower(9,c.getInt(c.getColumnIndex(KEY_POWER_9)));
		p.setPower(10,c.getInt(c.getColumnIndex(KEY_POWER_10)));
		p.setPower(11,c.getInt(c.getColumnIndex(KEY_POWER_11)));
		p.setPower(12,c.getInt(c.getColumnIndex(KEY_POWER_12)));
		p.setPower(13,c.getInt(c.getColumnIndex(KEY_POWER_13)));
		p.setPower(14,c.getInt(c.getColumnIndex(KEY_POWER_14)));
		p.setPower(15,c.getInt(c.getColumnIndex(KEY_PROF_1)));
		p.setPower(16,c.getInt(c.getColumnIndex(KEY_PROF_2)));
		p.setPower(17,c.getInt(c.getColumnIndex(KEY_PROF_3)));

		return p;
	}
	
	// Delete a Powers Object
	public void deletePowers(SQLiteDatabase db, long powers_id){
		db.delete(TABLE_POWER, KEY_ID + " = ?",
				new String[] { String.valueOf(powers_id) });
	}
	
	// Create a Scenario Object
	public long createScenarios(SQLiteDatabase db){		
		ContentValues cv = new ContentValues();
		
		cv.put(KEY_SCEN_0_1, 0);
		cv.put(KEY_SCEN_0_2, 0);
		cv.put(KEY_SCEN_0_3, 0);
		cv.put(KEY_SCEN_1_1, 0);
		cv.put(KEY_SCEN_1_2, 0);
		cv.put(KEY_SCEN_1_3, 0);
		cv.put(KEY_SCEN_1_4, 0);
		cv.put(KEY_SCEN_1_5, 0);
		cv.put(KEY_SCEN_2_1, 0);
		cv.put(KEY_SCEN_2_2, 0);
		cv.put(KEY_SCEN_2_3, 0);
		cv.put(KEY_SCEN_2_4, 0);
		cv.put(KEY_SCEN_2_5, 0);
		cv.put(KEY_SCEN_3_1, 0);
		cv.put(KEY_SCEN_3_2, 0);
		cv.put(KEY_SCEN_3_3, 0);
		cv.put(KEY_SCEN_3_4, 0);
		cv.put(KEY_SCEN_3_5, 0);
		cv.put(KEY_SCEN_4_1, 0);
		cv.put(KEY_SCEN_4_2, 0);
		cv.put(KEY_SCEN_4_3, 0);
		cv.put(KEY_SCEN_4_4, 0);
		cv.put(KEY_SCEN_4_5, 0);
		cv.put(KEY_SCEN_5_1, 0);
		cv.put(KEY_SCEN_5_2, 0);
		cv.put(KEY_SCEN_5_3, 0);
		cv.put(KEY_SCEN_5_4, 0);
		cv.put(KEY_SCEN_5_5, 0);
		cv.put(KEY_SCEN_6_1, 0);
		cv.put(KEY_SCEN_6_2, 0);
		cv.put(KEY_SCEN_6_3, 0);
		cv.put(KEY_SCEN_6_4, 0);
		cv.put(KEY_SCEN_6_5, 0);
		
		long scen_id = db.insert(TABLE_SCEN, null, cv);
		return scen_id;
	}
	
	// Get Scenario Object
	public Scenarios getScenarios(long scen_id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM " + TABLE_SCEN + " WHERE " 
				+ KEY_ID + " = " + scen_id;
		
		Cursor c = db.rawQuery(selectQuery, null);
		
		if(c != null)
			c.moveToFirst();
		
		Scenarios s = new Scenarios();
		s.setScenario(0,1,c.getInt(c.getColumnIndex(KEY_SCEN_0_1)));
		s.setScenario(0,2,c.getInt(c.getColumnIndex(KEY_SCEN_0_2)));
		s.setScenario(0,3,c.getInt(c.getColumnIndex(KEY_SCEN_0_3)));
		s.setScenario(1,1,c.getInt(c.getColumnIndex(KEY_SCEN_1_1)));
		s.setScenario(1,2,c.getInt(c.getColumnIndex(KEY_SCEN_1_2)));
		s.setScenario(1,3,c.getInt(c.getColumnIndex(KEY_SCEN_1_3)));
		s.setScenario(1,4,c.getInt(c.getColumnIndex(KEY_SCEN_1_4)));
		s.setScenario(1,5,c.getInt(c.getColumnIndex(KEY_SCEN_1_5)));
		s.setScenario(2,1,c.getInt(c.getColumnIndex(KEY_SCEN_2_1)));
		s.setScenario(2,2,c.getInt(c.getColumnIndex(KEY_SCEN_2_2)));
		s.setScenario(2,3,c.getInt(c.getColumnIndex(KEY_SCEN_2_3)));
		s.setScenario(2,4,c.getInt(c.getColumnIndex(KEY_SCEN_2_4)));
		s.setScenario(2,5,c.getInt(c.getColumnIndex(KEY_SCEN_2_5)));
		s.setScenario(3,1,c.getInt(c.getColumnIndex(KEY_SCEN_3_1)));
		s.setScenario(3,2,c.getInt(c.getColumnIndex(KEY_SCEN_3_2)));
		s.setScenario(3,3,c.getInt(c.getColumnIndex(KEY_SCEN_3_3)));
		s.setScenario(3,4,c.getInt(c.getColumnIndex(KEY_SCEN_3_4)));
		s.setScenario(3,5,c.getInt(c.getColumnIndex(KEY_SCEN_3_5)));
		s.setScenario(4,1,c.getInt(c.getColumnIndex(KEY_SCEN_4_1)));
		s.setScenario(4,2,c.getInt(c.getColumnIndex(KEY_SCEN_4_2)));
		s.setScenario(4,3,c.getInt(c.getColumnIndex(KEY_SCEN_4_3)));
		s.setScenario(4,4,c.getInt(c.getColumnIndex(KEY_SCEN_4_4)));
		s.setScenario(4,5,c.getInt(c.getColumnIndex(KEY_SCEN_4_5)));
		s.setScenario(5,1,c.getInt(c.getColumnIndex(KEY_SCEN_5_1)));
		s.setScenario(5,2,c.getInt(c.getColumnIndex(KEY_SCEN_5_2)));
		s.setScenario(5,3,c.getInt(c.getColumnIndex(KEY_SCEN_5_3)));
		s.setScenario(5,4,c.getInt(c.getColumnIndex(KEY_SCEN_5_4)));
		s.setScenario(5,5,c.getInt(c.getColumnIndex(KEY_SCEN_5_5)));
		s.setScenario(6,1,c.getInt(c.getColumnIndex(KEY_SCEN_6_1)));
		s.setScenario(6,2,c.getInt(c.getColumnIndex(KEY_SCEN_6_2)));
		s.setScenario(6,3,c.getInt(c.getColumnIndex(KEY_SCEN_6_3)));
		s.setScenario(6,4,c.getInt(c.getColumnIndex(KEY_SCEN_6_4)));
		s.setScenario(6,5,c.getInt(c.getColumnIndex(KEY_SCEN_6_5)));

		return s;
	}
	
	// Update a Scenario
		public void updateScenario(long scenario_id, int scenValue, int value){
			SQLiteDatabase db = this.getReadableDatabase();
			ContentValues cv = new ContentValues();
			String scenKey = null;
			
			switch(scenValue + 1){
			case 1:
				scenKey = KEY_SCEN_0_1; break;
			case 2:
				scenKey = KEY_SCEN_0_2; break;
			case 3:
				scenKey = KEY_SCEN_0_3; break;
			case 4:
				scenKey = KEY_SCEN_1_1; break;
			case 5:
				scenKey = KEY_SCEN_1_2; break;
			case 6:
				scenKey = KEY_SCEN_1_3; break;
			case 7:
				scenKey = KEY_SCEN_1_4; break;
			case 8:
				scenKey = KEY_SCEN_1_5; break;
			case 9:
				scenKey = KEY_SCEN_2_1; break;
			case 10:
				scenKey = KEY_SCEN_2_2; break;
			case 11:
				scenKey = KEY_SCEN_2_3; break;
			case 12:
				scenKey = KEY_SCEN_2_4; break;
			case 13:
				scenKey = KEY_SCEN_2_5; break;
			case 14:
				scenKey = KEY_SCEN_3_1; break;
			case 15:
				scenKey = KEY_SCEN_3_2; break;
			case 16:
				scenKey = KEY_SCEN_3_3; break;
			case 17:
				scenKey = KEY_SCEN_3_4; break;
			case 18:
				scenKey = KEY_SCEN_3_5; break;
			case 19:
				scenKey = KEY_SCEN_4_1; break;
			case 20:
				scenKey = KEY_SCEN_4_2; break;
			case 21:
				scenKey = KEY_SCEN_4_3; break;
			case 22:
				scenKey = KEY_SCEN_4_4; break;
			case 23:
				scenKey = KEY_SCEN_4_5; break;
			case 24:
				scenKey = KEY_SCEN_5_1; break;
			case 25:
				scenKey = KEY_SCEN_5_2; break;
			case 26:
				scenKey = KEY_SCEN_5_3; break;
			case 27:
				scenKey = KEY_SCEN_5_4; break;
			case 28:
				scenKey = KEY_SCEN_5_5; break;
			case 29:
				scenKey = KEY_SCEN_6_1; break;
			case 30:
				scenKey = KEY_SCEN_6_2; break;
			case 31:
				scenKey = KEY_SCEN_6_3; break;
			case 32:
				scenKey = KEY_SCEN_6_4; break;
			case 33:
				scenKey = KEY_SCEN_6_5; break;
			}
			
			cv.put(scenKey, value);
			
			db.update(TABLE_SCEN, cv, KEY_ID + " = ?", new String[] { String.valueOf(scenario_id) });
		}
		
		// Populate Card Table
		public void populateCardTable(SQLiteDatabase db){
			int[] cardName = {R.string.weapon_01,R.string.weapon_02,R.string.weapon_03,R.string.weapon_04,R.string.weapon_05,R.string.weapon_06,R.string.weapon_07,R.string.weapon_08,R.string.weapon_09,R.string.weapon_10,R.string.weapon_11,R.string.weapon_12,R.string.weapon_13,R.string.weapon_14,R.string.weapon_15,R.string.weapon_16,R.string.weapon_17,R.string.weapon_18,R.string.weapon_19,R.string.weapon_20,R.string.weapon_21,R.string.weapon_22,R.string.weapon_23,R.string.weapon_24,R.string.weapon_25,R.string.weapon_26,R.string.weapon_27,R.string.weapon_28,R.string.weapon_29,
					R.string.weapon_30,R.string.weapon_31,R.string.weapon_32,R.string.weapon_33,R.string.weapon_34,R.string.weapon_35,R.string.weapon_36,R.string.weapon_37,R.string.weapon_38,R.string.weapon_39,R.string.weapon_40,R.string.weapon_41,R.string.weapon_42,R.string.weapon_43,R.string.weapon_44,R.string.weapon_45,R.string.weapon_46,R.string.weapon_47,R.string.weapon_48,R.string.weapon_49,R.string.weapon_50,R.string.weapon_51,R.string.weapon_52,R.string.weapon_53,R.string.weapon_54,R.string.weapon_55,R.string.weapon_56,R.string.weapon_57,R.string.weapon_58,R.string.weapon_59,
					R.string.weapon_60,R.string.weapon_61,R.string.spell_01,R.string.spell_02,R.string.spell_03,R.string.spell_04,R.string.spell_05,R.string.spell_06,R.string.spell_07,R.string.spell_08,R.string.spell_09,R.string.spell_10,R.string.spell_11,R.string.spell_12,R.string.spell_13,R.string.spell_14,R.string.spell_15,R.string.spell_16,R.string.spell_17,R.string.spell_18,R.string.spell_19,R.string.spell_20,R.string.spell_21,R.string.spell_22,R.string.spell_23,R.string.spell_24,R.string.spell_25,R.string.spell_26,R.string.spell_27,R.string.spell_28,R.string.spell_29,R.string.spell_30,
					R.string.spell_31,R.string.spell_32,R.string.spell_33,R.string.spell_34,R.string.spell_35,R.string.spell_36,R.string.spell_37,R.string.spell_38,R.string.spell_39,R.string.spell_40,R.string.spell_41,R.string.spell_42,R.string.spell_43,R.string.spell_44,R.string.spell_45,R.string.spell_46,R.string.spell_47,R.string.spell_48,R.string.spell_49,R.string.spell_50,R.string.armor_01,R.string.armor_02,R.string.armor_03,R.string.armor_04,R.string.armor_05,R.string.armor_06,R.string.armor_07,R.string.armor_08,R.string.armor_09,R.string.armor_10,R.string.armor_11,R.string.armor_12,
					R.string.armor_13,R.string.armor_14,R.string.armor_15,R.string.armor_16,R.string.armor_17,R.string.armor_18,R.string.armor_19,R.string.armor_20,R.string.armor_21,R.string.armor_22,R.string.armor_23,R.string.armor_24,R.string.armor_25,R.string.armor_26,R.string.armor_27,R.string.armor_28,R.string.armor_29,R.string.armor_30,R.string.item_01,R.string.item_02,R.string.item_03,R.string.item_04,R.string.item_05,R.string.item_06,R.string.item_07,R.string.item_08,R.string.item_09,R.string.item_10,R.string.item_11,R.string.item_12,R.string.item_13,R.string.item_14,R.string.item_15,
					R.string.item_16,R.string.item_17,R.string.item_18,R.string.item_19,R.string.item_20,R.string.item_21,R.string.item_22,R.string.item_23,R.string.item_24,R.string.item_25,R.string.item_26,R.string.item_27,R.string.item_28,R.string.item_29,R.string.item_30,R.string.item_31,R.string.item_32,R.string.item_33,R.string.item_34,R.string.item_35,R.string.item_36,R.string.item_37,R.string.item_38,R.string.item_39,R.string.item_40,R.string.item_41,R.string.item_42,R.string.item_43,R.string.item_44,R.string.item_45,R.string.item_46,R.string.item_47,R.string.item_48,R.string.item_49,
					R.string.item_50,R.string.item_51,R.string.item_52,R.string.item_53,R.string.item_54,R.string.item_55,R.string.item_56,R.string.item_57,R.string.item_58,R.string.item_59,R.string.item_60,R.string.item_61,R.string.item_62,R.string.item_63,R.string.item_64,R.string.item_65,R.string.ally_01,R.string.ally_02,R.string.ally_03,R.string.ally_04,R.string.ally_05,R.string.ally_06,R.string.ally_07,R.string.ally_08,R.string.ally_09,R.string.ally_10,R.string.ally_11,R.string.ally_12,R.string.ally_13,R.string.ally_14,R.string.ally_15,R.string.ally_16,R.string.ally_17,R.string.ally_18,
					R.string.ally_19,R.string.ally_20,R.string.ally_21,R.string.ally_22,R.string.ally_23,R.string.ally_24,R.string.ally_25,R.string.ally_26,R.string.ally_27,R.string.ally_28,R.string.ally_29,R.string.ally_30,R.string.ally_31,R.string.ally_32,R.string.ally_33,R.string.ally_34,R.string.ally_35,R.string.ally_36,R.string.ally_37,R.string.ally_38,R.string.ally_39,R.string.ally_40,R.string.ally_41,R.string.ally_42,R.string.ally_43,R.string.ally_44,R.string.ally_45,R.string.ally_46,R.string.ally_47,R.string.ally_48,R.string.ally_49,R.string.ally_50,R.string.ally_51,R.string.ally_52,
					R.string.ally_53,R.string.blessing_01,R.string.blessing_02,R.string.blessing_03,R.string.blessing_04,R.string.blessing_05,R.string.blessing_06,R.string.blessing_07,R.string.blessing_08,R.string.blessing_09,R.string.blessing_10,R.string.blessing_11,R.string.blessing_12,R.string.blessing_13,R.string.blessing_14,R.string.blessing_15,R.string.blessing_16,R.string.blessing_17};
			int count = 0;
			
			for(int i = 0; i < 61; i++){
				cardTableHelper(db, 1, 1, this.context.getResources().getString(cardName[count]));
				count++;
			}
			for(int i = 0; i < 50; i++){
				cardTableHelper(db, 2, 1, this.context.getResources().getString(cardName[count]));
				count++;
			}
			for(int i = 0; i < 30; i++){
				cardTableHelper(db, 3, 1, this.context.getResources().getString(cardName[count]));
				count++;
			}
			for(int i = 0; i < 65; i++){
				cardTableHelper(db, 4, 1, this.context.getResources().getString(cardName[count]));
				count++;
			}
			for(int i = 0; i < 53; i++){
				cardTableHelper(db, 5, 1, this.context.getResources().getString(cardName[count]));
				count++;
			}
			for(int i = 0; i < 17; i++){
				cardTableHelper(db, 6, 1, this.context.getResources().getString(cardName[count]));
				count++;
			}
		}
		
		// Populate Character Table Helper
		public void cardTableHelper(SQLiteDatabase db, int type, int expan, String name){
			ContentValues values = new ContentValues();
			values.put(KEY_CARD_TYPE, type);
			values.put(KEY_CARD_EXPAN, expan);
			values.put(KEY_CARD_NAME, name);
			db.insert(TABLE_CARD, null, values);
		}
		
		// Get a Card
		public Card getCard(long card_id){
			SQLiteDatabase db = this.getReadableDatabase();
			
			String selectQuery = "SELECT * FROM " + TABLE_CARD + " WHERE " 
					+ KEY_ID + " = " + card_id;
			Cursor c = db.rawQuery(selectQuery, null);
			
			if(c != null)
				c.moveToFirst();
			
			Card card = new Card();
			card.setId(c.getInt(c.getColumnIndex(KEY_ID)));
			card.setCardType(c.getInt(c.getColumnIndex(KEY_CARD_TYPE)));
			card.setCardExpan(c.getInt(c.getColumnIndex(KEY_CARD_EXPAN)));
			card.setCardName(c.getString(c.getColumnIndex(KEY_CARD_NAME)));
			return card;
		}
		
		// Get Multiple Cards
		public ArrayList<Card> getCards(int type, int exp){
			ArrayList<Card> cards = new ArrayList<Card>();
			String selectQuery = "SELECT * FROM " + TABLE_CARD + " WHERE "
					+ KEY_CARD_TYPE + " = " + type + " AND " + KEY_CARD_EXPAN
					+ " = " + exp;
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor c = db.rawQuery(selectQuery, null);
			
			if(c.moveToFirst()){
				do{
					Card card = new Card();
					card.setId(c.getInt(c.getColumnIndex(KEY_ID)));
					card.setCardName(c.getString(c.getColumnIndex(KEY_CARD_NAME)));
					cards.add(card);
				} while (c.moveToNext());
			}

			c.close();
			return cards;
		}
		
		// Create a Powers Object
		public long createDeck(SQLiteDatabase db, long char_id){
			int[] values = null;
			
			int[] amiValues = {33,34,39,49,49,128,141,173,183,229,251,274,274,274,274,0,0,0,0,0,0,0,0,0,0};
			int[] ezrValues = {39,64,73,80,88,90,92,92,104,149,156,158,240,245,251,0,0,0,0,0,0,0,0,0,0};
			int[] harValues = {7,29,29,47,47,128,144,159,185,220,274,274,274,274,274,0,0,0,0,0,0,0,0,0,0};
			int[] kyrValues = {37,39,71,83,95,116,141,169,228,274,274,274,274,274,274,0,0,0,0,0,0,0,0,0,0};
			int[] lemValues = {52,71,90,100,107,157,199,215,245,254,274,274,274,274,274,0,0,0,0,0,0,0,0,0,0};
			int[] linValues = {71,71,73,83,100,107,177,191,220,222,222,274,274,274,274,0,0,0,0,0,0,0,0,0,0};
			int[] merValues = {7,10,130,156,161,180,185,199,199,215,228,274,274,274,274,0,0,0,0,0,0,0,0,0,0};
			int[] sajValues = {145,154,169,180,229,245,254,274,274,274,274,274,274,274,274,0,0,0,0,0,0,0,0,0,0};
			int[] seeValues = {34,37,49,71,125,125,141,240,251,274,274,274,274,274,274,0,0,0,0,0,0,0,0,0,0};
			int[] seoValues = {64,80,88,149,153,178,215,228,229,254,274,274,274,274,274,0,0,0,0,0,0,0,0,0,0};
			int[] valValues = {7,34,34,37,49,116,141,141,173,183,240,251,274,274,274,0,0,0,0,0,0,0,0,0,0};
			
			switch((int) char_id){
			case 1:
				values = amiValues; break;
			case 2:
				values = ezrValues; break;
			case 3:
				values = harValues; break;
			case 4:
				values = kyrValues; break;
			case 5:
				values = lemValues; break;
			case 6:
				values = linValues; break;
			case 7:
				values = merValues; break;
			case 8:
				values = sajValues; break;
			case 9:
				values = seeValues; break;
			case 10:
				values = seoValues; break;
			case 11:
				values = valValues; break;
			}
			
			ContentValues cv = new ContentValues();
			
			cv.put(KEY_CARD_1, values[0]);
			cv.put(KEY_CARD_2, values[1]);
			cv.put(KEY_CARD_3, values[2]);
			cv.put(KEY_CARD_4, values[3]);
			cv.put(KEY_CARD_5, values[4]);
			cv.put(KEY_CARD_6, values[5]);
			cv.put(KEY_CARD_7, values[6]);
			cv.put(KEY_CARD_8, values[7]);
			cv.put(KEY_CARD_9, values[8]);
			cv.put(KEY_CARD_10, values[9]);
			cv.put(KEY_CARD_11, values[10]);
			cv.put(KEY_CARD_12, values[11]);
			cv.put(KEY_CARD_13, values[12]);
			cv.put(KEY_CARD_14, values[13]);
			cv.put(KEY_CARD_15, values[14]);
			cv.put(KEY_CARD_16, values[15]);
			cv.put(KEY_CARD_17, values[16]);
			cv.put(KEY_CARD_18, values[17]);
			cv.put(KEY_CARD_19, values[18]);
			cv.put(KEY_CARD_20, values[19]);
			cv.put(KEY_CARD_21, values[20]);
			cv.put(KEY_CARD_22, values[21]);
			cv.put(KEY_CARD_23, values[22]);
			cv.put(KEY_CARD_24, values[23]);
			cv.put(KEY_CARD_25, values[24]);
			
			long deck_id = db.insert(TABLE_DECK, null, cv);
			return deck_id;
		}
		
		// Update a Deck Object
		public void updateDeck(long deck_id, int old_id, int new_id){
			SQLiteDatabase db = this.getReadableDatabase();
			ContentValues cv = new ContentValues();
			String cardKey = null;
			
			String selectQuery = "SELECT * FROM " + TABLE_DECK + " WHERE " 
					+ KEY_ID + " = " + deck_id;
			
			Cursor c = db.rawQuery(selectQuery, null);
			
			if(c != null)
				c.moveToFirst();
			
			if(c.getInt(c.getColumnIndex(KEY_CARD_1)) == old_id){ cardKey = KEY_CARD_1;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_2)) == old_id){ cardKey = KEY_CARD_2;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_3)) == old_id){ cardKey = KEY_CARD_3;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_4)) == old_id){ cardKey = KEY_CARD_4;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_5)) == old_id){ cardKey = KEY_CARD_5;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_6)) == old_id){ cardKey = KEY_CARD_6;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_7)) == old_id){ cardKey = KEY_CARD_7;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_8)) == old_id){ cardKey = KEY_CARD_8;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_9)) == old_id){ cardKey = KEY_CARD_9;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_10)) == old_id){ cardKey = KEY_CARD_10;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_11)) == old_id){ cardKey = KEY_CARD_11;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_12)) == old_id){ cardKey = KEY_CARD_12;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_13)) == old_id){ cardKey = KEY_CARD_13;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_14)) == old_id){ cardKey = KEY_CARD_14;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_15)) == old_id){ cardKey = KEY_CARD_15;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_16)) == old_id){ cardKey = KEY_CARD_16;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_17)) == old_id){ cardKey = KEY_CARD_17;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_18)) == old_id){ cardKey = KEY_CARD_18;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_19)) == old_id){ cardKey = KEY_CARD_19;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_20)) == old_id){ cardKey = KEY_CARD_20;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_21)) == old_id){ cardKey = KEY_CARD_21;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_22)) == old_id){ cardKey = KEY_CARD_22;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_23)) == old_id){ cardKey = KEY_CARD_23;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_24)) == old_id){ cardKey = KEY_CARD_24;}
			else if(c.getInt(c.getColumnIndex(KEY_CARD_25)) == old_id){ cardKey = KEY_CARD_25;}
			
			cv.put(cardKey, new_id);
			
			db.update(TABLE_DECK, cv, KEY_ID + " = ?", new String[] { String.valueOf(deck_id) });
		}
		
		// Get a Deck Object
		public DeckContent getDeck(long deck_id){
			SQLiteDatabase db = this.getReadableDatabase();
			
			String selectQuery = "SELECT * FROM " + TABLE_DECK + " WHERE " 
					+ KEY_ID + " = " + deck_id;
			
			Cursor c = db.rawQuery(selectQuery, null);
			
			if(c != null)
				c.moveToFirst();
			
			DeckContent d = new DeckContent();
			d.setCard(1, c.getInt(c.getColumnIndex(KEY_CARD_1)));
			d.setCard(2, c.getInt(c.getColumnIndex(KEY_CARD_2)));
			d.setCard(3, c.getInt(c.getColumnIndex(KEY_CARD_3)));
			d.setCard(4, c.getInt(c.getColumnIndex(KEY_CARD_4)));
			d.setCard(5, c.getInt(c.getColumnIndex(KEY_CARD_5)));
			d.setCard(6, c.getInt(c.getColumnIndex(KEY_CARD_6)));
			d.setCard(7, c.getInt(c.getColumnIndex(KEY_CARD_7)));
			d.setCard(8, c.getInt(c.getColumnIndex(KEY_CARD_8)));
			d.setCard(9, c.getInt(c.getColumnIndex(KEY_CARD_9)));
			d.setCard(10, c.getInt(c.getColumnIndex(KEY_CARD_10)));
			d.setCard(11, c.getInt(c.getColumnIndex(KEY_CARD_11)));
			d.setCard(12, c.getInt(c.getColumnIndex(KEY_CARD_12)));
			d.setCard(13, c.getInt(c.getColumnIndex(KEY_CARD_13)));
			d.setCard(14, c.getInt(c.getColumnIndex(KEY_CARD_14)));
			d.setCard(15, c.getInt(c.getColumnIndex(KEY_CARD_15)));
			d.setCard(16, c.getInt(c.getColumnIndex(KEY_CARD_16)));
			d.setCard(17, c.getInt(c.getColumnIndex(KEY_CARD_17)));
			d.setCard(18, c.getInt(c.getColumnIndex(KEY_CARD_18)));
			d.setCard(19, c.getInt(c.getColumnIndex(KEY_CARD_19)));
			d.setCard(20, c.getInt(c.getColumnIndex(KEY_CARD_20)));
			d.setCard(21, c.getInt(c.getColumnIndex(KEY_CARD_21)));
			d.setCard(22, c.getInt(c.getColumnIndex(KEY_CARD_22)));
			d.setCard(23, c.getInt(c.getColumnIndex(KEY_CARD_23)));
			d.setCard(24, c.getInt(c.getColumnIndex(KEY_CARD_24)));
			d.setCard(25, c.getInt(c.getColumnIndex(KEY_CARD_25)));
			
			return d;
			
		}
		
		// Delete a Deck Object
		public void deleteDeck(SQLiteDatabase db, long deck_id){
			db.delete(TABLE_DECK, KEY_ID + " = ?",
					new String[] { String.valueOf(deck_id) });
		}
		
		// Add Removed Cards
		public void addRemovedCards(Context context, long party_id, ArrayList<Integer> cards){
			
			RemovedCards r = new RemovedCards((int)party_id, cards);
			try
			{
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
						new File(context.getFilesDir() + String.valueOf(party_id) + "_removed_cards.bin")));
				oos.writeObject(r);
				oos.flush();
				oos.close();
			} catch(Exception e)
			{
				Log.e("Serialization Save Error: ", e.getMessage());
			}
			
		}
		
		// Get Removed Cards
		public RemovedCards getRemovedCards(Context context, long party_id){
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
						new File(context.getFilesDir() + String.valueOf(party_id) + "_removed_cards.bin")));
				Object o = ois.readObject();
				ois.close();
				return (RemovedCards) o;
			} 
			catch(Exception e)
			{
				Log.e("Serialization Read Error: ", e.getMessage());
			}
			return null;
		}

}
