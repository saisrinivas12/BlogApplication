package com.example.BlogApplication.entities;


import javax.persistence.*;

@Entity
@Table(name="COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @Column(name="COMMENT_CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
