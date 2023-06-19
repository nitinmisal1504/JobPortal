package com.prominent.thymeleafdemo01.config;

import com.prominent.thymeleafdemo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.Cookie;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our user service
	@Autowired
	private UserService userService;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
/*
	@Autowired
	private DataSource securityDataSource;
*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/register/showForm*").hasAnyRole("MANAGER", "ADMIN")
				.antMatchers("/register/showRegistration*").permitAll()
				.antMatchers("/register/save*").hasAnyRole("MANAGER", "ADMIN")
				.antMatchers("/register/delete").hasRole("ADMIN")
				.antMatchers("/register/JobSeekers*").hasRole("JOB_SEEKER")
				.antMatchers("/register/Recruiters*").hasRole("RECRUITER")
				.antMatchers("/*").permitAll()
				//.antMatchers("/register/**").hasRole("EMPLOYEE")
				.antMatchers("/register/**").permitAll()
 				.antMatchers("/resources/**").permitAll()
				.and()
				.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
				.and()
				.logout()
				/*
				.addLogoutHandler((request, response, auth) -> {
					for (Cookie cookie : request.getCookies()) {
						String cookieName = cookie.getName();
						Cookie cookieToDelete = new Cookie(cookieName, null);
						cookieToDelete.setMaxAge(0);
						response.addCookie(cookieToDelete);
					}
				})*/
				.permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/access-denied");
	}

	//beans
	//bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}
/*
	@Bean
	SecurityWebFilterChain http(ServerHttpSecurity http) throws Exception {
		DelegatingServerLogoutHandler logoutHandler = new DelegatingServerLogoutHandler(
				new WebSessionServerLogoutHandler(), new SecurityContextServerLogoutHandler()
		);

		http
				.authorizeExchange((exchange) -> exchange.anyExchange().authenticated())
				.logout((logout) -> logout.logoutHandler(logoutHandler));

		return http.build();
	}
*/

/*
	@Bean
	public UserDetailsManager userDetailsManager() {

		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();

		jdbcUserDetailsManager.setDataSource(securityDataSource);

		return jdbcUserDetailsManager;
	}
*/

}
