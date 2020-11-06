package com.todo.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.todo.app.dto.Todo;
import com.todo.app.dto.User;
import com.todo.app.service.TodoService;
import com.todo.app.util.UserValidator;

@Controller
public class TodoController {

	@Autowired
	private TodoService todoService;

	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/addTask.app", method = RequestMethod.POST)
	public String addTodo(ModelMap map, @RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "description", required = true) String description,
			@RequestParam(value = "comment", required = true) String comment,
			@RequestParam(value = "targetDate", required = true) String targetDate, HttpSession session

	) {

		Todo todo = new Todo();
		todo.setTitle(title);
		todo.setDescription(description);
		todo.setComment(comment);
		todo.setTargetDate(targetDate);
		User user = (User) session.getAttribute("user");

		todoService.saveTodo(todo, user.getUserId());

		List<Todo> li = todoService.findTodoByUserId(user);
		// map.put("todoList", li);
		session.setAttribute("todoList", li);
		return "dashboard";
	}

	@RequestMapping(value = "/updateTaskForm.app")
	public String renderUpdateTask(ModelMap map, @RequestParam(value = "taskId", required = true) int taskId) {
		List<Todo> list = todoService.findTodoById(taskId);

		map.put("todo", list.get(0));
		return "taskUpdateForm";
	}

	@RequestMapping(value = "/updateTask.app")
	public String updateTask(ModelMap map, Todo todo, HttpSession session) {
		System.out.println("-----" + todo);

		User user = (User) session.getAttribute("user");

		System.out.println("======" + user);
		todo.setUserId(user.getUserId());
		todoService.modifyTodo(todo);

		List<Todo> li = todoService.findTodoByUserId(user);
		session.setAttribute("todoList", li);
		return "dashboard";
	}

	@RequestMapping(value = "/deleteTask.app", method = RequestMethod.GET)
	public String backToDasboard(ModelMap map, @RequestParam(value = "taskId", required = true) int taskId,
			HttpSession session) {

		todoService.removeTodo(taskId);
		User user = (User) session.getAttribute("user");
		List<Todo> li = todoService.findTodoByUserId(user);
		System.out.println("sdsdsd" + li);
		// map.put("todoList", li);
		session.setAttribute("todoList", li);

		return "dashboard";
	}
}
