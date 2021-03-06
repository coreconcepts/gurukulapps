package com.guru.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
@EnableAutoConfiguration
public class GWebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	setAuthorization(http);
    	
    	http.authorizeRequests().antMatchers("/user/**").hasRole("ADMIN");
    	
    	http.authorizeRequests().antMatchers("styles.css").permitAll();
    	
    	http.authorizeRequests().anyRequest().authenticated().and()
    		.formLogin().loginPage("/login").permitAll().and()
    		.logout().permitAll();
    	
    	
        
//            	
//            	.antMatchers("/**").hasAnyAuthority("ADMIN", "USER")
//            	.antMatchers("*/user/input").hasRole("ADMIN")
//            	.antMatchers("styles.css").permitAll()
//            	.antMatchers("*/addrecipe").permitAll()
//            	
    	
            	
           //http.authorizeRequests().anyRequest().permitAll();
    }
    private void setAuthorization (HttpSecurity http) throws Exception{
    	http.exceptionHandling().accessDeniedPage("/customerror");
    	http.exceptionHandling();
    	
    }
    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    /*    auth
            .inMemoryAuthentication()
                .withUser("a").password("a").roles("USER");
                */
    	auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select login,password, enabled from users where users.login=?")
		.authoritiesByUsernameQuery(
			"select login, role from users where users.login=?");	
    	
    }
    
    
    
    
    
    
}