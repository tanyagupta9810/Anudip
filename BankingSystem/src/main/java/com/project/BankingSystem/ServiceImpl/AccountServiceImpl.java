package com.project.BankingSystem.ServiceImpl;

import java.util.List;

import com.project.BankingSystem.Service.AccountService;
import com.project.BankingSystem.model.Account;
import com.project.BankingSystem.model.AccountType;
import com.project.BankingSystem.DAO.AccountDAO;
import com.project.BankingSystem.DAOImpl.AccountDAOImpl;

public class AccountServiceImpl implements AccountService {
	AccountDAO ad=new AccountDAOImpl();

	@Override
	public Account createAccount(Account a) {
		return ad.createAccount(a);
	}

	@Override
	public boolean deleteAccount(Account a) {
		return ad.deleteAccount(a);
	}

	@Override
	public Account viewAccountByNumber(String no) {
		return ad.viewAccountByNumber(no);
	}

	@Override
	public List<Account> viewAllAcount() {
		return ad.viewAllAcount();
	}

	@Override
	public List<Account> viewByType(AccountType a) {
		return ad.viewByType(a);
	}

	@Override
	public double checkBalance(int id) {
		return ad.checkBalance(id);
	}

	@Override
	public List<Account> viewAllAcountByUser(int u_id) {
		return ad.viewAllAcountByUser(u_id);
	}

}
