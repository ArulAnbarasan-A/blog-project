package com.service;

import java.util.List;

import com.dto.CommentDto;

public interface CommentService {
    CommentDto postComment(CommentDto commentDto);
    List<CommentDto> getAllComments();
    List<CommentDto> getCommentsByBlogId(Long blogId);
    void deleteCommentsByBlogId(Long blogId);
    CommentDto updateCommentByBlogId(Long blogId, CommentDto commentDto);
}
