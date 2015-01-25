package com.example.accgyro;

public class AccReadings {
	
	private String accx;
	private String accy;
	private String accz;
	private int id;

	public AccReadings() {
		// TODO Auto-generated constructor stub
	}
	
	public AccReadings(String x,String y,String z) {
		this.accx = x;
		//this.id = id;
		this.accy = y;
		this.accz  = z;
	}
	
	public void setAccId(int id) {
		this.id = id;
	}
	
	public void setAccX(String x) {
		this.accx = x;
	}
	
	public void setAccY(String y) {
		this.accy = y;
	}
	
	public void setAccZ(String z) {
		this.accz = z;
	}
	
	public int getAccId(){
		return id;
	}
	
	public String getAccX() {
		return accx;
	}
	
	public String getAccY() {
		return accy;
	}
	
	public String getAccZ() {
		return accz;
	}
	
}
	
	
	
	
	
	
	
	


