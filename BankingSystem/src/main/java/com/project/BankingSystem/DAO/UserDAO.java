package com.project.BankingSystem.DAO;

import java.util.List;

import com.project.BankingSystem.model.User;

public interface UserDAO {
	User saveUser(User a);
	User viewUserById(int id);
	List<User> viewAllUser();
	User update(User a);
	boolean deleteUser(int id);
	User getByUsername(String s);
}
