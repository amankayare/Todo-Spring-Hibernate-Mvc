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

import com.todo.app.dto.Todo;
import com.todo.app.dto.User;

@Repository
public class TodoDaoImple implements TodoDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public int insertTask(Todo todo, int userId) {
		hibernateTemplate.execute(new HibernateCallback<Void>() {

			@Override
			public Void doInHibernate(Session session) throws HibernateException {

				System.out.println("TodoDao insert()" + todo + "  " + userId);
				Transaction transaction = session.beginTransaction();
				Query query = session.createSQLQuery(
						"insert into todo_list(title, description,comment,user_id,target_date) values(?,?,?,?,?)");
				query.setString(0, todo.getTitle());
				query.setString(1, todo.getDescription());
				query.setString(2, todo.getComment());
				query.setInteger(3, userId);
				query.setString(4, todo.getTargetDate());

				query.executeUpdate();
				transaction.commit();
				System.out.println("task inserted successfuly!!");
				session.flush();
				session.close();
				return null;
			}
		});
		return 0;
	}

	@Override
	public int updateTask(Todo todo) {
		hibernateTemplate.execute(new HibernateCallback<Void>() {

			@Override
			public Void doInHibernate(Session session) throws HibernateException {

				Transaction transaction = session.beginTransaction();
				session.update(todo);
				transaction.commit();
				session.flush();
				session.close();
				return null;
			}
		});
		return 0;
	}

	@Override
	public int DeleteTask(int taskId) {
		hibernateTemplate.execute(new HibernateCallback<Void>() {

			@Override
			public Void doInHibernate(Session session) throws HibernateException {

				Transaction transaction = session.beginTransaction();
				Query query = session.createQuery("delete from Todo where taskId= ?");
				query.setInteger(0, taskId);
				query.executeUpdate();
				transaction.commit();
				session.flush();
				session.close();
				return null;
			}
		});
		return 0;
	}

	@Override
	public List<Todo> SelectAllTask() {
		return hibernateTemplate.execute(new HibernateCallback<List<Todo>>() {

			@Override
			public List<Todo> doInHibernate(Session session) throws HibernateException {

				Transaction transaction = session.beginTransaction();
				Query query = session.createQuery("from User");
				List<Todo> custList = query.list();
				transaction.commit();
				session.flush();
				session.close();
				return custList;
			}
		});
	}

	@Override
	public List<Todo> SelectTasksByEmail(User user) {
		return hibernateTemplate.execute(new HibernateCallback<List<Todo>>() {

			@Override
			public List<Todo> doInHibernate(Session session) throws HibernateException {

				Transaction transaction = session.beginTransaction();
				Query query = session.createQuery("From Todo where userEmail=?");
				query.setString(0, user.getUserEmail());
				List<Todo> list = query.list();
				System.out.println("Todo DAO RESULT:" + list);

				transaction.commit();
				session.flush();
				session.close();
				return list;
			}
		});
	}

	@Override
	public List<Todo> selectTaskById(int taskId) {
		return hibernateTemplate.execute(new HibernateCallback<List<Todo>>() {

			@Override
			public List<Todo> doInHibernate(Session session) throws HibernateException {

				Transaction transaction = session.beginTransaction();
				Query query = session.createQuery("From Todo where taskId=?");
				query.setInteger(0, taskId);
				List<Todo> list = query.list();
				System.out.println("Todo DAO RESULT:" + list);

				transaction.commit();
				session.flush();
				session.close();
				return list;
			}
		});
	}

	@Override
	public List<Todo> selectTodoByUserId(User user) {

		return hibernateTemplate.execute(new HibernateCallback<List<Todo>>() {

			@Override
			public List<Todo> doInHibernate(Session session) throws HibernateException {

				Transaction transaction = session.beginTransaction();
				Query query = session.createQuery("From Todo where userId=?");
				query.setInteger(0, user.getUserId());
				List<Todo> list = query.list();
				System.out.println("Todo DAO RESULT:" + list);

				transaction.commit();
				session.flush();
				session.close();
				return list;
			}
		});
	}

}
