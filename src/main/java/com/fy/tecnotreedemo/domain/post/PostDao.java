package com.fy.tecnotreedemo.domain.post;

import com.fy.tecnotreedemo.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post, Long> {

    List<Post> findByTitleContaining(String title);
}
