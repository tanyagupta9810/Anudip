package DAOImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import DAO.DriverDAO;
import Utility.StatementProvider;
import model.Driver;

public class DriverDAOImpl implements DriverDAO {

	String query;
	PreparedStatement prst;
	ResultSet rs;
	
	@Override
	public boolean saveDriver(Driver d) throws SQLException {
		query="insert into driver (name, location,contact,vechicle_type,vechicle_no) values(?,?,?,?,?)";
		int i = 0;
			prst = StatementProvider.getStatement(query);
			prst.setString(1, d.getName());
			prst.setString(2, d.getLocation());
			prst.setLong(3, d.getContact());
			prst.setString(4, d.getVechicle_type());
			prst.setString(5, d.getVechicle_no());
			i = prst.executeUpdate();
		return i > 0;
	}
	
	@Override
	public Driver findDriverById(int id) throws SQLException {
		query="select * from driver where driver_id=?";
		prst=StatementProvider.getStatement(query);
		prst.setInt(1, id);
		rs=prst.executeQuery();
		if(rs.next())
			return (new Driver(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getString(5),rs.getString(6),rs.getDouble(7),rs.getString(8)));
		return null;
	}
	
	@Override
	public List<Driver> displayDriver() throws SQLException {
		List<Driver> driver= new ArrayList<Driver>();
		query="select * from driver";
		Driver d=null;
		prst=StatementProvider.getStatement(query);
		rs=prst.executeQuery();
		while(rs.next())
		{
			d=new Driver(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getString(5),rs.getString(6),rs.getDouble(7),rs.getString(8));
			driver.add(d);
		}
		return driver;
	}
	
	@Override
	public boolean updateDriver(Driver d) throws SQLException {
		query = "update driver set name=?,location=?,contact=?,vechicle_type=?,vechicle_no=? where driver_id=?";
		int i = 0;
		prst = StatementProvider.getStatement(query);
		prst.setString(1, d.getName());
		prst.setString(2, d.getLocation());
		prst.setLong(3, d.getContact());
		prst.setString(4, d.getVechicle_type());
		prst.setString(5, d.getVechicle_no());
		prst.setInt(6,d.getDriver_id());
			i = prst.executeUpdate();
		return i > 0;
	}
	@Override
	public boolean deleteDriver(int id) throws SQLException {
		query="delete from driver where driver_id=?";
		int i=0;
		prst=StatementProvider.getStatement(query);
		prst.setInt(1, id);
		i=prst.executeUpdate();
		return i>0;
	}

	@Override
	public boolean updateAvaibility(int id,String s) throws SQLException {
		query = "update driver set is_available=? where driver_id=?";
		int i = 0;
		prst = StatementProvider.getStatement(query);
		prst.setString(1, s);
		prst.setInt(2,id);
			i = prst.executeUpdate();
		return i > 0;
	}
	
	@Override
	public PriorityQueue<Driver> availableDriver() throws SQLException {
		PriorityQueue<Driver> driver= new PriorityQueue<>((d1,d2)->d1.getLocation().compareTo(d2.getLocation()));
		query="select * from driver where is_available='true' order by location";
		Driver d=null;
		prst=StatementProvider.getStatement(query);
		rs=prst.executeQuery();
		while(rs.next())
		{
			d=new Driver(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getString(5),rs.getString(6),rs.getDouble(7),rs.getString(8));
			driver.add(d);
		}
		return driver;
	}
	@Override
	public boolean updateRating(int id) throws SQLException {
		RatingDAOImpl rdi=new RatingDAOImpl();
		double avg=rdi.averageRatingByDriver(id);
		query = "update driver set rating=? where driver_id=?";
		int i = 0;
		prst = StatementProvider.getStatement(query);
		prst.setDouble(1,avg);
		prst.setInt(2, id);
			i = prst.executeUpdate();
		return i > 0;
	}
	
}
