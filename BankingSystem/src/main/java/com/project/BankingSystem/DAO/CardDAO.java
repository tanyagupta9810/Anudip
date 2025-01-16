package com.project.BankingSystem.DAO;

import java.util.List;

import com.project.BankingSystem.model.Card;

public interface CardDAO {
 Card issueCard(Card c);
 boolean returnCard(int id);
 Card viewDetailbyNumber(String no);
 List<Card> viewAll(); 
}
