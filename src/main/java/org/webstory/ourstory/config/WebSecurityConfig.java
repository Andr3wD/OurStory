package org.webstory.ourstory.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.webstory.ourstory.response.AuthStatusResponse;
import org.webstory.ourstory.services.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void configure(AuthenticationManagerBuilder authManBuild) throws Exception {
		authManBuild.userDetailsService(userService);
	}

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TEMP cors and csrf disabled.
		// TODO check out proper spring boot security use to work with PreAuthorize annotation.
		http.authorizeRequests().anyRequest().permitAll()
		.and()
		.formLogin().loginPage("/login").loginProcessingUrl("/loginAuth") // /loginAuth is used as loginProcessingUrl because vue needs a different url than what's in the router.
		.successHandler(authSuccessHandler()).failureHandler(authFailureHandler())
		.and().logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)) // Thanks to https://stackoverflow.com/questions/36354405/spring-security-disable-logout-redirect
		.and()
		.cors().disable().csrf().disable();
	}
	// @formatter:on

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return userService;
	}

	// Thanks to https://stackoverflow.com/questions/32498868/custom-login-form-configure-spring-security-to-get-a-json-response
	private AuthenticationSuccessHandler authSuccessHandler() {
		return new AuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
				// Send account data, such as roles and username.
				System.out.println("Success login");
				AuthStatusResponse resp = new AuthStatusResponse((Collection<GrantedAuthority>) authentication.getAuthorities());
				resp.message = "Successfully logged in";
				resp.username = authentication.getName();

				response.getWriter().write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resp));
//				response.getWriter().write("{\"message\":\"Successfully logged in\"}, \"username\":\"\"");
			}
		};
	}

	private AuthenticationFailureHandler authFailureHandler() {
		return new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
				// Send login error message
				System.out.println("Fail login");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
//				response.getWriter().write("{\"message\":\"Bad Credentials\"}");
				
				AuthStatusResponse resp = new AuthStatusResponse();
				resp.username = "NONE";
				resp.message = "Bad Credentials";

				response.getWriter().write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resp));

			}
		};
	}
}
