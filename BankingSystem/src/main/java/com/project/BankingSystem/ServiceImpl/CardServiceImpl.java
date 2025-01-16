package com.project.BankingSystem.ServiceImpl;

import java.util.List;

import com.project.BankingSystem.Service.CardService;
import com.project.BankingSystem.model.Card;
import com.project.BankingSystem.DAO.CardDAO;
import com.project.BankingSystem.DAOImpl.CardDAOImpl;


public class CardServiceImpl  implements CardService{
	
	CardDAO cd= new CardDAOImpl();

	@Override
	public Card issueCard(Card c) {
		return cd.issueCard(c);
	}

	@Override
	public boolean returnCard(int id) {
		return cd.returnCard(id);
	}

	@Override
	public Card viewDetailbyNumber(String no) {
		return cd.viewDetailbyNumber(no);
	}

	@Override
	public List<Card> viewAll() {
		return cd.viewAll();
	}

}
