package com.project.BankingSystem.DAOImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.project.BankingSystem.DAO.AccountDAO;
import com.project.BankingSystem.model.Account;
import com.project.BankingSystem.model.AccountType;
import com.project.BankingSystem.model.Branch;
import com.project.BankingSystem.model.Status;
import com.project.BankingSystem.model.User;
import com.project.BankingSystem.utility.HibernateUtil;

public class AccountDAOImpl implements AccountDAO {
	Session session = null;
	Transaction tx = null;

	@Override
	public Account createAccount(Account a) {
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			session.save(a);
			Branch c = a.getBranch();
			c.getAccounts().add(a);
			session.saveOrUpdate(c);
			User u = a.getUser();
			u.getAccount().add(a);
			session.saveOrUpdate(u);
			tx.commit();
			return a;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			// e.printStackTrace();
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean deleteAccount(Account a) {
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Account ab = session.get(Account.class, a.getId());
			ab.setStatus(Status.Inactive);
			session.update(ab);
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
	public Account viewAccountByNumber(String no) {
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Query q = session.createQuery("From Account where account_number=?1");
			q.setParameter(1, no);
			Account a = (Account) q.getSingleResult();
			return a;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Account> viewAllAcount() {
		List<Account> a = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Query q = session.createQuery("From Account");
			a = q.getResultList();
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
	public double checkBalance(int id) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Account a = session.get(Account.class, id);
			return a.getBalance();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return 0;
	}

	@Override
	public List<Account> viewByType(AccountType a) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		List<Account> ab = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Query q = session.createQuery("From Account where type=?1");
			q.setParameter(1, a);
			ab = q.getResultList();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return ab;
	}

	@Override
	public List<Account> viewAllAcountByUser(int u_id) {
		List<Account> ab = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Query q = session.createNativeQuery("select * from Account where user_id=?1", Account.class);
			q.setParameter(1, u_id);
			ab = q.getResultList();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return ab;
	}

}
