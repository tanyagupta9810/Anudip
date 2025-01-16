package com.project.BankingSystem.ServiceImpl;

import java.util.List;
import com.project.BankingSystem.DAOImpl.AdminDAOImpl;
import com.project.BankingSystem.DAO.AdminDAO;
import com.project.BankingSystem.Service.AdminService;
import com.project.BankingSystem.model.Admin;

public class AdminServiceImpl implements AdminService{
	AdminDAO ad=new AdminDAOImpl();
	@Override
	public Admin saveAdmin(Admin a) {	
		return ad.saveAdmin(a);
	}

	@Override
	public Admin viewAdminById(int id) {
		return ad.viewAdminById(id);
	}

	@Override
	public List<Admin> viewAll() {
		return ad.viewAll();
	}

	@Override
	public Admin update(Admin a) {
		return ad.update(a);
	}

	@Override
	public boolean deleteAdmin(int id) {
		return ad.deleteAdmin(id);
	}

	@Override
	public Admin getByUsername(String s) {
		return ad.getByUsername(s);
	}

}
