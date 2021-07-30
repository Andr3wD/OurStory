package org.webstory.ourstory.response;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.security.core.GrantedAuthority;

public class AuthStatusResponse {

	public HashMap<String, Boolean> roles = new HashMap<>();
	public String username;
	public String message;

	public AuthStatusResponse(Collection<GrantedAuthority> authories) {
		for (GrantedAuthority grantedAuthority : authories) {
			switch (grantedAuthority.toString()) {
			case "ROLE_ADMIN":
				this.roles.put("isAdmin", true);
			case "ROLE_MOD":
				this.roles.put("isMod", true);
			case "ROLE_USER":
				this.roles.put("isUser", true);
				this.roles.put("isAnon", false);
			default:
				break;
			}
		}
	}

	public AuthStatusResponse() {
		this.roles.put("isAdmin", false);
		this.roles.put("isMod", false);
		this.roles.put("isUser", false);
		this.roles.put("isAnon", true);
	}

}
