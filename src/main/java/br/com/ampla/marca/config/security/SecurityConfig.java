package br.com.ampla.marca.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import br.com.ampla.marca.config.cors.CorsConfig;
import br.com.ampla.marca.config.security.jwt.JwtAuthenticationFilter;
import br.com.ampla.marca.config.security.jwt.JwtAuthorizationFilter;
import br.com.ampla.marca.config.security.jwt.handler.AccessDeniedHandler;
import br.com.ampla.marca.config.security.jwt.handler.UnauthorizedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
    @Autowired
    private UnauthorizedHandler unauthorizedHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 AuthenticationManager authManager = authenticationManager();

	     http
		     .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
		     .and()
		     .authorizeRequests()
		     	 .antMatchers(HttpMethod.POST, "/login").permitAll()
		     	 .antMatchers("/usuarios/**").permitAll()
		         .anyRequest().authenticated()
		     .and()
		     .csrf().disable()
		         .addFilter(new CorsConfig())
		         .addFilter(new JwtAuthenticationFilter(authManager))
		         .addFilter(new JwtAuthorizationFilter(authManager, userDetailsService))
		         .exceptionHandling()
		         .accessDeniedHandler(accessDeniedHandler)
		         .authenticationEntryPoint(unauthorizedHandler)
		     .and()
		         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		auth.userDetailsService(this.userDetailsService).passwordEncoder(encoder);
	}

}
