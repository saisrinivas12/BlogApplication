package com.example.BlogApplication.service;

import com.example.BlogApplication.Repository.ImageRepository;
import com.example.BlogApplication.entities.ImageData;
import com.example.BlogApplication.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;

@Service
public class StorageServiceImpl implements StorageService{


    @Autowired
    private ImageRepository imageRepository;
    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData data = new ImageData();
        data.setImageName(file.getOriginalFilename());
        data.setImageType(file.getContentType());
        data.setBytes(ImageUtils.compressImage(file.getBytes()));
        imageRepository.save(data);
        return "Uploaded Successfully";
    }
}
