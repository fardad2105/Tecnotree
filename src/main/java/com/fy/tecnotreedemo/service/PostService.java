package com.fy.tecnotreedemo.service;

import com.fy.tecnotreedemo.domain.post.Post;
import com.fy.tecnotreedemo.web.domain.PostDto;
import com.fy.tecnotreedemo.web.responses.PageDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PostService {

    PageDto<PostDto> getPosts(PageRequest pageRequest);
    PostDto getPostById(Long id);
    List<PostDto> getPostsWithCustomKeyword(String title);
    PostDto savePost(PostDto postDto);
    PostDto updatePost(long id , PostDto postDto);
    void deletePostById(long id);

}
