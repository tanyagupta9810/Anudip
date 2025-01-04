package DAO;

import java.util.List;

import model.Rating;

public interface RatingDAO {

	boolean addRating(Rating r);
	Double averageRatingByDriver(int id);
	Rating findRatingById(int id);
	boolean updateRating(Rating r);
	boolean deleteRating(int id);
	List<Rating> displayRating();
	
}
