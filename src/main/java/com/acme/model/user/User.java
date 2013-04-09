package com.acme.model.user;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import com.acme.model.AbstractPersistable;

import com.acme.model.geography.City;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


@Entity
public class User extends AbstractPersistable<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5470250124962285595L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	@Column(nullable=false,unique=true)
	private String username;
	@Column(nullable=false)
	private String password;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER)
    @JoinColumn(name="USER_ID")
	@MapKeyEnumerated(EnumType.ORDINAL)
	@MapKeyColumn(name="ROLE_TYPE",nullable=true)
	private Map<UserType,Role> roles=Maps.newHashMap();
	@Column(nullable=false)
	private String name;
	private String surname;
	private String address;
	private String phone;
	@Column(nullable=false)
	private String mobilephone;
	@Column(nullable=false,unique=true)
	private String email;
	@ManyToOne
	private City city;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public User() {

	}

	public User(String username, String password, String name, String address,
			String phone, String mobilephone, String email, City city) {
		super();
		this.username = username;
		this.password = password;
		this.roles = Maps.newHashMap();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.mobilephone = mobilephone;
		this.email = email;
	}

	public User(String username, String password, String name, String surname,
			String address, String phone, String mobilephone, String email,
			City city) {
		super();
		this.username = username;
		this.password = password;
		this.roles = Maps.newHashMap();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone = phone;
		this.mobilephone = mobilephone;
		this.email = email;
		this.city = city;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	
	public Map<UserType, Role> getRoles() {
		return roles;
	}

	public void setRoles(Map<UserType, Role> roles) {
		this.roles = roles;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------


	public void addRoleToUser(Role rol,UserType type) {
		this.roles.put(type, rol);
	}

	public Role removeRoleToUser(UserType type) {
		Role rol=this.roles.remove(type);
		return rol;
	}
	
	public Role getRole(UserType type){
		return this.roles.get(type);
	}
}
