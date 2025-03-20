package com.service;

import com.dto.CommentDto;

public interface CommentService {
    CommentDto postComment(CommentDto commentDto);
}
