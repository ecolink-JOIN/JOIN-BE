package com.join.core.auth.config;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.util.AntPathMatcher;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.InvalidParamException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompositeSessionIdResolver implements HttpSessionIdResolver {

	private final String headerName;
	private final String authorizationUriPattern;
	private final String redirectUriPattern;
	private final CookieHttpSessionIdResolver cookieHttpSessionIdResolver = new CookieHttpSessionIdResolver();
	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	public CompositeSessionIdResolver(String headerName, String authorizationBaseUri, String redirectBaseUri) {
		if (StringUtils.isAnyBlank(headerName, authorizationBaseUri, redirectBaseUri)) {
			throw new InvalidParamException(ErrorCode.INVALID_PARAMETER, "CompositeSessionIdResolver.String");
		}
		this.headerName = headerName;
		this.authorizationUriPattern = authorizationBaseUri + "/**";
		this.redirectUriPattern = redirectBaseUri + "/**";
	}

	@Override
	public List<String> resolveSessionIds(HttpServletRequest request) {
		// redirect로 들어오면 쿠키만 확인
		if (pathMatcher.match(redirectUriPattern, request.getRequestURI())) {
			return cookieHttpSessionIdResolver.resolveSessionIds(request);
		}

		String headerValue = request.getHeader(this.headerName);
		return headerValue != null ? Collections.singletonList(headerValue) : Collections.emptyList();
	}

	@Override
	public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
		// 인가 요청시 쿠키에 인증용 임시 세션 ID 설정
		String uri = request.getRequestURI();
		if (pathMatcher.match(authorizationUriPattern, uri)) {
			cookieHttpSessionIdResolver.setSessionId(request, response, sessionId);
			return;
		}

		// 리다이렉트 요청시 쿠키에 인증용 임시 세션 만료처리
		if (pathMatcher.match(redirectUriPattern, uri)) {
			cookieHttpSessionIdResolver.expireSession(request, response);
		}

		response.setHeader(this.headerName, sessionId);
	}

	@Override
	public void expireSession(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader(this.headerName, "");
	}
}
