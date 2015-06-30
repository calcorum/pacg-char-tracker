package com.corum.manticore.sqlite.model;

public class Adventurers {
	
	int id;
	int party_id;
	int character_id;
	int powers_id;
	int skills_id;
	int scenarios_id;
	int deckcon_id;
	int deckcount_id;
	int sort_order;
	String description;
	
	//constructor
	public Adventurers(){
		
	}
	
	public Adventurers(int party_id, int character_id, int powers_id, int skills_id, 
			int scenarios_id, int deckcon_id, int deckcount_id, int sort_order, String description){
		this.party_id = party_id;
		this.character_id = character_id;
		this.powers_id = powers_id;
		this.skills_id = skills_id;
		this.scenarios_id = scenarios_id;
		this.deckcon_id = deckcon_id;
		this.deckcount_id = deckcount_id;
		this.sort_order = sort_order;
		this.description = description;
	}
	
	//getters
	public int getId(){
		return this.id;
	}
	
	public int getPartyId(){
		return this.party_id;
	}
	
	public int getCharacterId(){
		return this.character_id;
	}
	
	public int getPowersId(){
		return this.powers_id;
	}
	
	public int getSkillsId(){
		return this.skills_id;		
	}
	
	public int getScenariosId(){
		return this.scenarios_id;
	}
	
	public int getDeckContentId(){
		return this.deckcon_id;
	}
	
	public int getDeckCountId(){
		return this.deckcount_id;
	}
	
	public int getSortOrder(){
		return this.sort_order;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getAllInfo(){
		return "Adv ID: " + this.id + " Party ID: " + this.party_id + " Char ID: " + this.character_id
				+ " Powers ID: " + this.powers_id + " Skills ID: " + this.skills_id;
	}
	
	//setters
	public void setId(int id){
		this.id = id;
	}
		
	public void setPartyId(int id){
		this.party_id = id; 
	}

	public void setCharacterId(int id){
		this.character_id = id; 
	}
		
	public void setPowersId(int id){
		this.powers_id = id; 
	}
		
	public void setSkillsId(int id){
		this.skills_id = id; 		
	}
	
	public void setScenariosId(int id){
		this.scenarios_id = id;
	}
		
	public void setDeckContentId(int id){
		this.deckcon_id = id; 
	}
		
	public void setDeckCountId(int id){
		this.deckcount_id = id; 
	}
	
	public void setSortOrder(int order){
		this.sort_order = order;
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}

}
