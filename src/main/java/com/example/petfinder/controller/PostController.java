package com.example.petfinder.controller;

import com.example.petfinder.dto.IdResponseDto;
import com.example.petfinder.dto.MessageResponseDto;
import com.example.petfinder.dto.PostResponseDto;
import com.example.petfinder.dto.param.PostParameters;
import com.example.petfinder.entity.post.Post;
import com.example.petfinder.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController()
@RequestMapping(path = "/post")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @PostMapping
    public IdResponseDto savePost(@RequestBody Post post) throws ExecutionException, InterruptedException {
        return postService.savePost(post);
    }

    @GetMapping
    public Post getPost(PostParameters postParameters) throws ExecutionException, InterruptedException {
        return postService.getPost(postParameters.getId());
    }

    @PutMapping
    public IdResponseDto updatePost(@RequestBody Post post)  {
        return postService.updatePost(post);
    }

    @DeleteMapping
    public MessageResponseDto deletePost(PostParameters postParameters){
        return postService.deletePost(postParameters.getId());
    }
    @GetMapping(path = "/all")
    public PostResponseDto getAllPosts() throws ExecutionException, InterruptedException {
        return postService.getAllPosts();
    }
}
