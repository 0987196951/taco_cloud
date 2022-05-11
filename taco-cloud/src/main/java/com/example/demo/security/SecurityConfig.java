package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class SecurityConfig {
	/**
	 *
	 */
	//@Autowired
	//private DataSource dataSource;
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.jdbcAuthentication()
////		.dataSource(dataSource)
////		.usersByUsernameQuery (
////				"select username, password, enable from Users where username = ?")
////		.authoritiesByUsernameQuery	(
////				"select username, authority from UserrAuthorities where username=?")
////		.passwordEncoder(new SCryptPasswordEncoder());
//		auth.ldapAuthentication()
//		.userSearchBase("ou=people")
//		.userSearchFilter("(uid={0})")
//		.groupSearchBase("ou=groups")
//		.groupSearchFilter("member={0}")
//		.passwordCompare()
//		.passwordEncoder(new BCryptPasswordEncoder())
//		.passwordAttribute("passcode");
//	}
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
