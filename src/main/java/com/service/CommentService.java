package com.service;

import java.util.List;

import com.dto.CommentDto;

public interface CommentService {
    CommentDto postComment(CommentDto commentDto);
    List<CommentDto> getAllComments();
}
