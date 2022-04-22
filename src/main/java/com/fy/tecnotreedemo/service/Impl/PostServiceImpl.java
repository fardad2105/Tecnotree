package com.fy.tecnotreedemo.service.Impl;

import com.fy.tecnotreedemo.domain.post.Post;
import com.fy.tecnotreedemo.domain.post.PostDao;
import com.fy.tecnotreedemo.exception.PostNotFoundException;
import com.fy.tecnotreedemo.service.PostService;
import com.fy.tecnotreedemo.web.domain.PostDto;
import com.fy.tecnotreedemo.web.responses.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostDao postDao;

    @Override
    public PageDto<PostDto> getPosts(PageRequest pageRequest) {
        final var posts = postDao.findAll(pageRequest);
        return buildDto(posts);
    }

    @Override
    public PostDto getPostById(Long id) {
        if (!postDao.existsById(id)) {
            throw new PostNotFoundException("Post with id: " + id + " not found!", HttpStatus.NOT_FOUND);
        }
        return toPostDto(postDao.getById(id));
    }

    @Override
    public List<PostDto> getPostsWithCustomKeyword(String title) {
        final var postDtos = postDao.findByTitleContaining(title);
        return  postDtos.stream()
                .map(
                        this::toPostDto
                ).toList();
    }

    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = new Post();
        post.setUserId(postDto.userId());
        post.setTitle(postDto.title());
        post.setBody(postDto.body());
        return toPostDto(postDao.save(post));
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        if (!postDao.existsById(id)) {
            throw new PostNotFoundException("Post with id: " + id + " not found!", HttpStatus.NOT_FOUND);
        } else {
            Post updatedPost = postDao.findById(id).get();
            updatedPost.setUserId(postDto.userId());
            updatedPost.setTitle(postDto.title());
            updatedPost.setBody(postDto.body());
            return toPostDto(postDao.save(updatedPost));
        }
    }

    @Override
    public void deletePostById(long id) {
        if (!postDao.existsById(id))
            throw new PostNotFoundException("Post with id: " + id + " not found!", HttpStatus.NOT_FOUND);
        postDao.deleteById(id);
    }


    private PageDto<PostDto> buildDto(final Page<Post> page) {
        final var postDtos = page.stream()
                .map(this::toPostDto)
                .toList();

        return new PageDto<PostDto>(page.getNumber(),
                page.getSize(),
                postDtos,
                page.getTotalElements(),
                page.getTotalPages());
    }

    private PostDto toPostDto(Post post) {

        return new PostDto(post.getUserId(),
                post.getTitle(),
                post.getBody()
        );
    }
}
