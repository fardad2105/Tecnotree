package com.fy.tecnotreedemo.service;

import com.fy.tecnotreedemo.domain.todo.ToDo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface getToDos {
    public CompletableFuture<Set<ToDo>> getToDos() throws InterruptedException;
}
