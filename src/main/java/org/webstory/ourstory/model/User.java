package org.webstory.ourstory.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * This class is for users and the parts they submit. Users can be individuals
 * without accounts, so we need to block via IP and fingerprinting.
 *
 */
@SuppressWarnings("serial")
//This annotation just tells MongoDB what 'collection' name these instances should be saved to.
@Document(collection = "users")
//The @Data annotation removes all boilerplate. Getters, setters, equals, hashcode, tostring, and default constructor are automatically added behind the scenes.
public @Data class User implements UserDetails {

	@Id
	private ObjectId id;
	private String ip; // HttpServletRequest.getRemoteAddr() returns String ip
	private List<ObjectId> segments = new ArrayList<ObjectId>();
	private String username = "Anonymous"; // Default.
	private String password;

	private Set<SimpleGrantedAuthority> authorities = new HashSet<>();

	public boolean addSegment(ObjectId seg) {
		return segments.add(seg);
	}

	public boolean addRole(String role) {
		return authorities.add(new SimpleGrantedAuthority(role));
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
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}
