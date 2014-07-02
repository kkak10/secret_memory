package org.ebj.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	protected final static Logger logger = Logger.getLogger(LoginCheckInterceptor.class);
	
	///Field
	Boolean check = false;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		logger.info("[ *********************** LoginCheckInterceptor Start........]");
		
		HttpSession session = request.getSession(true);
		
		
		if (session.getAttribute("dbUser") != null) {
			
			check = true;
			
		} else {
			if (request.getRequestURI().indexOf("inviteGroupListNo") == -1) {
				response.sendRedirect("/resources/index.html");
			}
		}
		
		logger.info("[ *********************** LoginCheckInterceptor End........]");
	
		return check;
		
		
	}
}
