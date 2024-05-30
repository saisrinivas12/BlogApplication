package com.example.BlogApplication.Repository;

import com.example.BlogApplication.entities.Dbusers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DBUsersRepository extends JpaRepository<Dbusers,Integer> {

    Optional<Dbusers> findByEmail(String userName);
}
