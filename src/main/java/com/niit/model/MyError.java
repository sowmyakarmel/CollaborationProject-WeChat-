package com.niit.model;

public class MyError {

	private int erno;
	private String message;
	
	public MyError(int a, String msg){
		super();
		erno=a;
		message=msg;
	}
	public int getErno() {
		return erno;
	}
	public void setErno(int erno) {
		this.erno = erno;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
