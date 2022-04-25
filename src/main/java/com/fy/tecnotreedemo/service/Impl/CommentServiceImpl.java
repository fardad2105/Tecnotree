package com.fy.tecnotreedemo.service.Impl;

import com.fy.tecnotreedemo.domain.comment.Comment;
import com.fy.tecnotreedemo.domain.comment.CommentDao;
import com.fy.tecnotreedemo.exception.CommentNotFoundException;
import com.fy.tecnotreedemo.service.CommentService;
import com.fy.tecnotreedemo.web.domain.CommentDto;
import com.fy.tecnotreedemo.web.responses.CommentDtoResponse;
import com.fy.tecnotreedemo.web.responses.PageDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    private final Logger LOG = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Override
    public PageDto<CommentDto> getComments(PageRequest pageRequest) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Start debug for get pagination list of Comments");
        }
        LOG.info("going to get pagination list of Comments ");
        final var comments = commentDao.findAll(pageRequest);
        return buildDto(comments);
    }

    @Override
    public List<CommentDto> getAllComments() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Start debug for get list of all Comments");
        }
        LOG.info("going to get all list of Comments ");
        return commentDao.findAll().stream().map(this::toCommentDto).toList();
    }

    @Override
    public CommentDtoResponse saveComment(CommentDto commentDto) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Start debug for Save Comment");
        }
        Comment comment = new Comment();
        comment.setPostId(commentDto.postId());
        comment.setName(commentDto.name());
        comment.setEmail(commentDto.email());
        comment.setBody(commentDto.body());

        return toCommentResponseDto(commentDao.save(comment));
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("start debug for updateComment");
        }
        if (!commentDao.existsById(id)) {
            throw new CommentNotFoundException("Comment with id: " + id + " not found!", HttpStatus.NOT_FOUND);
        } else {
            Comment updatedComment = commentDao.findById(id).get();
            updatedComment.setName(commentDto.name());
            updatedComment.setEmail(commentDto.email());
            updatedComment.setBody(commentDto.body());
            LOG.info("comment with email {} updated", updatedComment.getEmail());
            return toCommentDto(commentDao.save(updatedComment));
        }
    }


    @Override
    public void deletedCommentById(long id) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Start debug for deletePostById");
        }
        if (!commentDao.existsById(id))
            throw new CommentNotFoundException("Comment with id: " + id + " not found!",HttpStatus.NOT_FOUND);

        LOG.info("Comment with id {} deleted",id);
        commentDao.deleteById(id);
    }



    private PageDto<CommentDto> buildDto(final Page<Comment> page) {
        final var commentDtos = page.stream()
                .map(this::toCommentDto)
                .toList();

        return new PageDto<CommentDto>(page.getNumber(),
                page.getSize(),
                commentDtos,
                page.getTotalElements(),
                page.getTotalPages());
    }

    private CommentDto toCommentDto(Comment comment) {

        return new CommentDto(
                comment.getPostId(),
                comment.getName(),
                comment.getEmail(),
                comment.getBody()
        );
    }

    private CommentDtoResponse toCommentResponseDto(Comment comment) {

        return new CommentDtoResponse(
                comment.getId(),
                comment.getPostId(),
                comment.getName(),
                comment.getEmail(),
                comment.getBody()
        );
    }
}
