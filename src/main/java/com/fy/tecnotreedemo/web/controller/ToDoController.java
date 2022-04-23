package com.fy.tecnotreedemo.web.controller;

import com.fy.tecnotreedemo.domain.todo.ToDo;
import com.fy.tecnotreedemo.service.ToDoService;
import com.fy.tecnotreedemo.web.domain.ToDoDto;
import com.fy.tecnotreedemo.web.responses.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping
    public PageDto<ToDoDto> getToDos(final HttpServletRequest request,
                                     @RequestParam(value = "page", defaultValue = "0") final int page,
                                     @RequestParam(value = "perPage", defaultValue = "10") final int perPage) {
        return toDoService.getToDos(PageRequest.of(page, perPage));
    }

    @GetMapping("/get-todos")
    public ResponseEntity<List<ToDoDto>> getToDosWithCustomCondition(final HttpServletRequest request,
                                                                     @RequestParam(value = "userId", defaultValue = "1") long userId,
                                                                     @RequestParam(value = "completed", defaultValue = "true") Boolean completed) {
        return new ResponseEntity<List<ToDoDto>>(toDoService.getToDosWithCustomCondition(userId, completed), HttpStatus.OK);
    }
}
