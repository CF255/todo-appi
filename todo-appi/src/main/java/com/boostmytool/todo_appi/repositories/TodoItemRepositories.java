package com.boostmytool.todo_appi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.boostmytool.todo_appi.models.TodoItem;

public interface TodoItemRepositories extends CrudRepository<TodoItem, Long> {

    

}
