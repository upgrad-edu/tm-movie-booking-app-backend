package com.upgrad.mtb.config.config;


import com.upgrad.mtb.security.jwt.JwtConfigurer;
import com.upgrad.mtb.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  JwtTokenProvider jwtTokenProvider;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.httpBasic()
        .disable()
        .csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/access-tokens")
        .permitAll()
        .antMatchers("/users")
        .permitAll()
        .antMatchers("/access-tokens/refresh")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .apply(new JwtConfigurer(jwtTokenProvider));
  }

  @Bean
  public AuthenticationFailureHandler authenticationFailureHandler() {
    return new RestAuthenticationFailureHandler();
  }
}

