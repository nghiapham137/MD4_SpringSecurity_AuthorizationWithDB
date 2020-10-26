package com.example.demo1.config;

import com.example.demo1.service.user_service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Autowired
    public AppUserService appUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) appUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/access-denied").permitAll()
                .and()
                .authorizeRequests().antMatchers("/category/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET,"/tasks/**").hasAnyRole("USER","ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/tasks/**").hasRole("USER")
                .and()
                .formLogin().successHandler(customSuccessHandler)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and().exceptionHandling().accessDeniedPage("/access-denied");
        http.csrf().disable();
    }


}
