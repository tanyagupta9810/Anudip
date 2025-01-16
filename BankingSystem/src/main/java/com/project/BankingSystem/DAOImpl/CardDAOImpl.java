package com.project.BankingSystem.DAOImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.BankingSystem.DAO.CardDAO;
import com.project.BankingSystem.model.Account;
import com.project.BankingSystem.model.Card;
import com.project.BankingSystem.model.Status;
import com.project.BankingSystem.utility.HibernateUtil;

import jakarta.persistence.Query;

public class CardDAOImpl implements CardDAO {
	Session session = null;
	Transaction tx = null;

	@Override
	public Card issueCard(Card c) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			session.save(c);
			Account ac = c.getAccount();
			ac.setCard(c);
			session.saveOrUpdate(ac);
			tx.commit();
		} catch (Exception e) {
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return c;
	}

	@Override
	public boolean returnCard(int id) {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Card c = session.get(Card.class, id);
			c.setStatus(Status.Inactive);
			session.update(c);
			tx.commit();
		} catch (Exception e) {
			System.err.println("An error Occured:-" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public Card viewDetailbyNumber(String no) {
		try {
			session = HibernateUtil.getSession();
			Query q = session.createQuery("From Card where no=?1");
			q.setParameter(1, no);
			Card c = (Card) q.getSingleResult();
			if (c.getStatus().equals(Status.Inactive))
				return null;
			return c;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Card> viewAll() {
		session = HibernateUtil.getSession();
		Query q = session.createQuery("From Card");
		List<Card> c = q.getResultList();
		session.close();
		return c;
	}

}
