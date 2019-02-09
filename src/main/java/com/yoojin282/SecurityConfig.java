package com.yoojin282;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "/login**").permitAll()
				.anyRequest().authenticated()
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("http://localhost:8080/exit?redirect_uri=http://localhost:8081")
				.permitAll();
	}
}
