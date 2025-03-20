package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dto.CommentDto;
import com.entity.Blog;
import com.entity.Comment;
import com.exception.BlogNotFoundException;
import com.repository.BlogRepository;
import com.repository.CommentRepository;

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
}
