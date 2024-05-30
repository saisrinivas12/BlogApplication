package com.example.BlogApplication.config;

import com.example.BlogApplication.Repository.DBUsersRepository;
import com.example.BlogApplication.Repository.UserRepository;
import com.example.BlogApplication.entities.Dbusers;
import com.example.BlogApplication.entities.User;
import com.example.BlogApplication.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DBUsersRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Dbusers> user = this.userRepository.findByEmail(username);

        System.out.println("in custom userdetails service "+user);
        if(user.isPresent()){
            System.out.println("roles assigned "+user);
            List<String>rolesAssigned = user.get().getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
            System.out.println("roles assigned for this user "+rolesAssigned);
            return user.get();
        }
        throw new UsernameNotFoundException("UserName Not Found");

    }
}
