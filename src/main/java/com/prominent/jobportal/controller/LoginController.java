package com.prominent.jobportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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









