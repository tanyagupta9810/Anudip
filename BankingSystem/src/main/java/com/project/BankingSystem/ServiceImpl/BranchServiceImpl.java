package com.project.BankingSystem.ServiceImpl;

import java.util.List;

import com.project.BankingSystem.DAO.BranchDAO;
import com.project.BankingSystem.DAOImpl.BranchDAOImpl;
import com.project.BankingSystem.Service.BranchService;
import com.project.BankingSystem.model.Branch;

public class BranchServiceImpl  implements BranchService{
	
	BranchDAO bd=new BranchDAOImpl();

	@Override
	public Branch saveBranch(Branch b) {
		return bd.saveBranch(b);
	}

	@Override
	public Branch findBranchById(int id) {
		return bd.findBranchById(id);
	}

	@Override
	public List<Branch> viewAll() {
		return bd.viewAll();
	}

	@Override
	public Branch updateBranch(Branch c) {
		return bd.updateBranch(c);
	}

	@Override
	public boolean deleteBranch(int id) {
		return bd.deleteBranch(id);
	}

	@Override
	public Branch findfBranchByName(String name) {
		return bd.findfBranchByName(name);
	}

}
