package model;

public class Driver {
	
	private int driver_id;
	private String name;
	private String location;
	private long contact;
	private String vechicle_type;
	private String vechicle_no;
	private double rating;
	private String is_available;

// no args constructor
	public Driver() {
		super();
	}

// parametrized constructor
	public Driver(int driver_id, String name, String location, long contact, String vechicle_type, String vechicle_no,
			double rating, String is_available) {
		super();
		this.driver_id = driver_id;
		this.name = name;
		this.location = location;
		this.contact = contact;
		this.vechicle_type = vechicle_type;
		this.vechicle_no = vechicle_no;
		this.rating = rating;
		this.is_available = is_available;
	}

//getter and setter methods

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getVechicle_type() {
		return vechicle_type;
	}

	public void setVechicle_type(String vechicle_type) {
		this.vechicle_type = vechicle_type;
	}

	public String getVechicle_no() {
		return vechicle_no;
	}

	public void setVechicle_no(String vechicle_no) {
		this.vechicle_no = vechicle_no;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getIs_available() {
		return is_available;
	}

	public void setIs_available(String is_available) {
		this.is_available = is_available;
	}

// to string function to represent driver object in string
	@Override
	public String toString() {
		return "Driver_id=" + driver_id + "\nName=" + name + "\nLocation=" + location + "\nContact=" + contact
				+ "\nVechicle_type=" + vechicle_type + "\nVechicle_no=" + vechicle_no + "\nRating=" + rating
				+ "\nIs_available=" + is_available + "\n";
	}

}
