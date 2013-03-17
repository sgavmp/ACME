package com.acme.model.user;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public enum UserType {
	ADMINISTRATOR(0),
	WORKER(1),
	REVIEWER(2),
	COMPANY(3),
	CUSTOMER(4);	
	
	private final int num;
	UserType(Integer num) {
		this.num=num;
	}
	public int getValue (){
		return num;
	}
}
