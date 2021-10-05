package com.example.webapp.config;

import com.example.webapp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Sacuta V.A.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyCustomUserDetailsService myCustomUserDetailsService;
    private final ProfileService profileService;

    public SecurityConfig(MyCustomUserDetailsService myCustomUserDetailsService, ProfileService profileService) {
        this.myCustomUserDetailsService = myCustomUserDetailsService;
        this.profileService = profileService;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/reader").hasAnyRole("READER","AUTHOR","EDITOR")
                .antMatchers("/editor").hasAnyRole("EDITOR","AUTHOR")
                .antMatchers("/author").hasAnyRole("AUTHOR")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
                .formLogin()
                .disable()
                .logout().logoutSuccessUrl("/");
//                .logout(logout -> logout
//                .permitAll()
//                .logoutSuccessHandler((request, response, authentication) ->
//                            response.setStatus(HttpServletResponse.SC_OK)
//
//                ));

    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Autowired
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(myCustomUserDetailsService)
//                .passwordEncoder(BCryptPasswordEncoder)
                .passwordEncoder(NoOpPasswordEncoder.getInstance()
                );
    }
}
