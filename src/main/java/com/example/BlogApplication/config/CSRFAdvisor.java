package com.example.BlogApplication.config;




import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class CSRFAdvisor {


    @ModelAttribute
    public void handleRequest(HttpServletResponse response, CsrfToken token){
        if(token!=null) {
            System.out.println("entered into CSRF Advisor " + token.getToken() + "header " + token.getHeaderName());
            response.setHeader(token.getHeaderName(), token.getToken());
        }

    }

}
