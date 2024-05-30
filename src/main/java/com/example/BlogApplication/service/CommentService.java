package com.example.BlogApplication.service;

import com.example.BlogApplication.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId) throws Exception;

    String deleteCommentByCommentId(Integer commentId) throws Exception;
}
