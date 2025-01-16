package com.project.BankingSystem.DAO;

import java.util.List;

import com.project.BankingSystem.model.Account;
import com.project.BankingSystem.model.AccountType;

public interface AccountDAO {
	Account createAccount(Account a);
	boolean deleteAccount(Account a);
	Account viewAccountByNumber(String no);
	List<Account> viewAllAcount();
	List<Account>viewByType(AccountType a);
	double checkBalance(int id);
	List<Account> viewAllAcountByUser(int u_id);
}
