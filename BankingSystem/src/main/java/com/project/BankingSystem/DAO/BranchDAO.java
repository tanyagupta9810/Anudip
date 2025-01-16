package com.project.BankingSystem.DAO;

import java.util.List;

import com.project.BankingSystem.model.Branch;

public interface BranchDAO {
	Branch saveBranch(Branch b);
	Branch findBranchById(int id);
	List<Branch> viewAll();
	Branch updateBranch(Branch c);
	boolean deleteBranch(int id);
	Branch findfBranchByName(String name);
}
