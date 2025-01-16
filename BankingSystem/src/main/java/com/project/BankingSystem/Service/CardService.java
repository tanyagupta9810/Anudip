package com.project.BankingSystem.Service;

import java.util.List;

import com.project.BankingSystem.model.Card;

public interface CardService {
	
	 Card issueCard(Card c);
	 boolean returnCard(int id);
	 Card viewDetailbyNumber(String no);
	 List<Card> viewAll(); 
}
