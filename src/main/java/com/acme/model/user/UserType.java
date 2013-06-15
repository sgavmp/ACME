package com.acme.model.user;


public enum UserType {
	ROLE_ADMIN(0),
	ROLE_WORKER(1),
	ROLE_REVIEWER(2),
	ROLE_COMPANY(3),
	ROLE_CUSTOMER(4);	
	
	private final int num;
	UserType(Integer num) {
		this.num=num;
	}
	public int getValue (){
		return num;
	}
}
