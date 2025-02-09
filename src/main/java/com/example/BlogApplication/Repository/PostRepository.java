package com.example.BlogApplication.Repository;

import com.example.BlogApplication.entities.Category;
import com.example.BlogApplication.entities.Post;
import com.example.BlogApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post>findByUser(User user);

    List<Post>findByCategory(Category category);

    @Query("select p from Post p where p.title like :key")
    List<Post>searchPostByKeyword(@Param("key")String keyword);
}
