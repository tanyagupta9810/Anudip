package DAOImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.RatingDAO;
import Utility.StatementProvider;
import model.Rating;

public class RatingDAOImpl implements RatingDAO {
	String query;
	PreparedStatement prst;
	ResultSet rs;

	@Override
	public boolean addRating(Rating r) {
		query = "insert into rating (user_id,driver_id,ride_id,rating,review)value(?,?,?,?,?)";
		int i = 0;
		try {
			prst = StatementProvider.getStatement(query);
			prst.setInt(1, r.getUser_id());
			prst.setInt(2, r.getDriver_id());
			prst.setInt(3, r.getRide_id());
			prst.setInt(4, r.getRating());
			prst.setString(5, r.getReview());
			i = prst.executeUpdate();
			DriverDAOImpl ddi = new DriverDAOImpl();
			ddi.updateRating(r.getDriver_id());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return i > 0;
	}

	@Override
	public boolean updateRating(Rating r) {
		query = "update rating set rating=?,review=? where rating_id=?";
		int i = 0;
		try {
			prst = StatementProvider.getStatement(query);
			prst.setInt(1, r.getRating());
			prst.setString(2, r.getReview());
			i = prst.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return i > 0;
	}

	@Override
	public boolean deleteRating(int id) {
		query = "delete from rating where rating_id=?";
		int i = 0;
		try {
			prst = StatementProvider.getStatement(query);
			prst.setInt(1, id);
			i = prst.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return i > 0;
	}

	@Override
	public List<Rating> displayRating() {
		List<Rating> rating = new ArrayList<Rating>();
		query = "select * from rating";
		Rating r = null;
		try {
			prst = StatementProvider.getStatement(query);
			rs = prst.executeQuery();
			while (rs.next()) {
				r = new Rating(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
				rating.add(r);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rating;
	}

	@Override
	public Double averageRatingByDriver(int id) {
		query = "select avg(rating) from rating where driver_id=?";
		double avg = 0.0;
		try {
			prst = StatementProvider.getStatement(query);
			prst.setInt(1, id);
			rs = prst.executeQuery();
			if (rs.next())
				avg = rs.getDouble(1);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return avg;
	}

	@Override
	public Rating findRatingById(int id) {
		query = "select * from rating where rating_id=?";
		try {
			prst = StatementProvider.getStatement(query);
			prst.setInt(1, id);
			rs = prst.executeQuery();
			if (rs.next())
				return (new Rating(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6)));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

}
