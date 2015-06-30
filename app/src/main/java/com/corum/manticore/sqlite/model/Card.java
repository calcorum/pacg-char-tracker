package com.corum.manticore.sqlite.model;

public class Card {
	int id;
	int card_type;
	int card_expan;
	String card_name;
	
	//constructors
	public Card(){
		
	}
	
	public Card(int id, int type, int expan, String name){
		this.id = id;
		this.card_type = type;
		this.card_expan = expan;
		this.card_name = name;
	}
	
	//getters
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.card_name;
	}
	
	public int getType(){
		return this.card_type;
	}
	
	public int getExpan(){
		return this.card_expan;
	}
	
	//setters
	public void setId(int id){
		this.id = id;
	}
	
	public void setCardType(int type){
		this.card_type = type;
	}
	
	public void setCardName(String name){
		this.card_name = name;
	}
	
	public void setCardExpan(int expan){
		this.card_expan = expan;
	}

}
