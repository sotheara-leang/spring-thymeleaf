package com.example.springthymeleaf.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

// map this class to table user - default
@Table
// set this is entity
@Entity
// User must implement UserDetails to used in authentication object
// CredentialsContainer mean the password will be removed, see eraseCredentials()
public class User implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = -5227739888866155529L;
	
	/**
	 * Mark this field as id and auto-generated.
	 * default GenerationType.Auto. Provider will decide on id generating
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	// Mark max size for username
	// this field will map to column username in table user
	// in case the name is different from table, use @Column(name="..")
	@Size(max = 15)
	private String username;
	
	@Size(max = 50)
	private String password;
	
	@Size(max = 50)
	private String firstName;
	
	@Size(max = 15)
	private String lastName;

	@Override
	public void eraseCredentials() {
		this.password = null;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
}
