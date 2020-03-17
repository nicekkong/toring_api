package com.cplanet.toring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(null);
//        super.configure(auth);
//        auth.inMemoryAuthentication().withUser("test").password(passwordEncoder().encode("1234"))
//                .roles("ADMIN", "USER").authorities("ACCESS_TEST1", "ACCESS_TEST2");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
            .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/about/**").permitAll()
                .antMatchers("/contents/save/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/auth/**").hasAnyAuthority("ACCESS_TEST1", "ACCESS_TEST2")
//                .anyRequest().permitAll()
//                .anyRequest().authenticated()
            .and()
                .httpBasic()
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
