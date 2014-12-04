package com.guru.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import common.DataBean;

@Entity
@Table(name = "users")
public class User implements DataBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	public User(String login, String password, int enabled, String role) {
		super();
		this.login = login;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}
	public User() {
		super();
	}
	private String login;
	private String password;
	private int enabled;
	private String  role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
