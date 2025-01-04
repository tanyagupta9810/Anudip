package DAO;

import java.sql.SQLException;
import java.util.List;
import model.Rides;

public interface RidesDAO {
	boolean bookRides(Rides r) throws Exception;
	boolean deleteRides(int id) throws SQLException;
	List<Rides> displayRides()throws SQLException;
	Rides findRidesById(int id) throws SQLException;
//TreeMap<Rides,Driver> displayRideDetail(int id);
	boolean endRides(Rides r) throws SQLException;
	
}
