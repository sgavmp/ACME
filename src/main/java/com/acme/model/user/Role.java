package com.acme.model.user;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.DiscriminatorFormula;

import com.acme.model.AbstractPersistable;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ROLE_TYPE")
@DiscriminatorFormula("(CASE WHEN ROLE_TYPE IS NULL THEN '4' ELSE ROLE_TYPE END)")
public abstract class Role extends AbstractPersistable<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 958553541203776049L;

	public Role() {
	}	
}
