package DAOImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.UserDAO;
import Utility.StatementProvider;
import model.User;

public class UserDAOImpl implements UserDAO {

	String query;
	PreparedStatement prst;
	ResultSet rs;
	@Override
	public boolean saveUser(User u) {
		query = "insert into user(name,email,contact) values(?,?,?)";

		int i = 0;
		try {
			prst = StatementProvider.getStatement(query);
			prst.setString(1, u.getName());
			prst.setString(2, u.getEmail());
			prst.setLong(3, u.getContact());
			i = prst.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return i > 0;
	}

	@Override
	public User findUserById(int user_id) {
		query = "select * from user where user_id =?";
		try {
			prst = StatementProvider.getStatement(query);
			prst.setInt(1, user_id);
			rs = prst.executeQuery();
			if (rs.next()) {
				return (new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getLong(4)));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<User> displayUser() {
		List<User> user = new ArrayList<User>();
		User ab = null;
		query = "select * from user";
		//query = qu.retQuery("user");
		try {
			prst = StatementProvider.getStatement(query);
			rs = prst.executeQuery();
			while (rs.next()) {
				ab = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getLong(4));
				user.add(ab);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return user;
	}

	@Override
	public boolean updateUser(User u) {
		query = "update user set name=?,email=?,contact=? where user_id=?";
		int i = 0;
		try {
			prst = StatementProvider.getStatement(query);
			prst.setInt(4, u.getUser_id());
			prst.setString(1, u.getName());
			prst.setString(2, u.getEmail());
			prst.setLong(3, u.getContact());
			i = prst.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return i > 0;
	}

	public boolean deleteUser(int user_id) {
		query = "DELETE FROM user WHERE user_id = ?";
		int i = 0;
		try {
			prst = StatementProvider.getStatement(query);
			prst.setInt(1, user_id);
			i = prst.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return i > 0;
	}

}
