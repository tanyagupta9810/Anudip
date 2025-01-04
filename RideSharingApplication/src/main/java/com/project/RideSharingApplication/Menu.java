package com.project.RideSharingApplication;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import DAOImpl.*;
import model.*;

public class Menu {
	Scanner sc = new Scanner(System.in);

	public void displayMenu() {
		char sai = 'y';
		do {
			try {
				System.out.println("--------MAIN MENU--------");
				System.out.println("1. User");
				System.out.println("2. Driver");
				System.out.println("3. Rides");
				System.out.println("4. Review");
				System.out.println("9. Exit");
				System.out.println("-------------------------");
				System.out.println("Chose a option");
				int ch = sc.nextInt();
				switch (ch) {
				case 1:
					userMenu();
					break;
				case 2:
					driverMenu();
					break;
				case 3:
					ridesMenu();
					break;
				case 4:
					ratingMenu();
					break;
				case 9:
					System.out.println("\nClosing Application!!.");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid input, try again!!");
				}
			} catch (InputMismatchException e) {
				System.err.println("Please enter a valid numeric option.");
				sc.nextLine(); // Clear the invalid input
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
			System.out.println("Do you want to continue(y/n):-");
			sai = sc.next().charAt(0);
		} while (sai == 'y' || sai == 'Y');
	}

	public void userMenu() {

		char sa = 'y';
		do {
			try {
				UserDAOImpl udi = new UserDAOImpl();
				System.out.println("----------USER MENU----------");
				System.out.println("1. Register user");
				System.out.println("2. Display user");
				System.out.println("3. Search user");
				System.out.println("4. Update user");
				System.out.println("5. Delete user");
				System.out.println("8. Return to main menu");
				System.out.println("9. Exit");
				System.out.println("-----------------------------");
				System.out.println("Chose a option");
				int ch = sc.nextInt();
				switch (ch) {
				case 1: {
					System.out.println("Enter the required details for user creation:-");
					sc.nextLine();
					System.out.println("Enter name:");
					String name = sc.nextLine();
					System.out.println("Enter email:");
					String email = sc.nextLine();
					System.out.println("Enter contact:");
					long contact = sc.nextLong();
					User s = new User(0, name, email, contact);
					if (udi.saveUser(s))
						System.out.println("User created successfully!!");
					else
						System.out.println("User cannot be created,try again!");
					break;
				}
				case 2: {
					List<User> user = udi.displayUser();
					if (user.isEmpty())
						System.out.println("No user found");
					else {
						System.out.println("Users are:-");
						for (User a : user)
							System.out.println(a);
						System.out.println("..................................\n\n");
					}
					break;
				}
				case 3: {
					System.out.println("Enter user_id:");
					int id = sc.nextInt();
					User ab = udi.findUserById(id);
					if (ab != null) {
						System.out.println("User:-");
						System.out.println(ab);
						System.out.println("..................................\n\n");
					} else
						System.out.println("User not found!");
					break;
				}
				case 4: {
					System.out.println("Enter user_id whose detail need to be updated:");
					int user_ids = sc.nextInt();
					if (udi.findUserById(user_ids) == null)
						System.out.println("User with this Id does not exist");
					else {
						System.out.println("Enter the required details:-");
						sc.nextLine();
						System.out.println("Enter name:");
						String name = sc.nextLine();
						System.out.println("Enter email:");
						String email = sc.nextLine();
						System.out.println("Enter contact:");
						long contact = sc.nextLong();
						User s = new User(user_ids, name, email, contact);
						if (udi.updateUser(s))
							System.out.println("User updated succesfully");
					}
					break;
				}
				case 5:
					System.out.println("Enter user_id:");
					int ids = sc.nextInt();
					if (udi.findUserById(ids) == null)
						System.out.println("Invalid User..");
					else {
						if (udi.deleteUser(ids))
							System.out.println("User deleted succesfully!!");
						else
							System.out.println("Something went wrong");
					}
					break;
				case 8:
					displayMenu();
					break;
				case 9:
					System.out.println("\nClosing Application!!.");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid input, try again!!");
				}
			} catch (InputMismatchException e) {
				System.err.println("Please enter a valid numeric option.");
				sc.nextLine(); // Clear the invalid input
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
			System.out.println("Do you want to continue(y/n):-");
			sa = sc.next().charAt(0);
		} while (sa == 'y' || sa == 'Y');
	}

	public void driverMenu() {

		char sa = 'y';
		do {
			try {
				DriverDAOImpl ddi = new DriverDAOImpl();
				System.out.println("----------Driver MENU----------");
				System.out.println("1. Create driver");
				System.out.println("2. View All driver");
				System.out.println("3. Search driver");
				System.out.println("4. Update driver");
				System.out.println("5. Delete driver");
				System.out.println("6. View Available driver");
				System.out.println("8. Return to main menu");
				System.out.println("9. Exit");
				System.out.println("-------------------------------");
				System.out.println("Chose a option");
				int ch = sc.nextInt();
				// try {
				switch (ch) {
				case 1: {
					System.out.println("Enter the required details for driver creation:-");
					sc.nextLine();
					System.out.println("Enter name:");
					String name = sc.nextLine();
					System.out.println("Enter location:");
					String location = sc.nextLine();
					System.out.println("Enter contact:");
					long contact = sc.nextLong();
					sc.nextLine();
					System.out.println("Enter vechicle_type:");
					String v_type = sc.nextLine();
					System.out.println("Enter vechicle_no:");
					String v_no = sc.nextLine();
					Driver d = new Driver(0, name, location, contact, v_type, v_no, 0, "true");
					if (ddi.saveDriver(d))
						System.out.println("Driver created successfully!!");
					else
						System.out.println("Driver cannot be created,try again!");
					break;
				}
				case 2: {
					List<Driver> driver = ddi.displayDriver();
					if (driver.isEmpty())
						System.out.println("No driver found");
					else {
						System.out.println("Drivers are:-");
						for (Driver a : driver)
							System.out.println(a);
						System.out.println("..................................\n\n");
					}
					break;
				}
				case 3: {
					System.out.println("Enter driver_id:");
					int id = sc.nextInt();
					Driver ab = ddi.findDriverById(id);
					if (ab != null) {
						System.out.println("Driver:-");
						System.out.println(ab);
						System.out.println("..................................\n\n");
					} else
						System.out.println("Driver not found!");
					break;
				}
				case 4: {
					System.out.println("Enter driver_id whose detail need to be updated:");
					int id = sc.nextInt();
					if (ddi.findDriverById(id) == null)
						System.out.println("Driver with this Id does not exist");
					else {
						System.out.println("Enter the required details:-");
						sc.nextLine();
						System.out.println("Enter name:");
						String name = sc.nextLine();
						System.out.println("Enter location:");
						String location = sc.nextLine();
						System.out.println("Enter contact:");
						long contact = sc.nextLong();
						sc.nextLine();
						System.out.println("Enter vechicle_type:");
						String v_type = sc.nextLine();
						System.out.println("Enter vechicle_no:");
						String v_no = sc.nextLine();
						Driver d = new Driver(id, name, location, contact, v_type, v_no, 0, "true");
						if (ddi.updateDriver(d))
							System.out.println("Driver updated succesfully");
					}
					break;
				}
				case 5:
					System.out.println("Enter driver_id:");
					int id = sc.nextInt();
					if (ddi.findDriverById(id) == null)
						System.out.println("Invalid Driver..");
					else {
						if (ddi.deleteDriver(id))
							System.out.println("Driver deleted succesfully!!");
					}
					break;
				case 6: {
					PriorityQueue<Driver> driver = ddi.availableDriver();
					if (driver.isEmpty())
						System.out.println("No driver found");
					else {
						System.out.println("Available Drivers are:-");
						for (Driver a : driver)
							System.out.println(a);
						System.out.println("..................................\n\n");
					}
					break;
				}
				case 8:
					displayMenu();
					break;
				case 9:
					System.out.println("\nClosing Application!!.");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid input, try again!!");
				}
//				}catch(SQLException e) {
//				System.err.println(e.getMessage());
//				}
			} catch (InputMismatchException e) {
				System.err.println("Please enter a valid numeric option.");
				sc.nextLine(); // Clear the invalid input
			} catch (SQLException e) {
				System.err.println("Database error: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
			System.out.println("Do you want to continue(y/n):-");
			sa = sc.next().charAt(0);
		} while (sa == 'y' || sa == 'Y');

	}

	public void ridesMenu() {

		char sa = 'y';
		do {
			try {
				RidesDAOImpl rdi = new RidesDAOImpl();
				System.out.println("----------RIDE MENU----------");
				System.out.println("1. Book Ride");
				System.out.println("2. View All Ride");
				System.out.println("3. Search Ride");
				System.out.println("4. End Ride");
				System.out.println("5. Delete Ride");
				System.out.println("8. Return to main menu");
				System.out.println("9. Exit");
				System.out.println("-------------------------------");
				System.out.println("Chose a option");
				int ch = sc.nextInt();
				switch (ch) {
				case 1: {
					System.out.println("Enter the required details for starting a ride:-");
					System.out.println("Enter User_id:");
					int id = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter Start location:");
					String s_location = sc.nextLine();
					System.out.println("Enter Destination:");
					String destination = sc.nextLine();
					Rides r = new Rides(0, id, 0, s_location, destination, "In Progress", 0);
					if (rdi.bookRides(r))
						System.out.println("Ride started successfully!!");
					
					else
						System.out.println("No driver available at a moment ,please try after some time!!");
					break;
				}
				case 2: {
					List<Rides> rides = rdi.displayRides();
					if (rides.isEmpty())
						System.out.println("No Ride is found");
					else {
						System.out.println("Rides are:-");
						for (Rides r : rides)
							System.out.println(r);
						System.out.println("..................................\n\n");
					}
					break;
				}
				case 3: {
					System.out.println("Enter ride_id:");
					int id = sc.nextInt();
					Rides ab = rdi.findRidesById(id);
					if (ab != null) {
						System.out.println("Rides:-");
						System.out.println(ab);
						System.out.println("..................................\n\n");
					} else
						System.out.println("Rides does not exist!!");
					break;
				}
				case 4: {
					System.out.println("Enter rides_id which need to be terminated:-");
					int id = sc.nextInt();
					if (rdi.findRidesById(id) == null)
						System.out.println("Ride with this Id does not exist");
					else {
						Rides r = new Rides(id, 0, 0, "", "", "Completed", 0.0);
						if (rdi.endRides(r)) {
							System.out.println("Ride Completed succesfully");
							Rides ab = rdi.findRidesById(id);
							System.out.println("Ride Details:-");
							System.out.println(ab);
							System.out.println("..................................\n\n");
							System.out.println("Rate ride and write reviwe(y/n)");
							char c = sc.next().charAt(0);
							if (c == 'y' || c == 'Y') {
								RatingDAOImpl re = new RatingDAOImpl();
								System.out.println("Enter rating(1-5):");
								int rating = sc.nextInt();
								sc.nextLine();
								System.out.println("Enter review");
								String review = sc.nextLine();
								Rating ri = new Rating(0, ab.getUser_id(), ab.getDriver_id(), id, rating, review);
								if (re.addRating(ri))
									System.out.println("Rating saved successfully!!");
								else
									System.out.println("Rating cannot be added,try again!");
							}
						} else
							System.out.println("Something went wrong");
					}
					break;
				}
				case 5:
					System.out.println("Enter Rides_id:");
					int id = sc.nextInt();
					if (rdi.findRidesById(id) == null)
						System.out.println("Invalid Ride..");
					else {
						if (rdi.deleteRides(id))
							System.out.println("Ride deleted succesfully!!");
					}
					break;
				case 8:
					displayMenu();
					break;
				case 9:
					System.out.println("\nClosing Application!!.");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid input, try again!!");
				}
			} catch (InputMismatchException e) {
				System.err.println("Please enter a valid numeric option.");
				sc.nextLine(); // Clear the invalid input
			} catch (SQLException e) {
				System.err.println("Database error: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
			System.out.println("Do you want to continue(y/n):-");
			sa = sc.next().charAt(0);
		} while (sa == 'y' || sa == 'Y');

	}

	public void ratingMenu() {

		char sa = 'y';
		do {
			try {
				RatingDAOImpl rdi = new RatingDAOImpl();
				RidesDAOImpl rdis = new RidesDAOImpl();
				System.out.println("----------Rating MENU----------");
				System.out.println("1. Add Rating");
				System.out.println("2. View All Review");
				System.out.println("3. Update Rating");
				System.out.println("4. Delete Rating");
				System.out.println("8. Return to main menu");
				System.out.println("9. Exit");
				System.out.println("-------------------------------");
				System.out.println("Chose a option");
				int ch = sc.nextInt();
				switch (ch) {
				case 1: {
					System.out.println("Enter the required details:-");
					System.out.println("Enter rides_id to which you want to add rating");
					int id = sc.nextInt();
					Rides r = rdis.findRidesById(id);
					if (r == null)
						System.out.println("Ride with this id doesn't exist!!");
					else if (r.getStatus().equals("In Progress")) {
						System.out.println("Ride is not completed yet, so try once ride is completed!!");
					} else {
						System.out.println("Enter rating(1-5):");
						int rating = sc.nextInt();
						sc.nextLine();
						System.out.println("Enter review");
						String review = sc.nextLine();
						Rating ri = new Rating(0, r.getUser_id(), r.getDriver_id(), id, rating, review);
						if (rdi.addRating(ri))
							System.out.println("Rating saved successfully!!");
						else
							System.out.println("Rating cannot be added,try again!");
					}
					break;
				}
				case 2: {
					List<Rating> r = rdi.displayRating();
					if (r.isEmpty())
						System.out.println("No Reviwe exist");
					else {
						System.out.println("Reviwes are:-");
						for (Rating a : r)
							System.out.println(a);
						System.out.println("..................................\n\n");
					}
					break;
				}
				case 4: {
					System.out.println("Enter rating_id whose detail need to be updated:");
					int id = sc.nextInt();
					if (rdi.findRatingById(id) == null)
						System.out.println("Review with this Id does not exist");
					else {
						System.out.println("Enter the required details:-");
						sc.nextLine();
						System.out.println("Enter rating(1-5):");
						int rating = sc.nextInt();
						sc.nextLine();
						System.out.println("Enter review");
						String review = sc.nextLine();
						Rating r = new Rating(id, 0, 0, 0, rating, review);
						if (rdi.updateRating(r))
							System.out.println("Review updated succesfully");
					}
					break;
				}
				case 8:
					displayMenu();
					break;
				case 9:
					System.out.println("\nClosing Application!!.");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid input, try again!!");
				}
			} catch (InputMismatchException e) {
				System.err.println("Please enter a valid numeric option.");
				sc.nextLine(); // Clear the invalid input
			} catch (SQLException e) {
				System.err.println("Database error: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
			System.out.println("Do you want to continue(y/n):-");
			sa = sc.next().charAt(0);
		} while (sa == 'y' || sa == 'Y');
		sc.close();
	}
}
