package com.join.core.auth.domain;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class AuthenticationToken extends AbstractAuthenticationToken {

	private final UserPrincipal principal;

	private AuthenticationToken(UserPrincipal principal, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
	}

	public AuthenticationToken(UserInfo.SigIn sigIn) {
		this(UserPrincipal.of(sigIn), sigIn.getAuthorities());
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof AuthenticationToken that))
			return false;
		if (!super.equals(o))
			return false;

		return principal.equals(that.principal);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + principal.hashCode();
		return result;
	}

}
