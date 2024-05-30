package com.example.BlogApplication.Controllers;

import com.example.BlogApplication.config.CustomUserDetailsService;
import com.example.BlogApplication.config.JWTHelper;
import com.example.BlogApplication.entities.JWTRequest;
import com.example.BlogApplication.entities.JWTResponse;
import com.example.BlogApplication.payload.UserDto;
import com.example.BlogApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customlogin")
public class AuthController {


    @Autowired
    private JWTHelper helper;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @PostMapping("/custom")
    public ResponseEntity<JWTResponse>login(@RequestBody JWTRequest request) throws Exception{
        if(this.doAuthenticate(request)){//user is authenticated
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
            String token = this.helper.generateToken(userDetails);
            JWTResponse response = new JWTResponse();
            response.setJwtToken(token);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/get")
    public ResponseEntity<String>getCustomCSRFToken(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Provided CSRF Token in the form of a Cookie");
    }


    private boolean doAuthenticate(JWTRequest request){
        try{
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword());
            Authentication auth = manager.authenticate(token);
            return auth.isAuthenticated();

        }
        catch(Exception e){
            System.out.println("Bad Credentials ");
            return false;
        }
    }


  /*  @PostMapping("/register")
    public ResponseEntity<UserDto>registerUser(@RequestBody UserDto userDto){
        UserDto savedUser = this.userService.registerNewUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }*/
}
