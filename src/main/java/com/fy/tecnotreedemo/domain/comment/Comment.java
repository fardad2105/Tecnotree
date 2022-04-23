package com.fy.tecnotreedemo.domain.comment;



import lombok.Data;
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
    private Long postId;
    private String name;
    private String email;
    @Column(updatable = true, name="BODY", columnDefinition = "varchar(max)")
    private String body;

//    @ManyToOne(fetch = FetchType.LAZY,optional = false)
//    @JoinColumn(name = "postId", nullable = false,referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Post post;
}
