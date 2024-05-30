package com.example.BlogApplication.service;

import com.example.BlogApplication.Repository.DBUsersRepository;
import com.example.BlogApplication.Repository.RoleRepository;
import com.example.BlogApplication.Repository.UserRepository;
import com.example.BlogApplication.config.AppConstants;
import com.example.BlogApplication.entities.Dbusers;
import com.example.BlogApplication.entities.Role;
import com.example.BlogApplication.entities.User;
import com.example.BlogApplication.exception.ResourceNotFoundException;
import com.example.BlogApplication.payload.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private RoleRepository repository;

    @Autowired
    private DBUsersRepository dbUsersRepository;

    /*@Override
    public UserDto registerNewUser(UserDto user) {
        Dbusers user1 = this.modelMapper.map(user,Dbusers.class);
        user1.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Role role = repository.findById(AppConstants.NORMAL_USER).get();
        user1.getRoles().add(role);
        return this.modelMapper.map(dbUsersRepository.save(user1),UserDto.class);

    }*/

    @Override
    public UserDto createUser(UserDto user) {
        User returnedUser =  userRepository.save(this.userDtoToUser(user));
        return this.userToUserDto(returnedUser);
    }

    @Override
    public UserDto updateUser(UserDto user, int userId) throws Exception{
       User user1 = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with userId"+userId+"not found!!"));
       System.out.println("fetched user "+user1.getId());
       user.setId(user1.getId());
       if(user1!=null){
          User updatedUser =  userRepository.save(this.userDtoToUser(user));
          UserDto dto = this.userToUserDto(updatedUser);
           return dto;
       }
       throw new Exception("UserId Not Found !!");
    }

    @Override
    public UserDto getUserById(int id) throws Exception {
        User user1 =  userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user with userId "+id+"not found on the server"));
        return this.userToUserDto(user1);
    }

    @Override
    public List<UserDto> getAllUsers() {
      List<Dbusers> users = dbUsersRepository.findAll();
      List<UserDto> userDtoList =  users.stream().map(user -> new UserDto(user.getId(),user.getUserName(),user.getEmail(),user.getPassword(),user.getAbout(),user.getRoles())).filter(userDto -> userDto.getId()>0).collect(Collectors.toList());
     System.out.println("users dto list "+userDtoList.get(0).getRoles());
      return userDtoList;
    }

    @Override
    public String deleteUserById(int id) {
         userRepository.deleteById(id);
         return "deleted Successfully";
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        Dbusers dbuser = this.modelMapper.map(userDto,Dbusers.class);
        Role role = new Role();
        role.setId(502);
        role.setName("ROLE_NORMAL");
        dbuser.getRoles().add(role);
        Dbusers savedUser = this.dbUsersRepository.save(dbuser);
        return this.modelMapper.map(savedUser,UserDto.class);

    }

    public User userDtoToUser(UserDto userDto){
       User user = this.modelMapper.map(userDto,User.class);
       return user;
    }

    public UserDto userToUserDto(User user){
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        return userDto;

    }
}
