package br.app.login.services.authenticator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import br.app.login.services.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object  Handler) throws UnsupportedEncodingException, IOException {
		
		if(CookieService.getCookie(request, "userId") != null) {
			
			return true;
			
		}else {
			
			response.sendRedirect("/login");
			return false;
		}
		
	}
	
	
}
