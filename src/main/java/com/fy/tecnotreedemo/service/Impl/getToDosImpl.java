package com.fy.tecnotreedemo.service.Impl;

import com.fy.tecnotreedemo.domain.todo.ToDo;
import com.fy.tecnotreedemo.domain.todo.ToDoDao;
import com.fy.tecnotreedemo.service.getToDos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class getToDosImpl implements getToDos {

    private final RestTemplate restTemplate;
    private final ToDoDao toDoDao;

    private final Logger LOG = LoggerFactory.getLogger(getToDosImpl.class);

    public getToDosImpl(RestTemplateBuilder restTemplateBuilder, ToDoDao toDoDao) {
        this.restTemplate = restTemplateBuilder.build();
        this.toDoDao = toDoDao;
    }

    @Async
    @Override
    public CompletableFuture<Set<ToDo>> catchToDos() throws InterruptedException {
        LOG.info("Starting Catch todos data ....");
        String url = "https://jsonplaceholder.typicode.com/todos";
        ToDo[] toDos = restTemplate.getForObject(url, ToDo[].class);
        Set<ToDo> toDoSet = Set.of(toDos);
        toDoDao.saveAll(toDoSet);
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(toDoSet);
    }
}
