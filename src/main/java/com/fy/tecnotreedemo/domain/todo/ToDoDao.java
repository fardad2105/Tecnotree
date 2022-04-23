package com.fy.tecnotreedemo.domain.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoDao extends JpaRepository<ToDo, Long> {

    List<ToDo> getByUserIdAndCompleted(Long userId,  Boolean completed);
}
