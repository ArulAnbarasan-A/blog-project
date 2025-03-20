package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dto.CommentDto;
import com.entity.Blog;
import com.entity.Comment;
import com.exception.BlogNotFoundException;
import com.exception.CommentNotFoundException;
import com.repository.BlogRepository;
import com.repository.CommentRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentBlogServiceImple implements CommentService {
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    public CommentBlogServiceImple(BlogRepository blogRepository, CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto postComment(CommentDto commentDto) {
        Blog blog = blogRepository.findById(commentDto.getBlogId())
                .orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + commentDto.getBlogId()));
        System.out.println(blog);
        Comment comment = new Comment();
        comment.setBlog(blog);
        comment.setComment(commentDto.getComment());

        Comment savedComment = commentRepository.save(comment);
        commentDto.setId(savedComment.getId());
        return commentDto;
    }
    
    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setBlogId(comment.getBlog().getId());
            commentDto.setComment(comment.getComment());
            return commentDto;
        }).collect(Collectors.toList());
    }
    
    @Override
    public List<CommentDto> getCommentsByBlogId(Long blogId) {
        List<Comment> comments = commentRepository.findByBlogId(blogId);
        
        return comments.stream().map(comment -> {
            CommentDto dto = new CommentDto();
            dto.setId(comment.getId());
            dto.setBlogId(comment.getBlog().getId());
            dto.setComment(comment.getComment());
            return dto;
        }).collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public void deleteCommentsByBlogId(Long blogId) {
        if (!blogRepository.existsById(blogId)) {
            throw new BlogNotFoundException("Blog not found with id: " + blogId);
        }
        commentRepository.deleteByBlogId(blogId);
    }
    
    @Override
    public CommentDto updateCommentByBlogId(Long blogId, CommentDto commentDto) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + blogId));

        List<Comment> comments = blog.getComments();
        if (comments.isEmpty()) {
            throw new CommentNotFoundException("No comments found for blog with id: " + blogId);
        }

        // Get the latest comment
        Comment latestComment = comments.get(comments.size() - 1);
        latestComment.setComment(commentDto.getComment());

        Comment updatedComment = commentRepository.save(latestComment);

        CommentDto responseDto = new CommentDto();
        responseDto.setId(updatedComment.getId());
        responseDto.setBlogId(blogId);
        responseDto.setComment(updatedComment.getComment());

        return responseDto;
    }
}
