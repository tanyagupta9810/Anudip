package com.project.BankingSystem.DAO;

import java.time.LocalDate;
import java.util.List;

import com.project.BankingSystem.model.Transactions;

public interface TransactionsDAO {
	List<Transactions> lastFewTransactions(int id);
	List<Transactions> viewAllByAccount(int a_id);
	Transactions withdraw(Transactions t);
	Transactions deposit(Transactions t);
	Transactions viewById(int id);
	List<Transactions> viewAll();
	List<Transactions> viewAllByStatus(String s);
	List<Transactions> getAccountStatement(int accountId, LocalDate startDate, LocalDate endDate);
}
