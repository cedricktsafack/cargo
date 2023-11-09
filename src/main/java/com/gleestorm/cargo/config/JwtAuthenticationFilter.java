package com.gleestorm.cargo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        //Getting the header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        //If the token doesn't exist we continue
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //if  the token exist, extract the token
        jwt = authHeader.substring(7);

        //Extract user email from the token
        userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //if the user email into the token
            //and if the authentication is null that means the user is not connected yet

            //So we get the user from the database by email
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            //if the user exist in database, we check if the token is valid
            if (jwtService.isTokenValid(jwt, userDetails)) {
                //The token is valid
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                //Update security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }


            //The Filter chain
            filterChain.doFilter(request, response);
            
        }
    }
}
