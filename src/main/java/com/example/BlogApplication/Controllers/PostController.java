package com.example.BlogApplication.Controllers;


import com.example.BlogApplication.config.AppConstants;
import com.example.BlogApplication.entities.Post;
import com.example.BlogApplication.entities.PostResponse;
import com.example.BlogApplication.payload.PostDto;
import com.example.BlogApplication.service.ImageService;
import com.example.BlogApplication.service.PostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService service;

    @Autowired
    private ImageService imageService;

    @Value("${project.images}")
    private String destinationFolder;

    @PostMapping("/users/{userId}/categories/{categoryId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) throws Exception {
        PostDto post = this.service.createPost(postDto, userId, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("/get")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(name="pageNo",defaultValue = AppConstants.PAGE_NO,required = false
    )Integer pageNo,@RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
                                                    @RequestParam(name="sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                    @RequestParam(name="sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir){
        PostResponse postResponse = this.service.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(postResponse);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) throws Exception {
        PostDto postDto1 = this.service.updatePost(postDto, postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(postDto1);

    }

    @GetMapping("/get/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) throws Exception {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.getPostById(postId));
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable Integer postId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.deletePostById(postId));
    }

    @GetMapping("/get/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) throws Exception {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.getPostByCategoryId(categoryId));
    }

    @GetMapping("/get/users/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) throws Exception {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.getPostByUserId(userId));

    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>>searchPostsByKeyword(@PathVariable(name="keyword")String keyword){
        List<PostDto>posts = this.service.searchKeywords(keyword);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(posts);
    }


    @PostMapping("/upload/{postId}")
    public ResponseEntity<PostDto>uploadImageForPost(@RequestParam(name="image")MultipartFile image,
                                                     @PathVariable(name="postId")int postId) throws Exception{

        PostDto postDto = this.service.getPostById(postId);
         String uploadResult = this.imageService.uploadImage(destinationFolder,image);

         if(uploadResult!=null){
             postDto.setImageName(uploadResult);
             PostDto p = this.service.updatePost(postDto,postId);
             return ResponseEntity.status(HttpStatus.ACCEPTED).body(postDto);
         }
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();


    }

    @GetMapping("/download/{imageId}")
    public void downloadImage(@PathVariable(name="imageId")String imageId, HttpServletResponse response) throws Exception{
        InputStream ip = this.imageService.downloadImage(destinationFolder,imageId);
        response.setContentType("application/jpeg");
        OutputStream op = response.getOutputStream();
        op.write(ip.readAllBytes());

    }
}