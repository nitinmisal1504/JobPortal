package com.prominent.thymeleafdemo01.controller;

import com.prominent.thymeleafdemo01.dao.LogInDao;
import com.prominent.thymeleafdemo01.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.util.Collection;

@Controller
public class LoginController {

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		
		return "fancy-login";
		
	}
	// add request mapping for /access-denied

	@GetMapping("/access-denied")
	public String showAccessDenied() {

		return "access-denied";

	}
	/*
	@PostMapping("/authenticateTheUser")
	public void showMyLoginSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String n=request.getParameter("username");
		String p=request.getParameter("password");
		String encodedPass=passwordEncoder.encode(p);

		if(LogInDao.LoginDao.validate(n, encodedPass)){
			RequestDispatcher rd=request.getRequestDispatcher("/index");
			rd.forward(request,response);
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("fancy-login.html");
			rd.include(request,response);
		}

		out.close();



	}


	@GetMapping("/authenticateTheUser")
	public void authenticateTheUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String username = authentication.getName();
		Object principal = authentication.getPrincipal();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		context.setAuthentication(authentication);


	}*/

	

	
}









