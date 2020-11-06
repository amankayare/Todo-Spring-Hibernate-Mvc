package com.todo.app.service;

import java.util.List;

import com.todo.app.dto.Todo;
import com.todo.app.dto.User;

public interface TodoService {

	void saveTodo(Todo todo, int userId);

	void modifyTodo(Todo todo);

	void removeTodo(int taskId);

	public List<Todo> findTodoByEmail(User user);

	public List<Todo> findTodoById(int taskId);

	public List<Todo> findTodoByUserId(User user);

	List<Todo> findAllTodo();

}
