package com.project.BankingSystem.ServiceImpl;

import java.time.LocalDate;
import java.util.List;

import com.project.BankingSystem.DAO.TransactionsDAO;
import com.project.BankingSystem.DAOImpl.TransactionsDAOImpl;
import com.project.BankingSystem.Service.TransactionService;
import com.project.BankingSystem.model.Transactions;

public class TransactionServiceImpl implements TransactionService {

	TransactionsDAO td = new TransactionsDAOImpl();

	@Override
	public void printLast10Transactions(int a_id) {
		List<Transactions> trans = td.lastFewTransactions(a_id);
		if (trans==null||trans.isEmpty())
			System.out.println("No transactions found for account ID: " + a_id);
		else {
			System.out.println("Last 10 Transactions for Account ID: " + a_id);
			for (Transactions t : trans) {
				System.out.println(t);
			}
		}
	}

	@Override
	public void viewAllByAccount(int a_id) {
		List<Transactions> trans = td.viewAllByAccount(a_id);
		if (trans.isEmpty())
			System.out.println("No transactions found for account ID: " + a_id);
		else {
			System.out.println("Transactions for Account ID: " + a_id);
			for (Transactions t : trans) {
				System.out.println(t);
			}
		}
	}

	@Override
	public void withdrawService(Transactions t) {

		Transactions tc = td.withdraw(t);
		if (tc != null) {
			// System.out.println("Withdrawal of " + t.getAmount()+ " from account " +
			// t.getAccount().t + " was successful.");
			System.out.println("Withdrawal is successfull !!\nTransaction details:-\n" + t);

		}
	}

	@Override
	public void depositService(Transactions t) {
		Transactions tc = td.deposit(t);
		if (tc != null) {
			System.out.println("Deposit successful. New balance: " + t.getAccount().getBalance());
		}
	}

	@Override
	public void diplayTransactionsById(int id) {
		Transactions t = td.viewById(id);
		if (t == null)
			System.out.println("No transactions found with this ID: " + id);
		else
			System.out.println("Transaction :-\n" + t + "\n..................................\n\n");
	}

	@Override
	public void listOfTransaqction() {
		List<Transactions> trans = td.viewAll();
		if (trans.isEmpty())
			System.out.println("No transactions found !!");
		else {
			System.out.println("Transactions are: ");
			for (Transactions t : trans) {
				System.out.println(t);
			}
		}

	}

	@Override
	public void listOfTransactionByStatus(String s) {
		List<Transactions> trans= td.viewAllByStatus(s);
		if(trans.isEmpty())
System.out.println("No "+s+" transactions are found !!");
else
{
	 System.out.println(s+" Transactions are: ");
        for (Transactions t : trans) {
            System.out.println(t);
        }
}		

	}

	@Override
	public void printAccountStatement(int accountId, LocalDate startDate, LocalDate endDate) {
		List<Transactions> trans = td.getAccountStatement(accountId, startDate, endDate);
		System.out.println("Account Statement:");
		System.out.println("Account ID: " + accountId);
		System.out.println("Date Range: " + startDate + " to " + endDate);
//        System.out.println("Current Balance: " + summary.getOpeningBalance());
//        System.out.println("Closing Balance: " + summary.getClosingBalance());
//        System.out.println("Total Deposits: " + summary.getTotalDeposits());
//        System.out.println("Total Withdrawals: " + summary.getTotalWithdrawals());
		System.out.println("\nTransactions:");
		for (Transactions t : trans) {
			System.out.println(t);
		}

	}

}
