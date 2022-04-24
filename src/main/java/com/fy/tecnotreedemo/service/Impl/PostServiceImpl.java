package com.fy.tecnotreedemo.service.Impl;

import com.fy.tecnotreedemo.domain.post.Post;
import com.fy.tecnotreedemo.domain.post.PostDao;
import com.fy.tecnotreedemo.exception.PostNotFoundException;
import com.fy.tecnotreedemo.service.PostService;
import com.fy.tecnotreedemo.web.domain.PostDto;
import com.fy.tecnotreedemo.web.responses.PageDto;
import com.fy.tecnotreedemo.web.responses.PostDtoResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostDao postDao;

    private final Logger LOG = LoggerFactory.getLogger(PostServiceImpl.class);

    @Override
    public PageDto<PostDto> getPosts(PageRequest pageRequest) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Start debug for get pagination list of Posts");
        }
        LOG.info("going to get pagination list of Posts ");
        final var posts = postDao.findAll(pageRequest);
        return buildDto(posts);
    }

    @Override
    public List<PostDtoResponse> getAllPosts() {
        return postDao.findAll().stream().map(this::toPostResponseDto).toList();
    }

    @Override
    public PostDto getPostById(Long id) {
        if (!postDao.existsById(id)) {
            throw new PostNotFoundException("Post with id: " + id + " not found!", HttpStatus.NOT_FOUND);
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("Start debug for get post by id");
        }
        LOG.info("going to get post by id {}", id);
        return toPostDto(postDao.getById(id));
    }

    @Override
    public List<PostDto> getPostsWithCustomKeyword(String title) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Start debug for getPostsWithCustomKeyword");
        }
        final var postDtos = postDao.findByTitleContaining(title);
        if (postDtos.size() == 0) {
            throw new PostNotFoundException("Posts with this condition not found!",HttpStatus.NOT_FOUND);
        }
        List<PostDto> postDtoList = postDtos.stream()
                .map(this::toPostDto).toList();
        LOG.info("get list with this condition keyword has size {}", postDtoList.size());
        return postDtoList;
    }

    @Override
    public PostDtoResponse savePost(PostDto postDto) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Start debug for savePost");
        }
        Post post = new Post();
        post.setUserId(postDto.userId());
        post.setTitle(postDto.title());
        post.setBody(postDto.body());
        return toPostResponseDto(postDao.save(post));
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Start debug for updatePost");
        }
        if (!postDao.existsById(id)) {
            throw new PostNotFoundException("Post with id: " + id + " not found!", HttpStatus.NOT_FOUND);
        } else {
            Post updatedPost = postDao.findById(id).get();
            updatedPost.setUserId(postDto.userId());
            updatedPost.setTitle(postDto.title());
            updatedPost.setBody(postDto.body());
            LOG.info("post with title {} updated", updatedPost.getTitle());
            return toPostDto(postDao.save(updatedPost));
        }
    }

    @Override
    public void deletePostById(long id) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Start debug for deletePostById");
        }
        if (!postDao.existsById(id))
            throw new PostNotFoundException("Post with id: " + id + " not found!", HttpStatus.NOT_FOUND);

        LOG.info("Post with id {} deleted", id);
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

    private PostDtoResponse toPostResponseDto(Post post) {

        return new PostDtoResponse(post.getId(),
                post.getUserId(),
                post.getTitle(),
                post.getBody()
        );
    }
}
