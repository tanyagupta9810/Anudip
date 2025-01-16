package com.project.BankingSystem.DAOImpl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.BankingSystem.DAO.TransactionsDAO;
import com.project.BankingSystem.model.Account;
import com.project.BankingSystem.model.Admin;
import com.project.BankingSystem.model.Status;
import com.project.BankingSystem.model.Transactions;
import com.project.BankingSystem.utility.HibernateUtil;

import jakarta.persistence.Query;

public class TransactionsDAOImpl implements TransactionsDAO {
	Session session = null;
	Transaction tx = null;

	@Override
	public List<Transactions> lastFewTransactions(int a_id) {
//		Session session = HibernateUtil.getSession();
		try {
			session = HibernateUtil.getSession();
			Query q = session.createNativeQuery(
					"Select * From Transaction where account_id=?1 order by date desc limit 10", Transactions.class);
			q.setParameter(1, a_id);
			List<Transactions> t = q.getResultList();
			return t;
		} catch (Exception e) {
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Transactions> viewAllByAccount(int a_id) {
		// Session session = HibernateUtil.getSession();
		try {
			session = HibernateUtil.getSession();
			Query q = session.createNativeQuery("Select * From Transaction where account_id=?1", Transactions.class);
			q.setParameter(1, a_id);
			List<Transactions> t = q.getResultList();
			return t;
		} catch (Exception e) {
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Transactions withdraw(Transactions t) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
		try {
			Account a = t.getAccount();
			if (a.getBalance() >= t.getAmount()) {
				a.setBalance(a.getBalance() - t.getAmount());
				t.setT_status("Success");
				t.setDate(LocalDate.now());
				t.setType("Withdrawal");
				session.update(a);
				session.save(t);
				tx.commit();
				return t;
			} else
				throw new Exception();
		} catch (Exception e) {
			if (tx != null) {
				t.setDate(LocalDate.now());
				t.setType("Withdrawal");
				t.setT_status("Failed");
				session.save(t);
				tx.commit();
				System.err.println("Withdraw Failed:\nInsufficient balance for withdrawal. Available balance:"
						+ t.getAccount().getBalance());
			} else
				System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Transactions deposit(Transactions t) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Account a = t.getAccount();
			a.setBalance(a.getBalance() + t.getAmount());
			t.setT_status("Success");
			t.setDate(LocalDate.now());
			t.setType("Deposit");
			session.update(a);
			session.save(t);
			tx.commit();
			return t;
		} catch (Exception e) {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			if (tx != null) {
				// tx.rollback();
				t.setDate(LocalDate.now());
				t.setType("Deposit");
				t.setT_status("Failed");
				session.save(t);
				tx.commit();
				System.err.println("Deposit Failed: " + e.getMessage());
			} else
				System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Transactions viewById(int id) {
		session = HibernateUtil.getSession();
		Transactions a = session.get(Transactions.class, id);
		session.close();
		if (a == null || a.getStatus().equals(Status.Inactive))
			return null;
		return a;
	}

	@Override
	public List<Transactions> viewAll() {
		// Session session = HibernateUtil.getSession();
		try {
			session = HibernateUtil.getSession();
			Query q = session.createQuery("From Transactions");
			List<Transactions> t = q.getResultList();
			return t;
		} catch (Exception e) {
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Transactions> viewAllByStatus(String s) {
//		Session session = HibernateUtil.getSession();
		try {
			session = HibernateUtil.getSession();
			Query q = session.createQuery("From Transactions where t_status=?1");
			q.setParameter(1, s);
			List<Transactions> t = q.getResultList();
			return t;
		} catch (Exception e) {
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Transactions> getAccountStatement(int accountId, LocalDate startDate, LocalDate endDate) {
		session = HibernateUtil.getSession();
		Query q = session.createNativeQuery(
				"Select * From Transaction where account_id=?1 and date>=?2 and date<=?3 order by date",
				Transactions.class);
		q.setParameter(1, accountId);
		q.setParameter(2, startDate);
		q.setParameter(3, endDate);
		List<Transactions> t = q.getResultList();
		session.close();
		return t;
	}

}
