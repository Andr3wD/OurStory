package org.webstory.ourstory.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Label this a config file for spring to use.
public class ServerConfig implements WebMvcConfigurer {

	// Respond with /index.html file if there would be an error page.
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
		return (test) -> test.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/index.html"));
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new GlobalIntercepter());
		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
