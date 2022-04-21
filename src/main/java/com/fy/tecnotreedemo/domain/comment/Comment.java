package com.fy.tecnotreedemo.domain.comment;


import com.fy.tecnotreedemo.domain.post.Post;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "T_COMMENTS")
@RequiredArgsConstructor
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Column(updatable = true, name="BODY", columnDefinition = "varchar(max)")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;
}
