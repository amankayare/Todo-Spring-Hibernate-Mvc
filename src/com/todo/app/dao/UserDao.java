package com.todo.app.dao;

import java.util.List;

import com.todo.app.dto.User;

public interface UserDao {

	public int insertUser(User user);

	public int updateUser(User user);

	public int DeleteUser(User user);

	public List<User> SelectAllUser();

	public List<User> SelectUserByIdPassword(User user);

	public List<User> SelectUserByEmail(User user);

	public String forgotPassword(String email);

	void uploadImage(String profilePic, int userId);

}
