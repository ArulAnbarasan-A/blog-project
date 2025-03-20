package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dto.BlogDto;
import com.entity.Blog;
import com.exception.BlogNotFoundException;
import com.exception.InvalidValueException;
import com.repository.BlogRepository;

@Service
public class BlogServiceImple implements BlogService {
    private final BlogRepository blogRepository;

    public BlogServiceImple(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogDto createBlog(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        Blog savedBlog = blogRepository.save(blog);
        blogDto.setId(savedBlog.getId());
        return blogDto;
    }

    @Override
    public BlogDto getBlogById(Long id) {
    	 if (id < 0) {
             throw new InvalidValueException("Blog ID should be above 0");
         }
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + id));
        BlogDto blogDto = new BlogDto();
        blogDto.setId(blog.getId());
        blogDto.setTitle(blog.getTitle());
        blogDto.setContent(blog.getContent());
        return blogDto;
    }

    @Override
    public BlogDto updateBlog(Long id, BlogDto blogDto) {
    	if (id < 0) {
            throw new InvalidValueException("Blog ID should be above 0");
        }
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + id));
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        Blog updatedBlog = blogRepository.save(blog);
        blogDto.setId(updatedBlog.getId());
        return blogDto;
    }

    @Override
    public String deleteBlog(Long id) {
    	 if (id < 0) {
             throw new InvalidValueException("Blog ID should be above 0");
         }
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + id));
        blogRepository.delete(blog);
        return "Blog deleted successfully";
    }
    
    @Override
    public List<BlogDto> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream().map(blog -> {
            BlogDto blogDto = new BlogDto();
            blogDto.setId(blog.getId());
            blogDto.setTitle(blog.getTitle());
            blogDto.setContent(blog.getContent());
            return blogDto;
        }).collect(Collectors.toList());
    }
}