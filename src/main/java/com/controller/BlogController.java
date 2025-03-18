package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dto.BlogDto;
import com.service.BlogService;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@RequestBody BlogDto blogDto) {
        return ResponseEntity.status(201).body(blogService.createBlog(blogDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable Long id, @RequestBody BlogDto blogDto) {
        return ResponseEntity.ok(blogService.updateBlog(id, blogDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.deleteBlog(id));
    }
}