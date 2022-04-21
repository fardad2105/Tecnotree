package com.fy.tecnotreedemo.service.Impl;

import com.fy.tecnotreedemo.domain.post.Post;
import com.fy.tecnotreedemo.domain.post.PostDao;
import com.fy.tecnotreedemo.service.getPosts;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class getPostsImpl implements getPosts {

    private final RestTemplate restTemplate;
    private final PostDao postDao;

    public getPostsImpl(RestTemplateBuilder restTemplateBuilder, PostDao postDao) {
        this.restTemplate = restTemplateBuilder.build();
        this.postDao = postDao;
    }

    @Async
    @Override
    public CompletableFuture<Set<Post>> getPosts() throws InterruptedException {
        String url = "https://jsonplaceholder.typicode.com/posts";
        Post[] posts = restTemplate.getForObject(url,Post[].class);
        Set<Post> postSet = Set.of(posts);
        postDao.saveAll(postSet);
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(postSet);
    }
}
