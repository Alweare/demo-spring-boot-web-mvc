package fr.eni.demo.configuration.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class EniSecurityConfig {
	
	//@Bean
	public UserDetailsService users() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String password = encoder.encode("Pa$$w0rd");
		System.out.println("password = " + password);
		
		
		UserDetails user = User.builder()
			.username("abaille@campus-eni.fr")
			.password(password)
			.roles("EMPLOYE","FORMATEUR ")
			.build();
		UserDetails admin = User.builder()
			.username("sgobin@campus-eni.fr")
			.password(password)
			.roles("EMPLOYE","FORMATEUR", "ADMIN")
			.build();
		UserDetails user2 = User.builder()
				.username("sdautais@campus-eni.fr")
				.password(password)
				.roles("EMPLOYE")
				.build();
		return new InMemoryUserDetailsManager(user,user2 ,admin);
	}
	
	@Bean
	UserDetailsManager users(DataSource dataSource) {
		
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
	
		return users;
	}
	



}
