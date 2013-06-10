package com.acme.model.user;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.acme.model.AbstractPersistable;

import com.acme.model.geography.City;
import com.acme.model.geography.Country;
import com.acme.model.geography.State;
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
	@NotNull(message = "user.username.null")
	@NotBlank(message = "user.username.null")
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER)
    @JoinColumn(name="USER_ID")
	@MapKeyEnumerated(EnumType.ORDINAL)
	@MapKeyColumn(name="ROLE_TYPE",nullable=true)
	private Map<UserType,Role> roles=Maps.newHashMap();
	
	@Column(nullable=false)
	@NotNull(message = "user.name.null")
	@NotBlank(message = "user.name.null")
	private String name;
	
	@NotNull(message = "user.surname.null")
	@NotBlank(message = "user.surname.null")
	private String surname;
	
	private String address;
	
	@Digits(integer=12, fraction = 0)
	@NotNull(message = "user.mobilphone.null")
	@NotBlank(message = "user.mobilphone.null")
	private String phone;
	
	@Column(nullable=false)
	@NotNull(message = "user.mobilphone.null")
	@NotBlank(message = "user.mobilphone.null")
	@Digits(integer=12, fraction = 0)
	private String mobilephone;
	
	@Column(nullable=false,unique=true)
	@NotNull(message = "user.mobilphone.null")
	@NotBlank(message = "user.mobilphone.null")
	@Email
	private String email;
	
	@ManyToOne
	@NotNull(message = "user.city.null")
	private City city;
	
	@ManyToOne
	@NotNull(message = "user.state.null")
	private State state;
	
	@ManyToOne
	@NotNull(message = "user.country.null")
	private Country country;
	
	private boolean enabled=true;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public User() {

	}
	
	//Constructor usado solo para Thymeleaf
	public User(String id) {
		this.id=Long.decode(id);
	}

	public User(String username, String password, String name, String address,
			String phone, String mobilephone, String email) {
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
			City city, State state, Country country) {
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
		this.state = state;
		this.country = country;
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
	public void setValuesFromUser(User u){
		this.setAddress(u.getAddress());
		this.setCity(u.getCity());
		this.setState(u.getState());
		this.setCountry(u.getCountry());
		this.setEmail(u.getEmail());
		this.setMobilephone(u.getMobilephone());
		this.setName(u.getName());
		if (!u.getPassword().isEmpty()) 
			this.setPassword(u.getPassword());
		this.setPhone(u.getPhone());
		this.setSurname(u.getSurname());
		this.setUsername(u.getUsername());
	}

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
