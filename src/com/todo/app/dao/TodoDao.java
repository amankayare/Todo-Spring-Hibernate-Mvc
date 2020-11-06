package com.todo.app.dao;

import java.util.List;

import com.todo.app.dto.Todo;
import com.todo.app.dto.User;

public interface TodoDao {

	public int insertTask(Todo todo, int userId);

	public int updateTask(Todo task);

	public int DeleteTask(int taskId);

	public List<Todo> SelectAllTask();

	public List<Todo> SelectTasksByEmail(User user);

	public List<Todo> selectTaskById(int taskId);

	public List<Todo> selectTodoByUserId(User user);

}
