Project Overview
The Ride Sharing Application is a Java-based desktop program designed to efficiently manage ride-sharing services between users and drivers. The application allows users to book rides, drivers to manage their availability, and both users and drivers to rate their experiences. The system utilizes MySQL for data storage and JDBC for database connectivity, providing a robust backend solution. The application is structured in a layered architecture to ensure scalability, modularity, and maintainability, with features including user management, driver management, ride management, ratings, and automated database maintenance through triggers.
________________________________________
Key Features
1. User Management:
•	Register new users and update user details.
•	Search and display user information.
•	Users can delete their accounts, with cascading effects on rides and ratings.
•	Users can view their ride history and ratings.
2. Driver Management:
•	Drivers can register and update their profiles.
•	View and search for available drivers.
•	Drivers can delete their profiles, with cascading effects on ride and rating records.
•	Drivers can mark their availability status and view their ratings.
3. Ride Management:
•	Users can book rides by specifying start and destination locations.
•	The system automatically assigns the nearest available driver.
•	Users can end their rides and calculate fares based on distance.
•	Rides are marked as completed or in progress, and users can view past rides.
•	Ride records can be deleted by administrators.
4. Rating and Review System:
•	Users can rate drivers after completing a ride.
•	A text review can be submitted along with the rating.
•	The system recalculates the driver’s average rating in real-time.
•	Users can view all their ratings and reviews, as well as edit or delete them.
5. Database Management:
•	Four primary tables manage the data: Users, Drivers, Rides, and Ratings.
•	Foreign key constraints ensure referential integrity between tables.
•	Triggers automatically clean up orphaned data when users or drivers are deleted.
6. Automated Database Maintenance:
•	Triggers ensure data integrity: 
o	after_driver_delete: Removes orphaned records in the rides and ratings tables where the driver ID is null.
o	after_user_delete: Ensures no orphaned ride or rating data when a user is deleted.
________________________________________
Technologies Used
•	Programming Language: Java (JDK 8+).
•	Database: MySQL with structured tables, foreign key constraints, and check constraints for validation.
•	JDBC: Java Database Connectivity for interaction with MySQL.
•	Framework: Maven for project management and dependency handling.
•	Tools: 
o	MySQL Workbench for schema creation and database management.
o	Eclipse IDE for development.
•	Design Patterns: DAO (Data Access Object) for managing database operations.
•	Architecture: Layered architecture with five distinct packages: Model, DAO, DAO Implementation, Utility, and Presentation.
________________________________________
Database Schema
Tables:
1.	User Table:
o	user_id: Unique identifier for users.
o	name, email, contact: User personal details.
2.	Driver Table:
o	driver_id: Unique identifier for drivers.
o	location, vehicle_type, vehicle_no: Driver’s location and vehicle details.
o	rating: Average rating of the driver.
o	is_available: Availability status of the driver.
3.	Rides Table:
o	ride_id: Unique ride identifier.
o	user_id: Foreign key referencing the user.
o	driver_id: Foreign key referencing the driver.
o	start_location, destination: Ride route details.
o	fare, status: Fare and ride status (In Progress or Completed).
4.	Rating Table:
o	rating_id: Unique identifier for each rating.
o	user_id, driver_id: Foreign keys referencing users and drivers.
o	ride_id: Ride associated with the rating.
o	rating, review: The rating score and text review.
Constraints:
•	Foreign Keys: Ensure data integrity across user, driver, rides, and rating tables.
•	Check Constraints: Validate fields like status, rating, and fare.
________________________________________
Architecture
1. Packages:
•	com.project: Main entry point (App.java) and menu management (Menu.java).
•	Model: POJO classes for User, Driver, Ride, and Rating.
•	DAO: Defines CRUD methods for each entity.
•	DAO Implementation: Concrete implementations of the DAO interfaces with database logic.
•	Utility: Utility classes for managing database connections.
2. Flow:
•	The application starts with a main menu, allowing users to manage users, drivers, rides, and ratings.
•	Submenus direct users to relevant operations, such as registering users or assigning rides.
•	Database interactions are performed via DAO methods, abstracting database details and simplifying operations.
________________________________________
Ride Workflow
1.	Booking a Ride:
o	The user specifies start and destination locations.
o	The nearest available driver is assigned, and the driver’s status is updated to unavailable.
o	The ride status is set to "In Progress."
2.	Ending a Ride:
o	The ride status is updated to "Completed."
o	Fare is calculated based on distance.
o	The driver’s availability is updated to available.
3.	Rating a Ride:
o	After completing a ride, the user can rate and review the driver.
o	The driver’s average rating is updated automatically based on new reviews.
________________________________________
Features Highlight
1.	Modularity:
o	The application’s layered architecture separates concerns, ensuring scalability and ease of extension.
2.	Automation:
o	Triggers automate database maintenance by removing orphaned records.
o	Ratings are updated automatically without manual intervention.
3.	Efficiency:
o	Real-time assignment of the nearest available driver ensures minimal waiting times.
o	Optimized database queries ensure fast operations even with larger datasets.
4.	User Experience:
o	The intuitive menu-driven interface makes navigation easy for users and administrators.
o	Error handling provides helpful feedback for invalid inputs.
________________________________________
Conclusion
The Ride Sharing Application demonstrates a robust implementation of Java, JDBC, and MySQL, effectively managing users, drivers, rides, and ratings in a scalable and efficient manner. Its modular design, automated database management, and optimized performance provide a strong foundation for real-world ride-sharing services. The project showcases expertise in Java development, database design, and software engineering best practices, with a focus on maintainability and extensibility for future enhancements.
