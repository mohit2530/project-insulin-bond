package com.insulinbond.authorization.filter;

import com.insulinbond.authorization.MyUserDetailsService;
import com.insulinbond.authorization.util.JwtUtil;
import com.insulinbond.shared.Shared;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* Java Web Token Filter on end call made.
*
* Create by Anish May 29 2020
* */
@Service
public class JWTRequestFilter extends OncePerRequestFilter {
    @Inject
    private MyUserDetailsService myUserDetailsService;

    @Inject
    private JwtUtil jwtUtil;

    @Inject
    Shared shared;

    /*
    * It helps to create the Request Header Authorization and also use for JWt filter to validate
    * */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        final String context = request.getHeader("Context");
        String username = null;
        String jwt = null;

        try {
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
                // Check if the Token is validate and also check if the context coming from header match with what is save in Database
                if (jwtUtil.validateToken(jwt, userDetails) && shared.checkUserByContext(context) != null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        filterChain.doFilter(request, response);
    }
}
