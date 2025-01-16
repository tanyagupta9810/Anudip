package com.project.BankingSystem.DAOImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.project.BankingSystem.DAO.UserDAO;
import com.project.BankingSystem.model.Status;
import com.project.BankingSystem.model.User;
import com.project.BankingSystem.utility.HibernateUtil;

public class UserDAOImpl implements UserDAO {
//	Session session = HibernateUtil.getSession();
//	Transaction tx = session.beginTransaction();
	Session session = null;
	Transaction tx = null;

	@Override
	public User saveUser(User a) {
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			session.save(a);
			tx.commit();
		} catch (Exception e) {
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return a;
	}

	@Override
	public User viewUserById(int id) {
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
		User a = session.get(User.class, id);
		session.close();
		if (a == null || a.getStatus().equals(Status.Inactive))
			return null;
		return a;
	}

	@Override
	public List<User> viewAllUser() {
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
		Query<User> q = session.createQuery("From User", User.class);
		List<User> a = q.getResultList();
		session.close();
		return a;
	}

	@Override
	public User update(User a) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		User user = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			user = session.get(User.class, a.getId());
			if (user.getStatus().equals(Status.Inactive))
				return null;
			user.setAddress(a.getAddress());
			user.setContact(a.getContact());
			user.setEmail(a.getEmail());
			user.setName(a.getName());
			user.setPassword(a.getPassword());
			user.setUserName(a.getUserName());
			user.setAccount(a.getAccount());
			session.update(user);
			tx.commit();
		} catch (Exception e) {
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public boolean deleteUser(int id) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		User a;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			a = session.get(User.class, id);
			a.setStatus(Status.Inactive);
			session.update(a);
			tx.commit();
		} catch (Exception e) {
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public User getByUsername(String s) {
		session = HibernateUtil.getSession();
		Query q = session.createQuery("From User where userName=?1");
		q.setParameter(1, s);
		try {
			User a = (User) q.getSingleResult();
			if (a.getStatus().equals(Status.Inactive))
				return null;
			return a;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}

	}

}
