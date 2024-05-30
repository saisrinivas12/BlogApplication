package com.example.BlogApplication.Controllers;

import com.example.BlogApplication.payload.CommentDto;
import com.example.BlogApplication.service.CommentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create/{postId}")
    public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto,@PathVariable(name="postId") Integer postId) throws Exception{
        CommentDto commentDto1 = this.commentService.createComment(commentDto,postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDto1);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String>deleteComment(@PathVariable(name="commentId")Integer commentId) throws Exception{
        String result = this.commentService.deleteCommentByCommentId(commentId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }


}
