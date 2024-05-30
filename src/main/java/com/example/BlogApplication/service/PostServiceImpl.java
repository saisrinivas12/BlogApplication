package com.example.BlogApplication.service;

import com.example.BlogApplication.Repository.CategoryRepository;
import com.example.BlogApplication.Repository.PostRepository;
import com.example.BlogApplication.Repository.UserRepository;
import com.example.BlogApplication.entities.Category;
import com.example.BlogApplication.entities.Post;
import com.example.BlogApplication.entities.PostResponse;
import com.example.BlogApplication.entities.User;
import com.example.BlogApplication.exception.ResourceNotFoundException;
import com.example.BlogApplication.payload.PostDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) throws Exception {
        postDto.setAddedDate(new Date());
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Requested category Id not found on the server"));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Requested userId not found on the server"));

        Post post = this.mapper.map(postDto,Post.class);
        post.setCategory(category);
        post.setUser(user);
       return this.mapper.map( this.postRepository.save(post),PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNo, Integer pageSize,String sortBy,String sortDir) {
        Sort.Direction direction = ("asc").equals(sortDir)?Sort.Direction.ASC:Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(direction,sortBy));
        Page<Post> page = this.postRepository.findAll(pageable);

        List<Post> posts = page.getContent();

        List<PostDto> postDtos = posts.stream().map(post -> this.mapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setPageNo(pageNo);
        postResponse.setPageSize(pageSize);


        postResponse.setContent(postDtos);
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setTotalElements(page.getTotalElements());
        postResponse.setLastPage(page.isLast());

      return postResponse;
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) throws Exception{
        postDto.setAddedDate(new Date());
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Requested PostId not Found on the server"));
        Post updatedPost = this.mapper.map(postDto,Post.class);
        System.out.println("updated post is "+updatedPost.getCategory());
        updatedPost.setPostId(postId);
        updatedPost.setCategory(this.categoryRepository.findById(post.getCategory().getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Requested category id not found on the server")));
        updatedPost.setUser(this.userRepository.findById(post.getUser().getId()).orElseThrow(()-> new ResourceNotFoundException("Requested userId not found on the server")));
        return this.mapper.map(this.postRepository.save(updatedPost),PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer postId) throws Exception{
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Requested PostId not found on the server"));
        System.out.println("got post "+post.getCategory());
        return this.mapper.map(post, PostDto.class);
    }

    @Override
    public String deletePostById(Integer postId) {
        this.postRepository.deleteById(postId);
        return "Deleted Successfully !!";
    }

    @Override
    public List<PostDto> getPostByUserId(Integer userId) throws Exception{

        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Requested UserId not found on the server"));

        List<Post> posts = this.postRepository.findByUser(user);
        List<PostDto> postDtos = posts.stream().map(post -> this.mapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByCategoryId(Integer categoryId) throws Exception {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Requested categoryId not found on the server"));

        List<Post>posts = this.postRepository.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map(post -> this.mapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchKeywords(String keyword) {
      List<Post> posts = this.postRepository.searchPostByKeyword("%"+keyword+"%");
      List<PostDto>postDtos = posts.stream().map(post-> this.mapper.map(post,PostDto.class)).collect(Collectors.toList());
      return postDtos;
    }
}
