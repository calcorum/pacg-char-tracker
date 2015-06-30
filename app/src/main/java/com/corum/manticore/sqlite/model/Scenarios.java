package com.corum.manticore.sqlite.model;

public class Scenarios {
	int id;
	int scenario_0_1, scenario_0_2,	scenario_0_3;
	int scenario_1_1, scenario_1_2, scenario_1_3, scenario_1_4, scenario_1_5; 
	int scenario_2_1, scenario_2_2, scenario_2_3, scenario_2_4, scenario_2_5; 
	int scenario_3_1, scenario_3_2, scenario_3_3, scenario_3_4, scenario_3_5; 
	int scenario_4_1, scenario_4_2, scenario_4_3, scenario_4_4, scenario_4_5; 
	int scenario_5_1, scenario_5_2, scenario_5_3, scenario_5_4, scenario_5_5; 
	int scenario_6_1, scenario_6_2, scenario_6_3, scenario_6_4, scenario_6_5; 

	//constructor
	public Scenarios(){
		this.scenario_0_1 = 0;
		this.scenario_0_2 = 0;
		this.scenario_0_3 = 0;
		this.scenario_1_1 = 0;
		this.scenario_1_2 = 0;
		this.scenario_1_3 = 0;
		this.scenario_1_4 = 0;
		this.scenario_1_5 = 0;
		this.scenario_2_1 = 0;
		this.scenario_2_2 = 0;
		this.scenario_2_3 = 0;
		this.scenario_2_4 = 0;
		this.scenario_2_5 = 0;
		this.scenario_3_1 = 0;
		this.scenario_3_2 = 0;
		this.scenario_3_3 = 0;
		this.scenario_3_4 = 0;
		this.scenario_3_5 = 0;
		this.scenario_4_1 = 0;
		this.scenario_4_2 = 0;
		this.scenario_4_3 = 0;
		this.scenario_4_4 = 0;
		this.scenario_4_5 = 0;
		this.scenario_5_1 = 0;
		this.scenario_5_2 = 0;
		this.scenario_5_3 = 0;
		this.scenario_5_4 = 0;
		this.scenario_5_5 = 0;
		this.scenario_6_1 = 0;
		this.scenario_6_2 = 0;
		this.scenario_6_3 = 0;
		this.scenario_6_4 = 0;
		this.scenario_6_5 = 0;
	}
	
	//setters
	public void setScenario(int adv, int scen, int val){
		switch(adv){
		case 0:
			switch(scen){
			case 1: this.scenario_0_1 = val; break;
			case 2: this.scenario_0_2 = val; break;
			case 3: this.scenario_0_3 = val; break;
			} break;
		case 1:
			switch(scen){
			case 1: this.scenario_1_1 = val; break;
			case 2: this.scenario_1_2 = val; break;
			case 3: this.scenario_1_3 = val; break;
			case 4: this.scenario_1_4 = val; break;
			case 5: this.scenario_1_5 = val; break;
			} break;
		case 2:
			switch(scen){
			case 1: this.scenario_2_1 = val; break;
			case 2: this.scenario_2_2 = val; break;
			case 3: this.scenario_2_3 = val; break;
			case 4: this.scenario_2_4 = val; break;
			case 5: this.scenario_2_5 = val; break;
			} break;
		case 3:
			switch(scen){
			case 1: this.scenario_3_1 = val; break;
			case 2: this.scenario_3_2 = val; break;
			case 3: this.scenario_3_3 = val; break;
			case 4: this.scenario_3_4 = val; break;
			case 5: this.scenario_3_5 = val; break;
			} break;
		case 4:
			switch(scen){
			case 1: this.scenario_4_1 = val; break;
			case 2: this.scenario_4_2 = val; break;
			case 3: this.scenario_4_3 = val; break;
			case 4: this.scenario_4_4 = val; break;
			case 5: this.scenario_4_5 = val; break;
			} break;
		case 5:
			switch(scen){
			case 1: this.scenario_5_1 = val; break;
			case 2: this.scenario_5_2 = val; break;
			case 3: this.scenario_5_3 = val; break;
			case 4: this.scenario_5_4 = val; break;
			case 5: this.scenario_5_5 = val; break;
			} break;
		case 6:
			switch(scen){
			case 1: this.scenario_6_1 = val; break;
			case 2: this.scenario_6_2 = val; break;
			case 3: this.scenario_6_3 = val; break;
			case 4: this.scenario_6_4 = val; break;
			case 5: this.scenario_6_5 = val; break;
			} break;
		}
	}
	
	//getters
	public int[] getScenarios(){
		int[] scenarios = {this.scenario_0_1,this.scenario_0_2,this.scenario_0_3,
				this.scenario_1_1,this.scenario_1_2,this.scenario_1_3,this.scenario_1_4,this.scenario_1_5,
				this.scenario_2_1,this.scenario_2_2,this.scenario_2_3,this.scenario_2_4,this.scenario_2_5,
				this.scenario_3_1,this.scenario_3_2,this.scenario_3_3,this.scenario_3_4,this.scenario_3_5,
				this.scenario_4_1,this.scenario_4_2,this.scenario_4_3,this.scenario_4_4,this.scenario_4_5,
				this.scenario_5_1,this.scenario_5_2,this.scenario_5_3,this.scenario_5_4,this.scenario_5_5,
				this.scenario_6_1,this.scenario_6_2,this.scenario_6_3,this.scenario_6_4,this.scenario_6_5};
		return scenarios;

	}

}
