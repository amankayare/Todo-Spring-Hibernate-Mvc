package com.todo.app.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.todo.app.dto.User;

@Repository
public class UserDaoImple implements UserDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public int insertUser(User user) {

		hibernateTemplate.execute(new HibernateCallback<Void>() {

			@Override
			public Void doInHibernate(Session session) throws HibernateException {

				System.out.println("UserDao insert()" + user);
				Transaction transaction = session.beginTransaction();
				session.save(user);
				transaction.commit();
				session.flush();
				session.close();
				return null;
			}
		});

		return 0;
	}

	@Override
	public int updateUser(User user) {
		hibernateTemplate.execute(new HibernateCallback<Void>() {

			@Override
			public Void doInHibernate(Session session) throws HibernateException {

				Transaction transaction = session.beginTransaction();
				session.update(user);
				transaction.commit();
				session.flush();
				session.close();
				return null;
			}
		});
		return 0;
	}

	@Override
	public int DeleteUser(User user) {
		hibernateTemplate.execute(new HibernateCallback<Void>() {

			@Override
			public Void doInHibernate(Session session) throws HibernateException {

				Transaction transaction = session.beginTransaction();
				session.delete(user);
				transaction.commit();
				session.flush();
				session.close();
				return null;
			}
		});
		return 0;
	}

	@Override
	public List<User> SelectAllUser() {
		return hibernateTemplate.execute(new HibernateCallback<List<User>>() {

			@Override
			public List<User> doInHibernate(Session session) throws HibernateException {

				Transaction transaction = session.beginTransaction();
				Query query = session.createQuery("from User");
				List<User> custList = query.list();
				transaction.commit();
				session.flush();
				session.close();
				return custList;
			}
		});
	}

	@Override
	public List<User> SelectUserByIdPassword(User user) {
		return hibernateTemplate.execute(new HibernateCallback<List<User>>() {

			@Override
			public List<User> doInHibernate(Session session) throws HibernateException {

				Transaction transaction = session.beginTransaction();
				Query query = session.createQuery("From User where userEmail=? and userPassword=?");
				query.setString(0, user.getUserEmail());
				query.setString(1, user.getUserPassword());
				List<User> list = query.list();
				System.out.println("DAO RESULT:" + list);

				transaction.commit();
				session.flush();
				session.close();
				return list;
			}
		});
	}

	@Override
	public List<User> SelectUserByEmail(User user) {
		return hibernateTemplate.execute(new HibernateCallback<List<User>>() {

			@Override
			public List<User> doInHibernate(Session session) throws HibernateException {

				Transaction transaction = session.beginTransaction();
				Query query = session.createQuery("From User where userEmail=?");
				query.setString(0, user.getUserEmail());
				List<User> list = query.list();
				System.out.println("DAO RESULT:" + list);

				transaction.commit();
				session.flush();
				session.close();
				return list;
			}
		});
	}

	@Override
	public String forgotPassword(String email) {
		return hibernateTemplate.execute(new HibernateCallback<String>() {

			@Override
			public String doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("from User where userEmail = ?");
				q.setString(0, email);
				List<User> li = q.list();
				String pass = null;
				if (!li.isEmpty())
					pass = li.get(0).getUserPassword();
				tr.commit();
				session.flush();
				session.close();
				System.out.println("PASS:" + pass);
				return pass;
			}

		});
	}

	@Override
	public void uploadImage(String profilePic, int userId) {
		hibernateTemplate.execute(new HibernateCallback<Void>() {

			@Override
			public Void doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				User user = (User) session.get(User.class, userId);
				user.setProfilePic(profilePic);
				tr.commit();
				session.flush();
				session.close();
				return null;
			}

		});
	}

}
