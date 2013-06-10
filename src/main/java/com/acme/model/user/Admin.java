package com.acme.model.user;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("0")
public class Admin extends Role {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7930207371531530727L;

	public Admin() {
		
	}
	
	
}
