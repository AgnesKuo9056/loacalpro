package com.pinyun.java.gradle.Controller;

import com.pinyun.java.gradle.Service.CommentService;
import com.pinyun.java.gradle.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{id}/comments")
    public Comment addComment(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String content = request.get("content");
        return commentService.addComment(id, content);

    }
}
