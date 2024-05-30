package com.example.BlogApplication.Controllers;


import com.example.BlogApplication.entities.Dbusers;
import com.example.BlogApplication.payload.UserDto;
import com.example.BlogApplication.service.UserService;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto userDto1 =  userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto1);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,@PathVariable(name="userId") Integer userId) throws Exception{
        UserDto updatedUser ;

        updatedUser = userService.updateUser(userDto, userId);

        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/users")
    public ResponseEntity<?>getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{userId}")
    public ResponseEntity<?>getUserById(@PathVariable(name="userId") Integer userId) throws Exception {
        UserDto userDto ;

             userDto = userService.getUserById(userId);
             System.out.println("fetched user in controller "+userDto );

        return ResponseEntity.ok(userDto);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?>deleteUserBId(@PathVariable(name="userId") Integer userId){
           userService.deleteUserById(userId);
           return ResponseEntity.ok("Deleted Successfully");
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto>registerUser(@RequestBody UserDto userDto){
       UserDto savedUser = this.userService.registerUser(userDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
