package com.corum.manticore.sqlite.model;

import java.util.Arrays;

public class DeckContent {
	
	int id;
	int card_1, card_2, card_3, card_4, card_5;
	int card_6, card_7, card_8, card_9, card_10;
	int card_11, card_12, card_13, card_14, card_15;
	int card_16, card_17, card_18, card_19, card_20;
	int card_21, card_22, card_23, card_24, card_25;
	
	//constructors
	public DeckContent(){
		
	}
	
	//getters
	public int getCardId(int card){
		if(card < 14){
			switch(card){
			case 1: return card_1; 
			case 2: return card_2; 
			case 3: return card_3; 
			case 4: return card_4; 
			case 5: return card_5; 
			case 6: return card_6; 
			case 7: return card_7; 
			case 8: return card_8; 
			case 9: return card_9; 
			case 10: return card_10; 
			case 11: return card_11; 
			case 12: return card_12; 
			case 13: return card_13; 
			}
		}else{
			switch(card){
			case 14: return card_14; 
			case 15: return card_15; 
			case 16: return card_16; 
			case 17: return card_17; 
			case 18: return card_18; 
			case 19: return card_19; 
			case 20: return card_20; 
			case 21: return card_21; 
			case 22: return card_22; 
			case 23: return card_23; 
			case 24: return card_24; 
			case 25: return card_25; 
			}
		}
		
		return 0;
	}
	
	public int[] getAllCards(){
		int[] cards = {card_1, card_2, card_3, card_4, card_5, card_6, card_7,
				card_8, card_9, card_10, card_11, card_12, card_13, card_14, card_15,
				card_16, card_17, card_18, card_19, card_20, card_21, card_22, card_23,
				card_24, card_25};
		Arrays.sort(cards);
		return cards;
	}
	
	//setters
	public void setCard(int which, int card_id){
		if(which < 14){
			switch(which){
			case 1: card_1 = card_id; 
			case 2: card_2 = card_id; 
			case 3: card_3 = card_id; 
			case 4: card_4 = card_id; 
			case 5: card_5 = card_id; 
			case 6: card_6 = card_id; 
			case 7: card_7 = card_id; 
			case 8: card_8 = card_id; 
			case 9: card_9 = card_id; 
			case 10: card_10 = card_id; 
			case 11: card_11 = card_id; 
			case 12: card_12 = card_id; 
			case 13: card_13 = card_id; 
			}
		}else{
			switch(which){
			case 14: card_14 = card_id; 
			case 15: card_15 = card_id; 
			case 16: card_16 = card_id; 
			case 17: card_17 = card_id; 
			case 18: card_18 = card_id; 
			case 19: card_19 = card_id; 
			case 20: card_20 = card_id; 
			case 21: card_21 = card_id; 
			case 22: card_22 = card_id; 
			case 23: card_23 = card_id; 
			case 24: card_24 = card_id; 
			case 25: card_25 = card_id; 
			}
		}
	}
	

}
