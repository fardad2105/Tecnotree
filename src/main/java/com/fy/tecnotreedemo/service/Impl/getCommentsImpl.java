package com.fy.tecnotreedemo.service.Impl;

import com.fy.tecnotreedemo.domain.comment.Comment;
import com.fy.tecnotreedemo.domain.comment.CommentDao;
import com.fy.tecnotreedemo.service.getComments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class getCommentsImpl implements getComments {

    private final RestTemplate restTemplate;
    private final CommentDao commentDao;

    private final Logger LOG = LoggerFactory.getLogger(getCommentsImpl.class);

    public getCommentsImpl(RestTemplateBuilder restTemplateBuilder, CommentDao commentDao) {
        this.restTemplate = restTemplateBuilder.build();
        this.commentDao = commentDao;
    }

    @Async // @Scheduled(fixedRate = 10_000)
    @Override
    public CompletableFuture<Set<Comment>> catchComments() throws InterruptedException {
        LOG.info("Starting Catch Comment data....");
        String url = "https://jsonplaceholder.typicode.com/comments";
        Comment[] comments = restTemplate.getForObject(url, Comment[].class);
        assert comments != null;
        Set<Comment> commentSet = Set.of(comments);
        commentDao.saveAll(commentSet);
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(commentSet);
    }
}
