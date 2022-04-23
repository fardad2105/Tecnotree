package com.fy.tecnotreedemo.service;

import com.fy.tecnotreedemo.web.domain.CommentDto;
import com.fy.tecnotreedemo.web.responses.PageDto;
import org.springframework.data.domain.PageRequest;

public interface CommentService {

    PageDto<CommentDto> getComments(PageRequest pageRequest);
    CommentDto saveComment(CommentDto commentDto);
    CommentDto updateComment(long id,CommentDto commentDto);
    void deletedCommentById(long id);
}
