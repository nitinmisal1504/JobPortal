package com.prominent.thymeleafdemo01.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


import com.prominent.thymeleafdemo01.entity.User;
import com.prominent.thymeleafdemo01.service.UserService;
import com.prominent.thymeleafdemo01.user.CrmUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;


@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
    private UserService userService;
	/*
	@Autowired
	private UserDetailsManager userDetailsManager;
*/
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private Logger logger = Logger.getLogger(getClass().getName());

	private Map<String, String> roles;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@PostConstruct
	protected void loadRoles() {

		// using hashmap, could also read this info from a database

		roles = new LinkedHashMap<String, String>();
		// key=the role, value=display to user
		roles.put("ROLE_ADMIN", "Admin");
		roles.put("ROLE_MANAGER", "Manager");
		roles.put("ROLE_EMPLOYEE", "Employee");
		roles.put("ROLE_RECRUITER", "Recruiter");
		roles.put("ROLE_JOB_SEEKER", "Job Seeker");
	}



	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {

		theModel.addAttribute("crmUser", new CrmUser());
		theModel.addAttribute("roles", roles);
		System.out.println("processing showRegistrationForm.....");

		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
			@Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
			BindingResult theBindingResult,
			Model theModel) {
		String userName = theCrmUser.getUserName();

		logger.info("Processing registration form for: " + userName);

		// form validation
		if (theBindingResult.hasErrors()) {

			theModel.addAttribute("crmUser", new CrmUser());

			// add roles to the model for form display
			theModel.addAttribute("roles", roles);

			theModel.addAttribute("registrationError", "User name/password can not be empty.");

			logger.warning("User name/password can not be empty.");

			return "registration-form";
		}


		//
		// whew ... we passed all of the validation checks!
		// let's get down to business!!!
		//

		// encrypt the password
		String encodedPassword = passwordEncoder.encode(theCrmUser.getPassword());

		// prepend the encoding algorithm id
		//encodedPassword = "{bcrypt}" + encodedPassword;

		// give user default role of "employee"
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList();
		authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));

		// if the user selected role other than employee,
		// then add that one too (multiple roles)
		String formRole = theCrmUser.getFormRole();

		if (!formRole.equals("ROLE_EMPLOYEE")) {
			authorities.add(new SimpleGrantedAuthority(formRole));
		}

		// create user object (from Spring Security framework)
		//User tempUser = new User(userName, encodedPassword, authorities);

		// save user in the database
		//userDetailsManager.createUser((UserDetails) tempUser);

		// check the database if user already exists
		User existing = userService.findByUserName(userName);
		if (existing != null){
			theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
			return "registration-form";
		}

		// create user account
		userService.save(theCrmUser);

		logger.info("Successfully created user: " + userName);


		return "registration-confirmation";
	}


	@RequestMapping("/Recruiters_HomePage")
	public String displayRecruitersPage(){
		return "recruiter_home";
	}

	@RequestMapping("/Recruiters_PostJobPage")
	public String displayRecruitersPostJobPage(){
		return "post_job_page";
	}

	@RequestMapping("/JobSeekers_HomePage")
	public String displayJobSeekersPage(){
		return "job_seekers_home";
	}

	@RequestMapping("/JobSeekers_FindJobPage")
	public String findJobPage(){
		return "find_job_page";
	}


	@RequestMapping("/viewUsersPage")
	public String viewRecruitersPage(Model model){
		model.addAttribute("listUsers",userService.getAllUsers());
		return "viewUsers";
	}

	@RequestMapping(value = "/showFormForAdd")
	private String showAddRecruiterForm(Model model) {
		User user=new User();
		model.addAttribute("user",user);
		return "registration-form";
	}


	@PostMapping(value = "/saveUser")
	private String saveUser(@ModelAttribute("user") User user) {
		userService.saveUser(user);
		return "redirect:/register/viewUsersPage";
	}

	@GetMapping(value = "/showFormForUpdate/{id}")
	private String updateUser(@PathVariable(value = "id") int id, Model model) {
		User user=userService.getUserById(id);
		model.addAttribute("user",user);
		return "updateUser";
	}


	@GetMapping(value = "/deleteUser/{id}")
	private String deleteUser(@PathVariable (value = "id") int id) {
		userService.deleteRecordById(id);
		return "redirect:/register/viewUsersPage";
	}

}
