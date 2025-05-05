package com.CSED.SmartCityParking.Configurations;


import com.CSED.SmartCityParking.User.UserDAO;
import com.CSED.SmartCityParking.User.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {


    @Autowired
    UserDAO userDao ;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/user/register").permitAll()
                                        .anyRequest().authenticated()
                ).formLogin();

//        http.csrf().disable();


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetails(userDao);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
