package com.fy.tecnotreedemo.service.Impl;

import com.fy.tecnotreedemo.domain.comment.Comment;
import com.fy.tecnotreedemo.exception.CommentNotFoundException;
import com.fy.tecnotreedemo.service.CommentService;
import com.fy.tecnotreedemo.web.domain.CommentDto;
import com.fy.tecnotreedemo.web.responses.CommentDtoResponse;
import com.fy.tecnotreedemo.web.responses.PageDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;

    private CommentDto commentDto;
    private CommentDtoResponse savedComment;

    @BeforeEach
    void setUp() {
        commentDto = new CommentDto(1L, "new comment test",
                "test@yahoo.com", "this is test comment body");
    }

    @AfterEach
    void tearDown() {
        if (savedComment != null)
            commentService.deletedCommentById(savedComment.id());
    }

    @Test
    void getComments() {
        savedComment = commentService.saveComment(commentDto);
        PageDto<CommentDto> getComments = commentService.getComments(PageRequest.of(0, 10));
        List<CommentDto> commentDtoList = getComments.body().stream().toList();
        assertTrue(commentDtoList.size() != 0);
    }

    @Test
    void saveComment() {
        savedComment = commentService.saveComment(commentDto);
        assertEquals(savedComment.email(), commentDto.email());
    }

    @Test
    void updateComment() {
        savedComment = commentService.saveComment(commentDto);
        CommentDto updatedComment = new CommentDto(savedComment.id(), savedComment.name(),
                "updateemail@yahoo.com", savedComment.body());
        CommentDto returnUpdatedPost = commentService.updateComment(savedComment.id(), updatedComment);
        assertEquals(returnUpdatedPost.email(), updatedComment.email());
    }

    @Test
    void updateCommentWhenCommentIsNotExist() {
        Exception exception = assertThrows(CommentNotFoundException.class, () -> {
            commentService.updateComment(10000, new CommentDto(2L, "update comment name",
                    "updatecomment@yahoo.com", "this is test comment body"));
        });

        String expectedMessage = "Comment with id: " + 10000 + " not found!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deletedCommentById() throws InterruptedException {
        savedComment = commentService.saveComment(commentDto);

        // before deleted
        List<CommentDto> beforeDeleted = commentService.getAllComments().stream()
                .filter(commentDtoResponse -> commentDtoResponse.email().equals(savedComment.email())).toList();
        assertEquals(beforeDeleted.size(), 1);

        // after deleted
        commentService.deletedCommentById(savedComment.id());
        List<CommentDto> afterDeleted = commentService.getAllComments().stream()
                .filter(commentDtoResponse -> commentDtoResponse.email().equals(savedComment.email())).toList();
        assertEquals(afterDeleted.size(), 0);
        savedComment = null;

    }

    @Test
    void deleteCommentWhenCommentNotExist() {
        Exception exception = assertThrows(CommentNotFoundException.class, () -> {
            commentService.deletedCommentById(10000);
        });

        String expectedMessage = "Comment with id: " + 10000 + " not found!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}