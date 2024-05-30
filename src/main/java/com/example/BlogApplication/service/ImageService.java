package com.example.BlogApplication.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

    String uploadImage(String path, MultipartFile file) throws IOException;

    InputStream downloadImage(String path,String fileName) throws Exception;
}
