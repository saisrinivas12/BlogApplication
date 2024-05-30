package com.example.BlogApplication.service;

import com.example.BlogApplication.Repository.CommentRepository;
import com.example.BlogApplication.Repository.PostRepository;
import com.example.BlogApplication.entities.Comment;
import com.example.BlogApplication.entities.Post;
import com.example.BlogApplication.exception.ResourceNotFoundException;
import com.example.BlogApplication.payload.CommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) throws Exception{
        Post post =postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Requested PostId not found on the server !!"));
        Comment comment = this.mapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepository.save(comment);
        return this.mapper.map(comment,CommentDto.class);
    }

    @Override
    public String deleteCommentByCommentId(Integer commentId) throws Exception{
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Requested commentId not found on the server"));
        this.commentRepository.delete(comment);
        return "Deleted Successfully";
    }
}
