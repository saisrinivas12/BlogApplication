package com.example.BlogApplication.Controllers;


import com.example.BlogApplication.service.ImageService;
import com.example.BlogApplication.service.StorageService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private StorageService service;

    @Autowired
    private ImageService imageService;

    @PostMapping("/db")
    public ResponseEntity<String>uploadImage(@RequestParam(name = "image")MultipartFile file)throws Exception{
        String result = service.uploadImage(file);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);

    }

}
