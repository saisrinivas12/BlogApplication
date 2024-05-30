package com.example.BlogApplication.service;

import com.example.BlogApplication.entities.Post;
import com.example.BlogApplication.entities.PostResponse;
import com.example.BlogApplication.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId ) throws Exception;
    PostResponse getAllPosts(Integer pageNo, Integer pageSize,String sortBy,String sortDir);

    PostDto updatePost(PostDto postDto,Integer postId) throws Exception;

    PostDto getPostById(Integer postId) throws Exception;


    String deletePostById(Integer postId);

    List<PostDto>getPostByUserId(Integer userId) throws Exception;

    List<PostDto>getPostByCategoryId(Integer categoryId) throws Exception;

    List<PostDto>searchKeywords(String keyword);
}
