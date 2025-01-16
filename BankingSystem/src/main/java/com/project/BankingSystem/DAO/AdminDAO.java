package com.project.BankingSystem.DAO;

import java.util.List;

import com.project.BankingSystem.model.Admin;

public interface AdminDAO {
	Admin saveAdmin(Admin a);
	Admin viewAdminById(int id);
	List<Admin> viewAll();
	Admin update(Admin a);
	boolean deleteAdmin(int id);
	Admin getByUsername(String s);
}
