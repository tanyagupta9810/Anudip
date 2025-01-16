package com.project.BankingSystem.DAOImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.BankingSystem.DAO.BranchDAO;
import com.project.BankingSystem.model.Branch;
import com.project.BankingSystem.model.Status;
import com.project.BankingSystem.utility.HibernateUtil;

import jakarta.persistence.Query;

public class BranchDAOImpl implements BranchDAO {
//	Session session = HibernateUtil.getSession();
//	Transaction tx = session.beginTransaction();
	Session session;
	Transaction tx;

	@Override
	public Branch saveBranch(Branch b) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			session.save(b);
			tx.commit();
			return b;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Branch findBranchById(int id) {
		session = HibernateUtil.getSession();
		Branch b = session.get(Branch.class, id);
		session.close();
		if (b == null || b.getStatus().equals(Status.Inactive))
			return null;
		return b;
	}

	@Override
	public List<Branch> viewAll() {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		List<Branch> b = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Query q = session.createQuery("From Branch", Branch.class);
			b = q.getResultList();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return b;
	}

	@Override
	public Branch updateBranch(Branch c) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		Branch b = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			b = session.get(Branch.class, c.getId());
			b.setAddress(c.getAddress());
			b.setName(c.getName());
			b.setManager(c.getManager());
			session.update(b);
			tx.commit();
			return b;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean deleteBranch(int id) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Branch b = session.get(Branch.class, id);
			b.setStatus(Status.Inactive);
			session.update(b);
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public Branch findfBranchByName(String name) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		Branch b = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Query q = session.createQuery("From Branch where name=?1");
			q.setParameter(1, name);
			b = (Branch) q.getResultList();
			return b;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

}
