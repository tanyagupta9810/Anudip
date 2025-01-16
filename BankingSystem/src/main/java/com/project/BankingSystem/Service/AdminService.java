package com.project.BankingSystem.Service;

import java.util.List;

import com.project.BankingSystem.model.Admin;

public interface AdminService {
	Admin saveAdmin(Admin a);
	Admin viewAdminById(int id);
	List<Admin> viewAll();
	Admin update(Admin a);
	boolean deleteAdmin(int id);
	Admin getByUsername(String s);
}

