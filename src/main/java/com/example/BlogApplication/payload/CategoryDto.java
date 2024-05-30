package com.example.BlogApplication.payload;

public class CategoryDto {
    private int categoryId;

    private String categoryTitle;

    private String categoryName;

    private String categoryDescription;

    public CategoryDto(int categoryId, String categoryTitle, String categoryName, String categoryDescription) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public CategoryDto(){};

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
}
