package com.example.BlogApplication.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService{
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        //append a random file instead of original file

        String randomUUID = UUID.randomUUID().toString();
        String updatedFileName = randomUUID.concat(fileName.substring(fileName.lastIndexOf(".")));
        System.out.println("file uploaded is "+fileName);
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        String filePath = path+ File.separator+updatedFileName;
        file.transferTo(new File(filePath));
        return updatedFileName;

    }

    @Override
    public InputStream downloadImage(String path,String fileName) throws Exception {
        InputStream inputStream = new FileInputStream(path+File.separator+fileName);
        return inputStream;
    }
}
