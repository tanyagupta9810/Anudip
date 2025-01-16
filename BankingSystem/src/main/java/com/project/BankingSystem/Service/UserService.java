package com.project.BankingSystem.Service;

import java.util.List;

import com.project.BankingSystem.model.User;

public interface UserService {
	User saveUser(User a);
	User viewUserById(int id);
	List<User> viewAllUser();
	User update(User a);
	boolean deleteUser(int id);
	User getByUsername(String s);
}
