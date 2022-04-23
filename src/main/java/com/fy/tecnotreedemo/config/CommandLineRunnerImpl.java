package com.fy.tecnotreedemo.config;

import com.fy.tecnotreedemo.domain.comment.Comment;
import com.fy.tecnotreedemo.domain.post.Post;
import com.fy.tecnotreedemo.domain.todo.ToDo;
import com.fy.tecnotreedemo.service.getComments;
import com.fy.tecnotreedemo.service.getPosts;
import com.fy.tecnotreedemo.service.getToDos;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerImpl  implements CommandLineRunner {

    private final getPosts GetPosts;
    private final getToDos GetToDos;
    private final getComments GetComments;

    @Override
    public void run(String... args) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        CompletableFuture<Set<Post>> postList = GetPosts.getPosts();
        CompletableFuture<Set<Comment>> commentList = GetComments.getComments();
        CompletableFuture<Set<ToDo>> toDosList = GetToDos.getToDos();

        // Wait until they are all done
        CompletableFuture.allOf(postList,commentList,toDosList);

    }
}
