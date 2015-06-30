package com.corum.manticore.sqlite.model;

public class Party {
	
	int id;
	String party_name;
	
	//constructor
	public Party(){
		
	}
	
	public Party(int id, String party_name){
		this.id = id;
		this.party_name = party_name;
	}
	
	//setter
	public void setName(String party_name){
		this.party_name = party_name;
	}

	public void setId(int id){
		this.id = id;
	}
	
	//getter
	public String getName(){
		return this.party_name;
	}
	
	public int getId(){
		return this.id;
	}

}
