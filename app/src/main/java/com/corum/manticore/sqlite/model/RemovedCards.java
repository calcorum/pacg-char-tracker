package com.corum.manticore.sqlite.model;

import java.io.Serializable;
import java.util.ArrayList;

import android.util.Log;

public class RemovedCards implements Serializable {
	
	private static final long serialVersionUID = 1L;
	int id;
	ArrayList<Integer> cards = new ArrayList<Integer>();
	
	// constructors
	public RemovedCards(){
		
	}
	
	public RemovedCards(int id, ArrayList<Integer> c){
		this.id = id;
		this.cards = c;
	}
	
	// setters
	public void setId(int id){
		this.id = id;
	}
	
	public void setCards(ArrayList<Integer> c){
		this.cards = c;
	}
	
	public void addCard(int c){
		this.cards.add(c);
	}
	
	// getters
	public ArrayList<Integer> getCards(){
		return this.cards;
	}
	
	public int getId(){
		return this.id;
	}
	
	//functions
	public void removeCard(int card_id){
		for (int i : cards){
			if(i == card_id){
				this.cards.remove(cards.indexOf(card_id)); break;
			}
		}
	}

}
