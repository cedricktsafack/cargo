package com.gleestorm.cargo.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.
                csrf()//We desable csrf
                .disable()
                .authorizeRequests()// We define the white list (The urls that doesn't require to be secure)
                .requestMatchers("/api/v1/auth/*")//All the methode that have this path
                .permitAll()
                .anyRequest() //All the others urls will need to be authenticated
                .authenticated()
                .and()
                //Configure the session management (Consist to define the session to be as stateless, so each request will be authenticated)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwAuthFilter, UsernamePasswordAuthenticationFilter.class) ;//Add our filter


        return http.build();
    }
}
