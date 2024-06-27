package fr.eni.demo.configuration.security;

import javax.sql.DataSource;
import javax.swing.plaf.metal.OceanTheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
		users.setUsersByUsernameQuery("SELECT pseudo,password,'true' as enabled FROM UTILISATEUR WHERE pseudo = ?");
		users.setAuthoritiesByUsernameQuery("SELECT pseudo,role FROM ROLES WHERE pseudo = ?");
		return users;
	}
	
	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests((authorize) -> authorize
		    .requestMatchers("/formateurs").hasAnyRole("EMPLOYE","FORMATEUR","ADMIN")
		    .requestMatchers("/formateurs/detail").hasAnyRole("FORMATEUR","ADMIN")
		    .requestMatchers("/formateurs/creer").hasRole("ADMIN")
		    .requestMatchers("/formateurs/cours").hasAnyRole("FORMATEUR","ADMIN")
		    //permet au role ADMIN d'accéder aux url commençant par /contexte
		    .requestMatchers("/contexte/**").hasRole("ADMIN")
		    .requestMatchers("/utilisateurs").hasRole("ADMIN")
		    .requestMatchers("/css/*").permitAll()
		    .requestMatchers("/images/*").permitAll()
		    .requestMatchers("/login").permitAll()
		    .requestMatchers("/").permitAll()
	            .anyRequest().authenticated()
	        );
	      
	    http.formLogin(form ->{ form.loginPage("/login");
	    						form.permitAll();
	    						form.defaultSuccessUrl("/");
	   
	    });
	    http.logout(form -> {
	    	form.invalidateHttpSession(true);
	    	form.clearAuthentication(true);
	    	form.deleteCookies("JSESSIONID");
	    	form.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	    	form.logoutSuccessUrl("/");
	    });

	    return http.build();
	}



}
