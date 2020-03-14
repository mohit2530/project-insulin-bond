package com.insulinbond.exampleTodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repo;

    public void AddTodo(TodoModel t){
        repo.save(t);
    }

    public Optional<TodoModel> getTodoByID(String id){
        return repo.findById(id);
    }

    public void deleteTodo(String id){
        repo.deleteById(id);

    }
    public void updateTodo(String id){
        TodoModel todo =  getTodoByID(id).get();
        todo.setComplete(true);
        repo.save(todo);
    }
    public Optional<List<TodoModel>> findAll(){
        return repo.findByIsCompleteFalse();
    }
    public Optional<List<TodoModel>> findAllComplete(){
        return repo.findByIsCompleteTrue();
    }

}
