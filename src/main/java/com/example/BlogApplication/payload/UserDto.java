package com.example.BlogApplication.payload;

import com.example.BlogApplication.entities.Role;
import com.example.BlogApplication.utils.AboutValidator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


public class UserDto {


    private int id ;


    @NotEmpty(message = "userName should not be empty")
    private String userName;

    @Email(message = "Email that you provided is not valid!!")
    @NotEmpty(message = "email should not be empty")
    @Size(min=7, max=15, message = "Email size should be between 7 and 15 !!")
    private String email;

    @NotEmpty(message="password should not be empty")
    @Size(min=6,max=10,message="password length should between 6 and 10")
    private String password;


    @AboutValidator
    private String about;


    private Set<Role> roles = new HashSet<>();

    public UserDto(int id, String userName, String email, String password, String about,Set<Role>roles) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.about = about;
        this.roles=roles;
    }

    public UserDto(){

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", about='" + about + '\'' +
                ", roles=" + roles +
                '}';
    }
}
