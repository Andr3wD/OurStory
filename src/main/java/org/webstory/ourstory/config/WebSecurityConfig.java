package org.webstory.ourstory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TEMP cors and csrf disabled.
		// TODO check out proper spring boot security use to work with PreAuthorize annotation.
		http.authorizeRequests().antMatchers("/**").anonymous()
		.and()
		.cors().disable()
		.csrf().disable();
	}
}
