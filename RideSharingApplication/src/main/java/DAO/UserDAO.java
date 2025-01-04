package DAO;

import java.util.List;

import model.User;

public interface UserDAO {
boolean saveUser(User u);
User findUserById(int id);
List<User> displayUser();
boolean updateUser(User u);
boolean deleteUser(int id);
}
