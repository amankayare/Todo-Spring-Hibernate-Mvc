package com.todo.app.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todo_list")
public class Todo {

	@Id
	@GeneratedValue
	@Column(name = "task_id")
	private int taskId;

	private String title;

	private String description;

	private String comment;

	@Column(name = "user_id")
	private int userId;

	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "target_date")
	private String targetDate;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

	@Override
	public String toString() {
		return "Todo [taskId=" + taskId + ", title=" + title + ", description=" + description + ", comment=" + comment
				+ ", userId=" + userId + ", targetDate=" + targetDate + "]";
	}

}
