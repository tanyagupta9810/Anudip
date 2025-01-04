package DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.PriorityQueue;

import model.Driver;


public interface DriverDAO {
	boolean saveDriver(Driver d) throws SQLException;
	Driver findDriverById(int id) throws SQLException;
	List<Driver> displayDriver() throws SQLException;
	boolean updateDriver(Driver d) throws SQLException;
	boolean deleteDriver(int id) throws SQLException;
	boolean updateAvaibility(int id,String s)throws SQLException;
	PriorityQueue<Driver> availableDriver() throws SQLException;
	boolean updateRating(int id) throws SQLException;
}