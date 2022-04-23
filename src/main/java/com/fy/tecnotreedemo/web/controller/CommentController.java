package com.fy.tecnotreedemo.web.controller;

import com.fy.tecnotreedemo.service.CommentService;
import com.fy.tecnotreedemo.web.domain.CommentDto;
import com.fy.tecnotreedemo.web.responses.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public PageDto<CommentDto> getComments(final HttpServletRequest request,
                                           @RequestParam(value = "page", defaultValue = "0") final int page,
                                           @RequestParam(value = "perPage", defaultValue = "10") final int perPage) {
        return commentService.getComments(PageRequest.of(page,perPage));
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        CommentDto createdComment = commentService.saveComment(commentDto);
        return new ResponseEntity<CommentDto>(createdComment, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable long id,
                                                        @RequestBody CommentDto commentDto) {
        CommentDto updatedComment = commentService.updateComment(id, commentDto);
        return new ResponseEntity<CommentDto>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable long id) {
        commentService.deletedCommentById(id);
        return new ResponseEntity<>("comment with id: " + id + " deleted successful",HttpStatus.OK);
    }


}
