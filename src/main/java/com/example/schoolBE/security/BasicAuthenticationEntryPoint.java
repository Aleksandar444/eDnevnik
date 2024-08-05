package com.example.schoolBE.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BasicAuthenticationEntryPoint {
	public void setRealm(String string) {
		// TODO Auto-generated method stub
		
	}
	
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	public void afterPropertiesSet() {
		// TODO Auto-generated method stub
		
	}
}
