package com.pinyun.java.gradle.Service;

import com.pinyun.java.gradle.Repository.CommentRepository;
import com.pinyun.java.gradle.Repository.PostRepository;
import com.pinyun.java.gradle.domain.Comment;
import com.pinyun.java.gradle.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CommentService {

    @Autowired
    private PostRepository postRepository;


    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Long postId, String content) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setPost(post);
            post.getComments().add(comment);
            commentRepository.save(comment);
            return comment;
        }
        return null;
    }
}
