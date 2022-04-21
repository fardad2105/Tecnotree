package com.fy.tecnotreedemo.service;

import com.fy.tecnotreedemo.domain.comment.Comment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface getComments {
     CompletableFuture<Set<Comment>> getComments() throws InterruptedException;
}
