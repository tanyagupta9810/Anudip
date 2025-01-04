package DAOImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import DAO.RidesDAO;
import Utility.StatementProvider;
import model.Driver;
import model.Rides;

public class RidesDAOImpl implements RidesDAO {
	String query;
	PreparedStatement prst;
	ResultSet rs;

	// create
	DriverDAOImpl ddi = new DriverDAOImpl();

	@Override
	public boolean bookRides(Rides r) throws Exception {
		PriorityQueue<Driver> driv = ddi.availableDriver();
		int i = 0;
		if (!driv.isEmpty()) {
			Driver d = driv.poll();
			if (!ddi.updateAvaibility(d.getDriver_id(), "false"))
				throw new Exception("Something went wrong");
			else {
				query = "insert into rides(user_id,driver_id,start_location,destination,status,fare) values(?,?,?,?,?,?)";
				prst = StatementProvider.getStatement(query);
				prst.setInt(1, r.getUser_id());
				prst.setInt(2, d.getDriver_id());
				prst.setString(3, r.getStart_location());
				prst.setString(4, r.getDestination());
				prst.setString(5, r.getStatus());
				prst.setDouble(6, 0);
				i = prst.executeUpdate();

			}
		}
		return i > 0;
	}

	// update
	@Override
	public boolean endRides(Rides re) throws SQLException {
		int i = 0;
		Rides r = findRidesById(re.getRide_id());
		double f = Math.abs(r.getDestination().compareTo(r.getStart_location())) * 50.5;
		query = "update rides set status=?,fare=? where ride_id=?";
		prst = StatementProvider.getStatement(query);
		prst.setString(1, re.getStatus());
		prst.setDouble(2, f);
		prst.setInt(3, r.getRide_id());
		i = prst.executeUpdate();
		ddi.updateAvaibility(r.getDriver_id(), "true");
		return i > 0;

	}

	// retrieve by id
	@Override
	public Rides findRidesById(int id) throws SQLException {
		query = "select * from rides where ride_id=?";
		prst = StatementProvider.getStatement(query);
		prst.setInt(1, id);
		rs = prst.executeQuery();
		if (rs.next())
			return (new Rides(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getDouble(7)));
		return null;
	}

	// retrieve
	@Override
	public List<Rides> displayRides() throws SQLException {
		List<Rides> rides = new ArrayList<Rides>();
		query = "select * from rides";
		Rides r = null;
		prst = StatementProvider.getStatement(query);
		rs = prst.executeQuery();
		while (rs.next()) {
			r = new Rides(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6),
					rs.getDouble(7));
			rides.add(r);
		}
		return rides;
	}

	@Override
	public boolean deleteRides(int id) throws SQLException {
		query = "delete from rides where ride_id=?";
		int i = 0;
		prst = StatementProvider.getStatement(query);
		prst.setInt(1, id);
		i = prst.executeUpdate();
		return i > 0;
	}

//	@Override
//	public TreeMap<Rides, Driver> displayRideDetail(int id) {
//		query="select r.rides_id, d.name,d.vechicle_type,d.vechicle_no from rides r join"
//		return null;
//	}

//	@Override
//	public TreeMap<Rides, com.mysql.cj.jdbc.Driver> displayRideDetail(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public PriorityQueue<Driver> availableDriver() throws SQLException {
//		PriorityQueue<Driver> driver = new PriorityQueue<>((d1, d2) -> d1.getLocation().compareTo(d2.getLocation()));
//		query = "select * from driver where is_available='true' order by location";
//		Driver d = null;
//		prst = StatementProvider.getStatement(query);
//		rs = prst.executeQuery();
//		while (rs.next()) {
//			d = new Driver(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getLong(4), rs.getString(5),
//					rs.getString(6), rs.getDouble(7), rs.getString(8));
//			driver.add(d);
//		}
//		return driver;
//	}
}
