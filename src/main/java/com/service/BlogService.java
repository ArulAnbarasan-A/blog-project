package com.service;

import java.util.List;

import com.dto.BlogDto;

public interface BlogService {
	BlogDto createBlog(BlogDto blogDto);
    BlogDto getBlogById(Long id);
    BlogDto updateBlog(Long id, BlogDto blogDto);
    String deleteBlog(Long id);
    List<BlogDto> getAllBlogs();
}
