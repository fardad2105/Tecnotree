package com.fy.tecnotreedemo.service;

import com.fy.tecnotreedemo.domain.post.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface getPosts {
    public CompletableFuture<Set<Post>> getPosts() throws InterruptedException;
}
