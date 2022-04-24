package com.fy.tecnotreedemo.service.Impl;

import com.fy.tecnotreedemo.exception.PostNotFoundException;
import com.fy.tecnotreedemo.service.PostService;
import com.fy.tecnotreedemo.web.domain.PostDto;
import com.fy.tecnotreedemo.web.responses.PageDto;
import com.fy.tecnotreedemo.web.responses.PostDtoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceImplTest {


    @Autowired
    private PostService postService;

    private PostDto postDto;
    private PostDtoResponse savedPost;


    @BeforeEach
    void setUp() {
        postDto = new PostDto(1L, "unit test eos post title", "this is unit test body");
    }

    @AfterEach
    void tearDown() {
        if (savedPost != null)
            postService.deletePostById(savedPost.id());
    }

    @Test
    void getPosts() {
        savedPost = postService.savePost(postDto);
        PageDto<PostDto> getPosts = postService.getPosts(PageRequest.of(0, 10));
        List<PostDto> postDtoList = getPosts.body().stream().toList();
        assertTrue(postDtoList.size() != 0);
    }

    @Test
    void getPostById() {
        savedPost = postService.savePost(postDto);
        assertEquals(savedPost.title(), postService.getPostById(savedPost.id()).title());
    }

    @Test
    void getPostsWithCustomKeyword() {
        savedPost = postService.savePost(postDto);
        List<PostDto> postDtos = postService.getPostsWithCustomKeyword("eos");
        assertFalse(postDtos.isEmpty());
    }

    @Test
    void savePost() {
        savedPost = postService.savePost(postDto);
        assertEquals(savedPost.title(), postDto.title());
    }

    @Test
    void updatePost() {
        savedPost = postService.savePost(postDto);
        PostDto updatedPost = new PostDto(1l, "update unit test eos post title",
                "this is unit test body update");
        PostDto returnUpdatedPost = postService.updatePost(savedPost.id(),updatedPost);
        assertEquals(returnUpdatedPost.title(),updatedPost.title());
    }

    @Test
    void deletePostById() {
        savedPost = postService.savePost(postDto);
        PostDto findPostDto = postService.getPostById(savedPost.id());
        assertEquals(savedPost.title(),findPostDto.title());
        postService.deletePostById(savedPost.id());

        List<PostDtoResponse> postDtoResponses = postService.getAllPosts()
                .stream()
                .filter(postDtoResponse -> postDtoResponse.title().equals(savedPost.title())).toList();

        assertEquals(postDtoResponses.size(),0);
        savedPost = null;

    }
}