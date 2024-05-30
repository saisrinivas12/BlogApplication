package com.example.BlogApplication.Repository;

import com.example.BlogApplication.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User>findByEmail(String userName);
}
