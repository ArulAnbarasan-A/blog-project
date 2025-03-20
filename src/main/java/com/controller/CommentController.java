package com.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CommentDto;
import com.service.CommentService;

@RestController
@RequestMapping("/api/blogs/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> postComment(@RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.postComment(commentDto));
    }
    
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }
    
    @GetMapping("/{blogId}")
    public ResponseEntity<List<CommentDto>> getCommentsByBlogId(@PathVariable Long blogId) {
        return ResponseEntity.ok(commentService.getCommentsByBlogId(blogId));
    }
    
    @PutMapping("/{blogId}")
    public ResponseEntity<CommentDto> updateCommentByBlogId(@PathVariable Long blogId, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateCommentByBlogId(blogId, commentDto));
    }
    
    @DeleteMapping("/{blogId}")
    public ResponseEntity<String> deleteCommentsByBlogId(@PathVariable Long blogId) {
        commentService.deleteCommentsByBlogId(blogId);
        return ResponseEntity.ok("All comments for blog ID " + blogId + " have been deleted.");
    }
}
