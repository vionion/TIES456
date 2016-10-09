package com.ties456.model.user;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(Authority authority) {
		this.authority = "ROLE_" + authority;
	}
	
	@Override
	public String getAuthority() {
		return authority.toString();
	}
}