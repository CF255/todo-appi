package com.boostmytool.todo_appi.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.boostmytool.todo_appi.models.TodoItem;
import com.boostmytool.todo_appi.repositories.TodoItemRepositories;



@Component
public class TodoItemDataLoader implements CommandLineRunner {
    

    private final Logger logger = LoggerFactory.getLogger(TodoItemDataLoader.class);
    @Autowired
    TodoItemRepositories todoItemRepositories;

    @Override
    public void run(String... args) throws Exception{
    loadseedData();
}

private void loadseedData(){
    if(todoItemRepositories.count() == 0){
        TodoItem todoItem1 = new TodoItem("Example");

        todoItemRepositories.save(todoItem1);


    }
    logger.info("Number of TodoItems: {}", todoItemRepositories.count());
}

}