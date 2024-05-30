package com.example.BlogApplication.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTHelper helper;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    private static final Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException {
        String header = request.getHeader("Authorization");
        log.info("header param "+header);
        String token = null;
        String userName = null;
        if(header!=null && header.startsWith("Bearer")){
              token = header.substring(7);
              userName =helper.getUsernameFromToken(token);
              if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                  UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                  if(userDetails!=null){
                      boolean valid = this.helper.validateToken(token,userDetails);
                      if(valid){
                          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                      }
                      else{
                          log.info("Token doesnt match ");
                      }
                  }
                  else{
                      log.error("Bad Credentials");
                  }
              }
        }
        else{
            log.error("header may be null or does not start with Bearer !!");
        }

        filterChain.doFilter(request,response);

    }
}
