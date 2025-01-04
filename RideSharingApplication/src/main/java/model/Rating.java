package model;

public class Rating {

	private int rating_id;
	private int user_id;
	private int driver_id;
	private int ride_id;
	private int rating;
	private String review;
	public Rating() {
		super();
	}
	public Rating(int rating_id, int user_id, int driver_id, int ride_id, int rating, String review) {
		super();
		this.rating_id = rating_id;
		this.user_id = user_id;
		this.driver_id = driver_id;
		this.ride_id = ride_id;
		this.rating = rating;
		this.review = review;
	}
	public int getRating_id() {
		return rating_id;
	}
	public void setRating_id(int rating_id) {
		this.rating_id = rating_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}
	public int getRide_id() {
		return ride_id;
	}
	public void setRide_id(int ride_id) {
		this.ride_id = ride_id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	@Override
	public String toString() {
		return "Rating_id=" + rating_id + "\nUser_id=" + user_id + "\nDriver_id=" + driver_id + "\nRide_id="
				+ ride_id + "\nRating=" + rating + "\nReview=" + review + "\n";
	}
	
	
	
}
