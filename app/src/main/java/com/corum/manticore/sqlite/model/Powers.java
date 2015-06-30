package com.corum.manticore.sqlite.model;

public class Powers {
	
	int id;
	int power_1, power_2, power_3, power_4, power_5;
	int power_6, power_7, power_8, power_9, power_10;
	int power_11, power_12, power_13, power_14, power_15, power_16, power_17, power_18;
	
	//constructor
	public Powers(){
		
	}
	
	public Powers(int id, int power_1, int power_2, int power_3, int power_4, int power_5,
		int power_6, int power_7, int power_8, int power_9, int power_10,
		int power_11, int power_12, int power_13, int power_14, int power_15,
		int power_16, int power_17, int power_18){
		this.id = id;
		this.power_1 = power_1;
		this.power_2 = power_2;
		this.power_3 = power_3;
		this.power_4 = power_4;
		this.power_5 = power_5;
		this.power_6 = power_6;
		this.power_7 = power_7;
		this.power_8 = power_8;
		this.power_9 = power_9;
		this.power_10 = power_10;
		this.power_11 = power_11;
		this.power_12 = power_12;
		this.power_13 = power_13;
		this.power_14 = power_14;
		this.power_15 = power_15;
		this.power_16 = power_16;
		this.power_17 = power_17;
		this.power_18 = power_18;
	}
	
	//setter
	public void setPower(int power_id, int value){
		switch(power_id){
		case 1:
			this.power_1 = value; break;
		case 2:
			this.power_2 = value; break;
		case 3:
			this.power_3 = value; break;
		case 4:
			this.power_4 = value; break;
		case 5:
			this.power_5 = value; break;
		case 6:
			this.power_6 = value; break;
		case 7:
			this.power_7 = value; break;
		case 8:
			this.power_8 = value; break;
		case 9:
			this.power_9 = value; break;
		case 10:
			this.power_10 = value; break;
		case 11:
			this.power_11 = value; break;
		case 12:
			this.power_12 = value; break;
		case 13:
			this.power_13 = value; break;
		case 14:
			this.power_14 = value; break;
		case 15:
			this.power_15 = value; break;
		case 16:
			this.power_16 = value; break;
		case 17:
			this.power_17 = value; break;
		case 18:
			this.power_18 = value; break;
		}
	}
	
	//getter
	public int[] getPowers(){
		int[] powers = {this.power_1, this.power_2, this.power_3, this.power_4, this.power_5,
				this.power_6, this.power_7, this.power_8, this.power_9, this.power_10,
				this.power_11, this.power_12, this.power_13, this.power_14, this.power_15,
				this.power_16, this.power_17, this.power_18};
		return powers;
	}

}
