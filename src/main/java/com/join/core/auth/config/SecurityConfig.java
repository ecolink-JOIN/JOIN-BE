package com.join.core.auth.config;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.join.core.auth.handler.JsonLogoutSuccessHandler;
import com.join.core.auth.handler.OAuth2SuccessHandler;
import com.join.core.auth.service.OAuth2UserLoadingService;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.InvalidParamException;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String OAUTH2_AUTHORIZATION_URI_POSTFIX = "/oauth2/authorization";
	private static final String OAUTH2_REDIRECT_URI_POSTFIX = "/oauth2/code";
	private final OAuth2SuccessHandler oauth2SuccessHandler;
	private final OAuth2UserLoadingService oauth2UserLoadingService;
	private final JsonLogoutSuccessHandler logoutSuccessHandler;
	private final String apiPrefix;
	private final String oauth2AuthorizationURI;
	private final String oauth2RedirectURI;

	public SecurityConfig(OAuth2SuccessHandler oauth2SuccessHandler, OAuth2UserLoadingService oauth2UserLoadingService,
		JsonLogoutSuccessHandler logoutSuccessHandler, @Value("${api.prefix}") String apiPrefix) {
		if (StringUtils.isBlank(apiPrefix))
			throw new InvalidParamException(ErrorCode.INVALID_PARAMETER, "유효하지 않은 API prefix");

		this.oauth2SuccessHandler = oauth2SuccessHandler;
		this.oauth2UserLoadingService = oauth2UserLoadingService;
		this.logoutSuccessHandler = logoutSuccessHandler;
		this.apiPrefix = apiPrefix;
		this.oauth2AuthorizationURI = apiPrefix + OAUTH2_AUTHORIZATION_URI_POSTFIX;
		this.oauth2RedirectURI = apiPrefix + OAUTH2_REDIRECT_URI_POSTFIX;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configurationSource(websiteConfigurationSource()))
			.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.oauth2Login(oauth ->
				oauth.authorizationEndpoint(authorization -> authorization.baseUri(oauth2AuthorizationURI))
					.redirectionEndpoint(redirection -> redirection.baseUri(oauth2RedirectURI))
					.userInfoEndpoint(userInfo -> userInfo.userService(oauth2UserLoadingService))
					.successHandler(oauth2SuccessHandler))
			.logout(logout -> logout.logoutUrl(apiPrefix + "/logout")
				.logoutSuccessHandler(logoutSuccessHandler))
			.headers(headers ->
				headers.addHeaderWriter(
					new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)));

		return http.build();
	}

	CorsConfigurationSource websiteConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:3000", "https://join.com"));
		configuration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT", "PATCH"));
		configuration.setAllowedHeaders(List.of(AUTHORIZATION_HEADER, "Content-Type"));
		configuration.setExposedHeaders(List.of(AUTHORIZATION_HEADER));
		configuration.setMaxAge(3600L);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	HttpSessionIdResolver httpSessionIdResolver() {
		return new CompositeSessionIdResolver(AUTHORIZATION_HEADER, oauth2AuthorizationURI, oauth2RedirectURI);
	}

	@Bean
	static RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
		hierarchy.setHierarchy("""
			ROLE_ADMIN > ROLE_USER
			ROLE_USER > ROLE_GUEST
			""");
		return hierarchy;
	}

	// Method Security 에 권한 계층 적용시 필요한 핸들러
	@Bean
	static MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
		expressionHandler.setRoleHierarchy(roleHierarchy);
		return expressionHandler;
	}

}
