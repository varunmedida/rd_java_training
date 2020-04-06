package com.epam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.epam.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
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

	@Order(1)
	@Configuration
	public static class RestConfiguration extends WebSecurityConfigurerAdapter {

		@Autowired
		JwtRequestFilter jwtRequestFilter;

		@Override
		@Bean
		protected AuthenticationManager authenticationManager() throws Exception {
			return super.authenticationManager();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll().antMatchers("/admin/**")
					.hasRole("ADMIN").antMatchers("/**").hasRole("USER").anyRequest().authenticated().and()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		}
	}

	@Order(2)
	@Configuration
	public static class MvcConfiguration extends WebSecurityConfigurerAdapter {
		
		private AuthenticationSuccessHandler authenticationSuccessHandler;

		@Autowired
		public MvcConfiguration(AuthenticationSuccessHandler authenticationSuccessHandler) {
			this.authenticationSuccessHandler = authenticationSuccessHandler;
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().antMatchers("/css/**")
					.permitAll().antMatchers("/js/**").permitAll().antMatchers("/img/**").permitAll()
					.antMatchers("/sass/**").permitAll().antMatchers("/webfonts/**").permitAll()
					.antMatchers("/fonts/**").permitAll().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/**")
					.hasRole("USER").anyRequest().authenticated().and().formLogin().loginPage("/login")
					.successHandler(authenticationSuccessHandler);
		}
	}

}
