package com.fy.tecnotreedemo.domain.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoDao extends JpaRepository<ToDo, Long> {
}
