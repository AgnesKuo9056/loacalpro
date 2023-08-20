package com.pinyun.java.gradle.Controller;
import com.pinyun.java.gradle.Service.PostService;
import com.pinyun.java.gradle.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;



    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public Post createPost(@RequestBody Map<String, String> request) {
        String content = request.get("content");
        return postService.createPost(content);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String content = request.get("content");
        return postService.updatePost(id, content);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    }

