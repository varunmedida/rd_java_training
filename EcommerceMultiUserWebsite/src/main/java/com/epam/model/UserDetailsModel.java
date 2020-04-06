package com.epam.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class UserDetailsModel implements UserDetails {

	private String userName;
	private String password;
	private boolean active;
	private boolean credentialsExpired;
	private boolean accountLocked;
	private boolean accountExpired;
	private List<GrantedAuthority> authorities= new ArrayList<>();

	public UserDetailsModel(User user) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.active = user.isActive();
		this.credentialsExpired = user.isCredentialsExpired();
		this.accountLocked = user.isAccountLocked();
		this.accountExpired = user.isAccountExpired();
		user.getRoles().forEach(role -> this.authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

}
