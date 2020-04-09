package com.epam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder authenticate) throws Exception {
		authenticate.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	public SecurityConfiguration(AuthenticationSuccessHandler authenticationSuccessHandler) {
		this.authenticationSuccessHandler = authenticationSuccessHandler;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().antMatchers("/api/**").permitAll().antMatchers("/css/**").permitAll()
				.antMatchers("/js/**").permitAll().antMatchers("/img/**").permitAll().antMatchers("/sass/**")
				.permitAll().antMatchers("/webfonts/**").permitAll().antMatchers("/fonts/**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/**").hasRole("USER").anyRequest()
				.authenticated().and().formLogin().loginPage("/login").successHandler(authenticationSuccessHandler);
	}

}
