package com.example.BlogApplication.service;

import com.example.BlogApplication.payload.UserDto;

import java.util.List;

public interface UserService {



    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user,int userId) throws Exception;

    UserDto getUserById(int id) throws Exception;

    List<UserDto>getAllUsers();

    String deleteUserById(int id);

    UserDto registerUser(UserDto userDto);
}
