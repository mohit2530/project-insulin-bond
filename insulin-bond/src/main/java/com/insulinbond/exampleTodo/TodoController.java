package com.insulinbond.exampleTodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class TodoController {
    @Autowired
    private TodoService ts;

    @PostMapping("/addTodo")
    public void addTodo(@RequestBody TodoModel todo) {
        ts.AddTodo(todo);

    }

    @GetMapping("/getTodo")
    public Optional<List<TodoModel>> getTodos() {
        return ts.findAll();
    }

    @PutMapping("/updateTodo/{id}")
    public void updateTodo(@PathVariable String id) {
        ts.updateTodo(id);
    }

    @GetMapping("/getCompleted")
    public Optional<List<TodoModel>> getCompleted() {
        return ts.findAllComplete();
    }

    @DeleteMapping("/deleteTodo/{id}")
    public void deleteTodo(@PathVariable String id) {
        ts.deleteTodo(id);
    }
}
