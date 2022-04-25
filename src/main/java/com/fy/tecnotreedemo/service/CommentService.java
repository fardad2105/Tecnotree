package com.fy.tecnotreedemo.service;

import com.fy.tecnotreedemo.web.domain.CommentDto;
import com.fy.tecnotreedemo.web.responses.CommentDtoResponse;
import com.fy.tecnotreedemo.web.responses.PageDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CommentService {

    PageDto<CommentDto> getComments(PageRequest pageRequest);
    List<CommentDto> getAllComments();
    CommentDtoResponse saveComment(CommentDto commentDto);
    CommentDto updateComment(long id,CommentDto commentDto);
    void deletedCommentById(long id);
}
