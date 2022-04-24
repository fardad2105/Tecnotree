package com.fy.tecnotreedemo.service.Impl;

import com.fy.tecnotreedemo.service.ToDoService;
import com.fy.tecnotreedemo.web.domain.ToDoDto;
import com.fy.tecnotreedemo.web.responses.PageDto;
import com.fy.tecnotreedemo.web.responses.ToDoDtoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.function.AsyncServerResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ToDosImplTest {

    @Autowired
    private ToDoService toDoService;

    private ToDoDto toDoDto;
    private ToDoDtoResponse savedToDo;

    @BeforeEach
    void setUp() {
        toDoDto = new ToDoDto(1L,"this test todo", true);
        savedToDo = toDoService.add(toDoDto);
    }

    @AfterEach
    void tearDown() {
        if (savedToDo != null) {
            toDoService.deleteById(savedToDo.id());
        }
    }

    @Test
    void getToDos() {
        PageDto<ToDoDto> toDoDtoPage = toDoService.getToDos(PageRequest.of(0,10));
        List<ToDoDto> toDoDtoList = toDoDtoPage.body().stream().toList();
        System.out.println(toDoDtoList.size());
        assertTrue(toDoDtoList.size() != 0);
    }

    @Test
    void getToDosWithCustomCondition() {
        List<ToDoDto> toDoDtoList = toDoService.getToDosWithCustomCondition(savedToDo.userId(), savedToDo.completed());
        assertTrue(toDoDtoList.size() != 0);
    }
}