package model;

public class Rides {

	private int ride_id;
	private int user_id;
	private int driver_id;
	private String start_location;
	private String destination;
	private String status;
	private double fare;

	public Rides() {
		super();
	}

	public Rides(int ride_id, int user_id, int driver_id, String start_location, String destination, String status,
			double fare) {
		super();
		this.ride_id = ride_id;
		this.user_id = user_id;
		this.driver_id = driver_id;
		this.start_location = start_location;
		this.destination = destination;
		this.status = status;
		this.fare = fare;
	}

	public int getRide_id() {
		return ride_id;
	}

	public void setRide_id(int ride_id) {
		this.ride_id = ride_id;
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

	public String getStart_location() {
		return start_location;
	}

	public void setStart_location(String start_location) {
		this.start_location = start_location;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		return "Ride_id=" + ride_id + "\nUser_id=" + user_id + "\nDriver_id=" + driver_id + "\nStart_location="
				+ start_location + "\nDestination=" + destination + "\nStatus=" + status + "\nFare=" + fare + "\n";
	}

}
