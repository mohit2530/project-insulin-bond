package com.insulinbond.exampleTodo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends MongoRepository<TodoModel, String> {

    public Optional<List<TodoModel>> findByIsCompleteFalse();

    public void deleteById(String id);

    public Optional<List<TodoModel>> findByIsCompleteTrue();
}
