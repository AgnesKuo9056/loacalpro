package com.pinyun.java.gradle.Service;

import com.pinyun.java.gradle.Repository.CommentRepository;
import com.pinyun.java.gradle.Repository.PostRepository;
import com.pinyun.java.gradle.domain.Comment;
import com.pinyun.java.gradle.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;



    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(String content) {
        Post post = new Post();
        post.setContent(content);
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, String content) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setContent(content);
            return postRepository.save(post);
        }
        return null;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

}
