package tn.esprit.spring.configuration;

import java.util.Arrays;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import tn.esprit.spring.Services.UserServiceImp;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	
	@Autowired
	private DataSource dataSource;

	

	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public UserDetailsService userDetailsService() {
	        return new UserDetailsServiceImpl();
	    }
	     
	    
	     
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	         
	        return authProvider;
	    }
	 
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    }


	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable();
		http.
		 authorizeRequests()
		.antMatchers("SpringMVC/servlet/user/**").permitAll()
		.antMatchers("SpringMVC/servlet/layer/**").permitAll()
		.antMatchers("SpringMVC/servlet/Stock/**").permitAll()
		.antMatchers("SpringMVC/servlet/subject/**").permitAll()
		.antMatchers("SpringMVC/servlet/comment/**").permitAll()

        .anyRequest().authenticated()
        .and()
        .httpBasic()
		.and()
		.csrf().disable().formLogin();
		
		
	        
	}
	
}
	



	  
