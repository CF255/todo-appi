package com.boostmytool.todo_appi.controller;


import java.time.Instant;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boostmytool.todo_appi.models.TodoItem;
import com.boostmytool.todo_appi.repositories.TodoItemRepositories;

import jakarta.validation.Valid;

@Controller
public class TodoItemController {
    private final Logger logger = LoggerFactory.getLogger(TodoItemController.class);

    @Autowired
    private TodoItemRepositories todoItemRepositories;

    @GetMapping("/")
    public ModelAndView index() {
        logger.debug("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", todoItemRepositories.findAll());
        modelAndView.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        return modelAndView;
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-todo-item";
        }

        todoItem.setCreateDate(Instant.now());
        todoItem.setModififiedDate(Instant.now());
        todoItemRepositories.save(todoItem);
        return "redirect:/";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") long id, @Valid TodoItem todoItem, BindingResult result, Model model){
        if(result.hasErrors()){
            todoItem.setId(id);
            return "update-todo-item";
        }

        todoItem.setModififiedDate((Instant.now()));
        todoItemRepositories.save(todoItem);
        return "redirect:/";
    }

}