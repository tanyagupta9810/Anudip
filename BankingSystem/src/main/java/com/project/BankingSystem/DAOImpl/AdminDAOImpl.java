package com.project.BankingSystem.DAOImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.project.BankingSystem.DAO.AdminDAO;
import com.project.BankingSystem.model.Admin;
import com.project.BankingSystem.model.Status;
import com.project.BankingSystem.utility.HibernateUtil;

public class AdminDAOImpl implements AdminDAO {
	Session session = null;
	Transaction tx = null;

	@Override
	public Admin saveAdmin(Admin a) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			session.save(a);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return a;
	}

	@Override
	public Admin viewAdminById(int id) {
		session = HibernateUtil.getSession();
		Admin a = session.get(Admin.class, id);
		session.close();
		if (a == null || a.getStatus().equals(Status.Inactive))
			return null;
		return a;
	}

	@Override
	public List<Admin> viewAll() {
		session = HibernateUtil.getSession();
		Query q = session.createNativeQuery("Select * From Admin where DTYPE=?1", Admin.class);
		q.setParameter(1, "Admin");
		List<Admin> a = q.getResultList();
		session.close();
		return a;
	}

	@Override
	public Admin update(Admin a) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		Admin admin = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			admin = session.get(Admin.class, a.getId());
			if (admin.getStatus().equals(Status.Inactive))
				return null;
			admin.setAddress(a.getAddress());
			admin.setContact(a.getContact());
			admin.setEmail(a.getEmail());
			admin.setName(a.getName());
			admin.setPassword(a.getPassword());
			admin.setUserName(a.getUserName());
			session.update(admin);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				// tx.rollback();
				System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return admin;
	}

	@Override
	public boolean deleteAdmin(int id) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		Admin a;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			a = session.get(Admin.class, id);
			a.setStatus(Status.Inactive);
			session.update(a);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public Admin getByUsername(String s) {
		try {
			session = HibernateUtil.getSession();
			Query q = session.createQuery("From Admin where userName=?1");
			q.setParameter(1, s);
			Admin a = (Admin) q.getSingleResult();
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
