package com.example.schoolBE.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import com.google.api.client.util.Value;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig {
	
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	
	 @Bean
	   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	            .authorizeRequests().anyRequest().authenticated().and()
	            .httpBasic().authenticationEntryPoint(authenticationEntryPoint);
	        
	        return http.build();
	   	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
		auth
			.inMemoryAuthentication()
			.withUser("admin").password("{noop}admin1").roles("ADMIN")
			.and()
    		.withUser("roditelj").password("{noop}password1").roles("RODITELJ")
    		.and()
    		.withUser("ucenik").password("{noop}password12").roles("UCENIK")
    		.and()
    		.withUser("nastavnik").password("{noop}password123").roles("NASTAVNIK");
 
		return auth.build();
	}
	
	
	
	@Value("${spring.security.user.name}")
	private String username;

	@Value("${spring.security.user.password}")
	private String password;
	
	
	@SuppressWarnings({ "removal" })
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/roditelj/**")
				.hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK").requestMatchers("/ucenik/**")
				.hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK").requestMatchers("/nastavnik/**")
				.hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK").requestMatchers("/ocena/**")
				.hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK").requestMatchers("/izostanak/**")
				.hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK").requestMatchers("/korisnik/**").hasAnyRole("ADMIN")
				.requestMatchers("/predmet/**").hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK")
				.requestMatchers("/student/**").hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK")
				.requestMatchers("/razred/**").hasAnyRole("RODITELJ", "NASTAVNIK", "UCENIK")

				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
				.logoutUrl("/logout").logoutSuccessUrl("/logout?logout").permitAll();
	}
}
