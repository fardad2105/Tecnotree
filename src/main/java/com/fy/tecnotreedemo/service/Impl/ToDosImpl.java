package com.fy.tecnotreedemo.service.Impl;

import com.fy.tecnotreedemo.domain.todo.ToDo;
import com.fy.tecnotreedemo.domain.todo.ToDoDao;
import com.fy.tecnotreedemo.service.ToDoService;
import com.fy.tecnotreedemo.web.domain.ToDoDto;
import com.fy.tecnotreedemo.web.responses.PageDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDosImpl implements ToDoService {

    private final ToDoDao toDoDao;

    private final Logger LOG = LoggerFactory.getLogger(ToDosImpl.class);

    @Override
    public PageDto<ToDoDto> getToDos(PageRequest pageRequest) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Start debug for get pagination list of ToDos");
        }
        LOG.info("going to get pagination list of ToDos ");
        final var todos = toDoDao.findAll(pageRequest);
        return buildDto(todos);
    }

    @Override
    public List<ToDoDto> getToDosWithCustomCondition(long userId, Boolean completed) {
        LOG.info("get list of all todos with userId {} and Completed True", userId);
        final var todoDtos = toDoDao.getByUserIdAndCompleted(userId,completed);
        List<ToDoDto> toDoDtoList = todoDtos.stream()
                .map(this::toToDoDto).toList();
        LOG.info("get list with this condition has size {}", toDoDtoList.size());
        return toDoDtoList;
    }

    private PageDto<ToDoDto> buildDto(final Page<ToDo> page) {
        final var todoDtos = page.stream()
                .map(this::toToDoDto)
                .toList();
        return new PageDto<ToDoDto>(page.getNumber(),
                page.getSize(),
                todoDtos,
                page.getTotalElements(),
                page.getTotalPages()
                );
    }

    private ToDoDto toToDoDto(ToDo toDo) {

        return new ToDoDto(toDo.getUserId(),
                toDo.getTitle(),
                toDo.getCompleted()
        );
    }
}
