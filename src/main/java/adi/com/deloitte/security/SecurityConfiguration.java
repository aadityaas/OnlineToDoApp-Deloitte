package adi.com.deloitte.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	        .withUser("test").password(passwordEncoder().encode("pwd123")).roles("USER")
	        .and()
	        .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
	        .and()
	        .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER");
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
	    http
	      .csrf().disable()
	      .authorizeRequests()
	      .antMatchers("/login*", "/h2-console/**").permitAll()
	      .antMatchers("/", "/*task*/**").access("hasRole('USER')")
	      .antMatchers("/admin/**").hasRole("ADMIN")
	      .antMatchers("/anonymous*").anonymous()
	      .anyRequest().authenticated()
	      .and()
	      .formLogin();
	}
}
