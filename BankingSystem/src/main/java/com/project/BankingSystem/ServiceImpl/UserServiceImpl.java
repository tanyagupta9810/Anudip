package com.project.BankingSystem.ServiceImpl;

import java.util.List;

import com.project.BankingSystem.DAO.UserDAO;
import com.project.BankingSystem.DAOImpl.UserDAOImpl;
import com.project.BankingSystem.Service.UserService;
import com.project.BankingSystem.model.User;

public class UserServiceImpl implements UserService{

	UserDAO ud=new UserDAOImpl();
	@Override
	public User saveUser(User a) {
				return ud.saveUser(a);
	}

	@Override
	public User viewUserById(int id) {
		return ud.viewUserById(id);
	}

	@Override
	public List<User> viewAllUser() {
		return ud.viewAllUser();
	}

	@Override
	public User update(User a) {
		return ud.update(a);
	}

	@Override
	public boolean deleteUser(int id) {
		return ud.deleteUser(id);
	}

	@Override
	public User getByUsername(String s) {
		return ud.getByUsername(s);
	}

}
