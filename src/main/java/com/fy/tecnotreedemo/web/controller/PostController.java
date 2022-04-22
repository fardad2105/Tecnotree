package com.fy.tecnotreedemo.web.controller;

import com.fy.tecnotreedemo.service.PostService;
import com.fy.tecnotreedemo.web.domain.PostDto;
import com.fy.tecnotreedemo.web.responses.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public PageDto<PostDto> getPosts(final HttpServletRequest request,
                                     @RequestParam(value = "page", defaultValue = "0") final int page,
                                     @RequestParam(value = "perPage", defaultValue = "10") final int perPage) {

        return postService.getPosts(PageRequest.of(page, perPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id) {
        PostDto postDto = postService.getPostById(id);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    @GetMapping("/posts-with-title")
    public ResponseEntity<List<PostDto>> getPostsWithCustomKeyword(final HttpServletRequest request,
                                                      @RequestParam(value = "title", defaultValue = "eos") final String title) {
        List<PostDto> postDtos = postService.getPostsWithCustomKeyword(title);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto createdPost = postService.savePost(postDto);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable long id,
                                                  @RequestBody PostDto postDto) {
        PostDto updatedPost = postService.updatePost(id, postDto);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("post with id: " + id + " deleted successful", HttpStatus.OK);
    }
}
