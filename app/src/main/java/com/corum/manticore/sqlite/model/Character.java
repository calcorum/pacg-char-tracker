package com.corum.manticore.sqlite.model;

public class Character {
	
	int id;
	String character_name;
	
	//constructor
	public Character(){
		
	}
	
	public Character(int id, String name){
		this.id = id;
		this.character_name = name;
	}
	
	//getter
	public String getName(){
		return this.character_name;
	}
	
	public int getId(){
		return this.id;
	}
	
	//setter
	public void setId(int id){
		this.id = id;
	}
	
	public void setName(String name){
		this.character_name = name;
	}

}
