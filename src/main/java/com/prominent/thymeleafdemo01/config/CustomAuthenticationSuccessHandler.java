package com.prominent.thymeleafdemo01.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prominent.thymeleafdemo01.entity.User;
import com.prominent.thymeleafdemo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");

		String userName = authentication.getName();

		System.out.println("userName=" + userName);

		User theUser = userService.findByUserName(userName);

		// now place in the session
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);

		// forward to home page
		if(theUser.getFormRole().equals("ROLE_ADMIN")||theUser.getFormRole().equals("ROLE_MANAGER")||theUser.getFormRole().equals("ROLE_EMPLOYEE"))
			response.sendRedirect(request.getContextPath() + "/register/viewUsersPage");
		else if(theUser.getFormRole().equals("ROLE_RECRUITER"))
			response.sendRedirect(request.getContextPath() + "/register/Recruiters_HomePage");
		else if(theUser.getFormRole().equals("ROLE_JOB_SEEKER"))
			response.sendRedirect(request.getContextPath() + "/register/JobSeekers_HomePage");

	}

}
