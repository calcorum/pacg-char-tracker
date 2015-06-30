package com.corum.manticore.sqlite.model;

public class Skills {
	int id;
	int skill_1, skill_2, skill_3, skill_4, skill_5, skill_6;
	int skill_7, skill_8, skill_9, skill_10, skill_11, skill_12;
	int skill_13, skill_14, skill_15, skill_16, skill_17, skill_18;
	int skill_19, skill_20, skill_21, skill_22;
	
	//constructor
	public Skills(){
		
	}
	
	public Skills(int id, int skill_1, int skill_2, int skill_3, int skill_4, int skill_5,
		int skill_6, int skill_7, int skill_8, int skill_9, int skill_10, int skill_11,
		int skill_12, int skill_13, int skill_14, int skill_15,	int skill_16, int skill_17, 
		int skill_18, int skill_19, int skill_20, int skill_21, int skill_22){
		this.id = id;
		this.skill_1 = skill_1;
		this.skill_2 = skill_2;
		this.skill_3 = skill_3;
		this.skill_4 = skill_4;
		this.skill_5 = skill_5;
		this.skill_6 = skill_6;
		this.skill_7 = skill_7;
		this.skill_8 = skill_8;
		this.skill_9 = skill_9;
		this.skill_10 = skill_10;
		this.skill_11 = skill_11;
		this.skill_12 = skill_12;
		this.skill_13 = skill_13;
		this.skill_14 = skill_14;
		this.skill_15 = skill_15;
		this.skill_16 = skill_16;
		this.skill_17 = skill_17;
		this.skill_18 = skill_18;
		this.skill_19 = skill_19;
		this.skill_20 = skill_20;
		this.skill_21 = skill_21;
		this.skill_22 = skill_22;
	}
	
	//setter
	public void setId(int id){
		this.id = id;
	}
	
	public void setSkill(int skill_id, int value){
		switch(skill_id){
		case 1:
			this.skill_1 = value; break;
		case 2:
			this.skill_2 = value; break;
		case 3:
			this.skill_3 = value; break;
		case 4:
			this.skill_4 = value; break;
		case 5:
			this.skill_5 = value; break;
		case 6:
			this.skill_6 = value; break;
		case 7:
			this.skill_7 = value; break;
		case 8:
			this.skill_8 = value; break;
		case 9:
			this.skill_9 = value; break;
		case 10:
			this.skill_10 = value; break;
		case 11:
			this.skill_11 = value; break;
		case 12:
			this.skill_12 = value; break;
		case 13:
			this.skill_13 = value; break;
		case 14:
			this.skill_14 = value; break;
		case 15:
			this.skill_15 = value; break;
		case 16:
			this.skill_16 = value; break;
		case 17:
			this.skill_17 = value; break;
		case 18:
			this.skill_18 = value; break;
		case 19:
			this.skill_19 = value; break;
		case 20:
			this.skill_20 = value; break;
		case 21: 
			this.skill_21 = value; break;
		case 22:
			this.skill_22 = value; break;
		}
	}
	
	//getter
	public int[] getSkills(){
		int[] skills = {this.skill_1, this.skill_2, this.skill_3, this.skill_4, this.skill_5,
			this.skill_6, this.skill_7, this.skill_8, this.skill_9, this.skill_10,
			this.skill_11, this.skill_12, this.skill_13, this.skill_14, this.skill_15,
			this.skill_16, this.skill_17, this.skill_18, this.skill_19, this.skill_20,
			this.skill_21, this.skill_22};
		return skills;
	}

}
