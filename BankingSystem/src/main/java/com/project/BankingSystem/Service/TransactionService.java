package com.project.BankingSystem.Service;

import java.time.LocalDate;
import com.project.BankingSystem.model.Transactions;

public interface TransactionService {
	void  printLast10Transactions(int a_id);
	void viewAllByAccount(int a_id);
	void withdrawService(Transactions t);
	void depositService(Transactions t);
	void diplayTransactionsById(int id);
	void listOfTransaqction();
	void listOfTransactionByStatus(String s);
	void printAccountStatement(int accountId, LocalDate startDate, LocalDate endDate);
}
