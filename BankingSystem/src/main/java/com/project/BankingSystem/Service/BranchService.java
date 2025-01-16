package com.project.BankingSystem.Service;

import java.util.List;

import com.project.BankingSystem.model.Branch;

public interface BranchService {
	Branch saveBranch(Branch b);
	Branch findBranchById(int id);
	List<Branch> viewAll();
	Branch updateBranch(Branch c);
	boolean deleteBranch(int id);
	Branch findfBranchByName(String name);

}
