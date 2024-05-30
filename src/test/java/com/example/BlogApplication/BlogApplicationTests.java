package com.example.BlogApplication;

import com.example.BlogApplication.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {


	@Autowired
	private UserRepository userRepository;
	@Test
	void contextLoads() {
	}


	@Test
	public void knowRepositoryClass(){
       Class<?> classe  = this.userRepository.getClass();
	   String className = classe.getName();
	   System.out.println("class Name "+className);


	}

}
