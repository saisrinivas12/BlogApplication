package com.example.BlogApplication.Repository;

import com.example.BlogApplication.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
