package com.service;

import com.dto.BlogDto;

public interface BlogService {
	BlogDto createBlog(BlogDto blogDto);
    BlogDto getBlogById(Long id);
    BlogDto updateBlog(Long id, BlogDto blogDto);
    String deleteBlog(Long id);
}
