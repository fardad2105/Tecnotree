package com.fy.tecnotreedemo.service;

import com.fy.tecnotreedemo.web.domain.ToDoDto;
import com.fy.tecnotreedemo.web.responses.PageDto;
import com.fy.tecnotreedemo.web.responses.ToDoDtoResponse;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ToDoService {

    PageDto<ToDoDto> getToDos(PageRequest pageRequest);
    List<ToDoDto> getToDosWithCustomCondition(long userId, Boolean completed);

    // used for test
    ToDoDtoResponse add (ToDoDto toDoDto);
    void deleteById (long id);
}
