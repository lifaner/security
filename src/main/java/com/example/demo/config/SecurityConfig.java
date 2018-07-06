package com.example.demo.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Create by lifan.
 * Date: 2018/7/6.
 * Time: 11:47.
 */
@Configuration
@Slf4j
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
        .antMatchers("/login/valid").antMatchers("/", "/resources/**")
        ;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
        http.formLogin().loginPage("/login");


    }

}
