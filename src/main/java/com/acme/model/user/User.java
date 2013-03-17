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

import com.acme.model.geography.City;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


@Entity
public class User {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private Integer id;
	private String username;
	private String password;
	private Map<UserType,Role> roles=Maps.newHashMap();
	private String name;
	private String surname;
	private String address;
	private String phone;
	private String mobilephone;
	private String email;
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
	@Column(nullable=false,unique=true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(nullable=false)
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
	@Column(nullable=false)
	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	@Column(nullable=false,unique=true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Id
    @GeneratedValue
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id=id;
	}

	@ManyToOne
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER)
    @JoinColumn(name="USER_ID")
	@MapKeyEnumerated(EnumType.ORDINAL)
	@MapKeyColumn(name="ROLE_TYPE",nullable=true)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
