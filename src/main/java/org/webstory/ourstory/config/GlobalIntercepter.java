package org.webstory.ourstory.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.webstory.ourstory.api.AdminController;
import org.webstory.ourstory.model.HttpRequestInstance;

public class GlobalIntercepter implements HandlerInterceptor {
	
	@Autowired

	// Intercepts all requests prior to them being handled.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO put in DB instead.
		AdminController.requestHistory.add(new HttpRequestInstance(DateTime.now(), request.getRequestURI(), request.getRemoteAddr()));
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
}
