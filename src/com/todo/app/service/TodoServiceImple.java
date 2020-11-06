package com.todo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.app.dao.TodoDao;
import com.todo.app.dto.Todo;
import com.todo.app.dto.User;

@Transactional
@Service
public class TodoServiceImple implements TodoService {

	@Autowired
	private TodoDao todoDao;

	@Override
	public void saveTodo(Todo todo, int userId) {
		System.out.println("Todo Service saveTodo()");
		todoDao.insertTask(todo, userId);
	}

	@Override
	public void modifyTodo(Todo todo) {
		System.out.println("Todo Service modifyTodo()");
		todoDao.updateTask(todo);
	}

	@Override
	public void removeTodo(int taskId) {
		System.out.println("Todo Service removeTodo()");
		todoDao.DeleteTask(taskId);

	}

	@Override
	public List<Todo> findTodoByEmail(User user) {
		System.out.println("Todo Service findTodoByEmail()");

		return todoDao.SelectTasksByEmail(user);
	}

	@Override
	public List<Todo> findAllTodo() {
		System.out.println("Todo Service findAllTodo()");
		return todoDao.SelectAllTask();
	}

	@Override
	public List<Todo> findTodoById(int taskId) {
		return todoDao.selectTaskById(taskId);
	}

	@Override
	public List<Todo> findTodoByUserId(User user) {
		return todoDao.selectTodoByUserId(user);
	}

}
