package com.tjcode.rideservice.configuration;

import com.tjcode.rideservice.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");

        String jwtToken = null;
        String userName = null;
        boolean flag = false;
        if (header != null && header.startsWith("Bearer ")) {
            jwtToken = header.substring(7);
            System.out.println(jwtToken);
            System.out.println(jwtUtil.dummy());

            try {
                flag = jwtUtil.validateToken(jwtToken);
                userName = jwtUtil.getUserNameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT token");
            } catch (ExpiredJwtException e) {
                System.out.println("Jwt token is expired");
            }
        } else {
            System.out.println("Jwt token does not start with Bearer");
        }

        if (jwtToken != null && flag) {
            Authentication auth = jwtUtil.getAuthentication(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

}
