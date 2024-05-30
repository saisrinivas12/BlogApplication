package com.example.BlogApplication.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    public String uploadImage(MultipartFile file) throws IOException;
}
