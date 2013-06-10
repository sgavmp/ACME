package com.acme.model.user;

import java.io.Serializable;

import javax.jws.HandlerChain;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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
