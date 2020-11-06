package com.todo.app.service;

import java.util.List;

import com.todo.app.dto.User;

public interface UserService {

	void saveUser(User user);

	void modifyUser(User user);

	void removeUser(User user);

	boolean loginUser(User user);

	public List<User> findUserByEmail(User user);

	List<User> findAllUser();

	public String forgotPassword(String email);

	void uploadImage(String profilePic, int userId);
}
