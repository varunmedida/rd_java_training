package com.epam.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@NotNull
	@Column(unique = true)
	private String userName;
	@NotNull
	private String password;
	private boolean active;
	private boolean credentialsExpired;
	private boolean accountLocked;
	private boolean accountExpired;
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
	private Set<Role> roles = new LinkedHashSet<>();

	
	public User(Long userId, @NotNull String userName, @NotNull String password, boolean active,
			boolean credentialsExpired, boolean accountLocked, boolean accountExpired, Set<Role> roles) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.active = active;
		this.credentialsExpired = credentialsExpired;
		this.accountLocked = accountLocked;
		this.accountExpired = accountExpired;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", active=" + active
				+ ", credentialsExpired=" + credentialsExpired + ", accountLocked=" + accountLocked
				+ ", accountExpired=" + accountExpired + ", roles=" + roles + "]";
	}

	public User() {

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
