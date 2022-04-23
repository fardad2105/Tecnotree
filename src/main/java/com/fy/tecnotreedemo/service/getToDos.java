package com.fy.tecnotreedemo.service;

import com.fy.tecnotreedemo.domain.todo.ToDo;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface getToDos {
    public CompletableFuture<Set<ToDo>> catchToDos() throws InterruptedException;
}
