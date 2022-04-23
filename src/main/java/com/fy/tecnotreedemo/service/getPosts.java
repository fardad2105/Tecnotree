package com.fy.tecnotreedemo.service;

import com.fy.tecnotreedemo.domain.post.Post;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface getPosts {
     CompletableFuture<Set<Post>> catchPosts() throws InterruptedException;
}
