package com.todo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.app.dao.UserDao;
import com.todo.app.dto.User;

@Transactional
@Service
public class UserServiceImple implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void saveUser(User user) {
		System.out.println("User service saveUser()");
		userDao.insertUser(user);
	}

	@Override
	public void modifyUser(User user) {
		userDao.updateUser(user);

	}

	@Override
	public void removeUser(User user) {
		userDao.DeleteUser(user);
	}

	@Override
	public boolean loginUser(User user) {
		System.out.println("user service");

		List<User> list = userDao.SelectUserByIdPassword(user);
		System.out.println(list);

		if (list.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public List<User> findUserByEmail(User user) {
		return userDao.SelectUserByEmail(user);
	}

	@Override
	public List<User> findAllUser() {
		return userDao.SelectAllUser();
	}

	@Override
	public String forgotPassword(String email) {
		System.out.println("forget service");
		return userDao.forgotPassword(email);
	}

	@Override
	public void uploadImage(String profilePic, int userId) {
		userDao.uploadImage(profilePic, userId);
	}

}
