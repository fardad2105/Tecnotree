package com.fy.tecnotreedemo.service.Impl;

import com.fy.tecnotreedemo.domain.post.Post;
import com.fy.tecnotreedemo.domain.post.PostDao;
import com.fy.tecnotreedemo.service.getPosts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger LOG = LoggerFactory.getLogger(getPostsImpl.class);

    public getPostsImpl(RestTemplateBuilder restTemplateBuilder, PostDao postDao) {
        this.restTemplate = restTemplateBuilder.build();
        this.postDao = postDao;
    }

    @Async // @Scheduled(fixedRate = 10_000)
    @Override
    public CompletableFuture<Set<Post>> catchPosts() throws InterruptedException {
        LOG.info("Starting Catch Posts data ....");
        String url = "https://jsonplaceholder.typicode.com/posts";
        Post[] posts = restTemplate.getForObject(url,Post[].class);
        assert posts != null;
        Set<Post> postSet = Set.of(posts);
        postDao.saveAll(postSet);
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(postSet);
    }
}
