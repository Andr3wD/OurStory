package org.webstory.ourstory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.webstory.ourstory.services.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder authManBuild) throws Exception {
		authManBuild.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TEMP cors and csrf disabled.
		// TODO check out proper spring boot security use to work with PreAuthorize annotation.
		http.authorizeRequests().anyRequest().permitAll()
		.and()
		.formLogin().loginPage("/login").loginProcessingUrl("/loginAuth") // /loginAuth is used as loginProcessingUrl because vue needs a different url than what's in the router.
		.and()
		.logout().logoutSuccessUrl("/")
		.and()
		.cors().disable().csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return userService; 
	}
}
