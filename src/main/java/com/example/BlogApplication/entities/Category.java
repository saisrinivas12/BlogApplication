package com.example.BlogApplication.entities;



import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="CATEGORY")
@Getter
@Setter

public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CATEGORY_ID")
    private int categoryId;

    @Column(name="CATEGORY_TITLE")
    @NotEmpty(message="categoryTitle should not be empty")
    @Size(min=4,message = "Please enter categoryTitle more than 4 chars !!")
    private String categoryTitle;


    @Column(name="CATEGORY_NAME")
    @NotEmpty(message="categoryName should not be empty !!")
    @Size(min=4,message = "Please enter categoryName more than 4 chars !!")
    private String categoryName;
    @Column(name="CATEGORY_DESCRIPTION")
    @NotEmpty(message = "categoryDescription should not be empty !!")
    @Size(min = 10,message = "Please enter categoryDescription more than 10 chars!!")
    private String categoryDescription;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post>posts = new ArrayList<>();

    public Category(int categoryId, String categoryTitle, String categoryName, String categoryDescription) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
    public Category(){}
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
