package com.project.BankingSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.project.BankingSystem.Service.*;
import com.project.BankingSystem.ServiceImpl.*;
import com.project.BankingSystem.model.*;

public class Menu {

	Scanner sc = new Scanner(System.in);
	AdminService admin_s = new AdminServiceImpl();
	UserService user_s = new UserServiceImpl();
	BranchService branch_s = new BranchServiceImpl();
	AccountService account_s = new AccountServiceImpl();
	TransactionService trans_s = new TransactionServiceImpl();
	CardService card_s = new CardServiceImpl();
	char f = 'y';

	private String generateRandomNumber(int length) {
		Random random = new Random();
		StringBuilder number = new StringBuilder();
		for (int i = 0; i < length; i++) {
			number.append(random.nextInt(10)); // Append a random digit (0-9)
		}
		return number.toString();
	}

	public Address input() {
		System.out.println("Enter Appartment no: ");
		String h_no = sc.nextLine().trim();
		System.out.println("Enter Area: ");
		String area = sc.nextLine().trim();
		System.out.println("Enter city: ");
		String city = sc.nextLine().trim();
		System.out.println("Enter state: ");
		String state = sc.nextLine().trim();
		System.out.println("Enter Pincode no: ");
		String pincode = sc.nextLine().trim();
		return new Address(0, h_no, area, city, state, pincode);
	}

	private void displayProfileMenu() {
		System.out.println("1. View Profile");
		System.out.println("2. Update Profile");
		System.out.println("3. Delete Profile");
		System.out.println("4. Change Password");
	}

	public void displayAllBranch() {
		List<Branch> branch = branch_s.viewAll();
		if (branch.isEmpty())
			System.out.println("No Branch exist");
		else {
			System.out.println("Branches are:-");
			for (Branch a : branch)
				System.out.println(a);
			System.out.println("..................................\n\n");
		}
		sc.nextLine();
	}

	public void displayMenu() {
		System.out.println("Welcome to the Banking System Appliaction !!");
		System.out.println("--------MAIN MENU--------");
		System.out.println("1. Admin");
		System.out.println("2. User");
		System.out.println("9. Exit");
		System.out.println("-------------------------");
		System.out.println("Chose a option");
		try {
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				adminMainMenu();
				break;
			case 2:
				userMainMenu();
				break;
			case 9:
				System.out.println("\nClosing Application!!.");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid input, try again!!");
				displayMenu();
			}
		} catch (InputMismatchException e) {
			System.out.println("Input mismatch ,Please enter a valid value.");
			sc.nextLine();
			displayMenu();
		} catch (Exception e) {
			System.err.println("An error Occured:-" + e.getMessage());
			displayMenu();
		}

	}

	private void adminMainMenu() {
		do {
			System.out.println("Welcome to Admin Menu");
			System.out.println("--------ADMIN MENU--------");
			System.out.println("1. Register Admin");
			System.out.println("2. Login");
			System.out.println("9. Exit");
			System.out.println("-------------------------");
			System.out.println("Chose a option");
			try {
				int ch = sc.nextInt();
				sc.nextLine();
				switch (ch) {
				case 1: {
					System.out.println("Enter the required details for Admin registration:-");
					System.out.println("Enter Name:");
					String name = sc.nextLine().trim();
					System.out.println("Enter email:");
					String email = sc.nextLine().trim();
					System.out.println("Enter contact:");
					long contact = sc.nextLong();
					sc.nextLine();
					System.out.println("Enter username:");
					String u_name = sc.nextLine().trim().toLowerCase();
					System.out.println("Enter Password");
					String pass = sc.nextLine().trim();
					System.out.println("Address\n");
					Address ab = input();
					Admin a = new Admin(name, email, contact, u_name, pass, ab);
					Admin n = admin_s.saveAdmin(a);
					if (n != null)
						System.out.println("Admin registered successfully!!\n" + n);
					else
						System.out.println("Admin cannot be registered,try again!");
					break;
				}
				case 2: {
					char tr = 'y';
					boolean flag = false;
					do {
						System.out.println("Enter the username:-");
						String u_name = sc.nextLine().trim().toLowerCase();
						System.out.println("Enter Password");
						String pass = sc.nextLine().trim();
						Admin a = admin_s.getByUsername(u_name);
						if (a != null) {
							if (a.getPassword().equals(pass)) {
								System.out.println("Login Succesfull !!");
								adminLoginMenu(a.getId());
								tr = 'n';
							} else {
								System.out.println("Incorrect password !!");
								flag = true;
							}
						} else {
							System.out.println("Invalid username !!");
							flag = true;
						}
						if (flag) {
							System.out.println(" Do you want to try again(y/n)");
							tr = sc.nextLine().trim().toLowerCase().charAt(0);
						}
					} while (tr == 'y');
					break;
				}
				case 9:
					System.out.println("\nClosing Application!!.");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid input, try again!!");
				}
				System.out.println(" Do you want to continue (y/n)");
				f = sc.nextLine().toLowerCase().charAt(0);
			} catch (InputMismatchException e) {
				System.out.println("Input mismatch ,Please enter a valid value.");
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
		} while (f == 'y');
		if (f == 'n')
			System.exit(0);
	}

	private void adminProfileMenu(int id) {
		Admin ab = null;
		do {
			System.out.println("--------MENU--------");
			displayProfileMenu();
			System.out.println("8. Back");
			System.out.println("9. Logout");
			System.out.println("-------------------------");
			System.out.println("Chose a option");
			try {
				int ch = sc.nextInt();
				ab = admin_s.viewAdminById(id);
				switch (ch) {
				case 1: {
					ab = admin_s.viewAdminById(id);
					if (ab != null) {
						System.out.println("Admin:-");
						System.out.println(ab);
						System.out.println("..................................\n\n");
					} else
						System.out.println("Admin not found!");
					sc.nextLine();
					break;
				}
				case 2: {
					Admin s = admin_s.viewAdminById(id);
					System.out.println("Enter the required details:-");
					sc.nextLine();
					System.out.println("Enter Name:");
					String name = sc.nextLine().trim();
					System.out.println("Enter email:");
					String email = sc.nextLine().trim();
					System.out.println("Enter contact:");
					long contact = sc.nextLong();
					sc.nextLine();
					System.out.println("Enter username:");
					String u_name = sc.nextLine().trim();
					System.out.println("Address\n");
					Address abc = input();
					Admin a = new Admin(name.isBlank() ? s.getName() : name, email.isBlank() ? s.getEmail() : email,
							contact == 0 ? s.getContact() : contact, u_name.isBlank() ? s.getUserName() : u_name,
							s.getPassword(), abc == null ? s.getAddress() : abc);
					a.setId(id);
					Admin n = admin_s.update(a);
					if (n != null)
						System.out.println("Admin updated succesfully");
					else
						System.out.println("Something went wrong");
					break;
				}
				case 3: {
					if (admin_s.viewAdminById(id) == null)
						System.out.println("Invalid Admin..");
					else {
						if (admin_s.deleteAdmin(id))
							System.out.println("Admin deleted succesfully!!");
						else
							System.out.println("Something went wrong");
					}
					sc.nextLine();
					break;
				}
				case 4: {
					char ts = 'y';
					do {
						System.out.println("Enter Old Password");
						sc.nextLine();
						String o_pass = sc.nextLine().trim();
						System.out.println("Enter New Password");
						String n_pass = sc.nextLine().trim();
						System.out.println("Enter New  Password again");
						String pass = sc.nextLine().trim();
						if (ab.getPassword().equals(o_pass) && pass.equals(n_pass) && !o_pass.equals(n_pass)) {
							ab.setPassword(n_pass);
							Admin n = admin_s.update(ab);
							ts = 'n';
							if (n != null)
								System.out.println("Password updated succesfully");
							else
								System.out.println("Something went wrong");
						} else {
							if (o_pass.equals(n_pass))
								System.out.println("Old and new password cannot be same!!");
							else
								System.out.println("Password doesn't match");
							System.out.println("Do you want to try again (y/n)");
							ts = sc.nextLine().toLowerCase().charAt(0);
						}
					} while (ts == 'y');
					break;
				}
				case 8:
					adminLoginMenu(ab.getId());
					break;
				case 9:
					System.out.println("\nLogout Succesfull !!");
					displayMenu();
					break;
				default:
					System.out.println("Invalid input, try again!!");
				}
				System.out.println(" Do you want to continue (y/n)");
				f = sc.nextLine().toLowerCase().charAt(0);
			} catch (InputMismatchException e) {
				System.out.println("Input mismatch ,Please enter a valid value.");
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
		} while (f == 'y');
		if (f == 'n') {
			System.out.println("\nClosing Application!!.");
			System.exit(0);
		}

	}

	private void adminLoginMenu(int id) {
		do {
			System.out.println("--------MENU--------");
			System.out.println("1. Profile");
			System.out.println("2. View All Admin");
			System.out.println("3. Search Admin");
			System.out.println("4. View all User");
			System.out.println("5. View All Card");
			System.out.println("6. Branch Services");
			System.out.println("7. Account Service");
			System.out.println("8. Transaction Services");
			System.out.println("9. Logout");
			System.out.println("-------------------------");
			System.out.println("Chose a option");
			try {
				int ch = sc.nextInt();
				switch (ch) {
				case 1: {
					adminProfileMenu(id);
					break;
				}
				case 2: {
					List<Admin> admin = admin_s.viewAll();
					if (admin.isEmpty())
						System.out.println("No admin found");
					else {
						System.out.println("Admins are:-");
						for (Admin a : admin)
							System.out.println(a.getName());
						System.out.println("..................................\n\n");
					}
					sc.nextLine();
					break;
				}
				case 3: {
					System.out.println("Enter admin_id to be searched:");
					int ids = sc.nextInt();
					Admin ad = admin_s.viewAdminById(ids);
					if (ad != null) {
						System.out.println("Admin:-");
						System.out.println(ad);
						System.out.println("..................................\n\n");
					} else
						System.out.println("Admin not found!");
					sc.nextLine();
					break;
				}
				case 4: {
					List<User> n = user_s.viewAllUser();
					if (n.isEmpty())
						System.out.println("No User found");
					else {
						System.out.println("Users are:-");
						for (User a : n)
							System.out.println(a);
						System.out.println("..................................\n\n");
					}
					sc.nextLine();
					break;
				}
				case 5: {
					List<Card> n = card_s.viewAll();
					if (n.isEmpty())
						System.out.println("No Card found");
					else {
						System.out.println("Cards are:-");
						for (Card a : n)
							System.out.println(a);
						System.out.println("..................................\n\n");
					}
					sc.nextLine();
					break;
				}
				case 6:
					adminBranchMenu(id);
					break;
				case 7:
					adminAccountMenu(id);
					break;
				case 8:
					adminTransactionMenu(id);
					break;
				case 9:
					System.out.println("\nLogout Succesfull !!");
					displayMenu();
					break;
				default:
					System.out.println("Invalid input, try again!!");
				}
				System.out.println(" Do you want to continue (y/n)");
				f = sc.nextLine().toLowerCase().charAt(0);
			} catch (InputMismatchException e) {
				System.out.println("Input mismatch ,Please enter a valid value.");
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
		} while (f == 'y');
		if (f == 'n') {
			System.out.println("\nClosing Application!!.");
			System.exit(0);
		}
	}

	private void adminTransactionMenu(int id) {
		do {
			System.out.println("--------Transaction MENU--------");
			System.out.println("1. View All Transaction");
			System.out.println("2. View All Transaction by Tranasction Status");
			System.out.println("8. Back");
			System.out.println("9. Logout");
			System.out.println("-------------------------");
			System.out.println("Chose a option");
			try {
				int ch = sc.nextInt();
				sc.nextLine();
				switch (ch) {
				case 1:
					trans_s.listOfTransaqction();
					break;
				case 2: {
					System.out.println("Enter Transaction status (Success/Failed)");
					String t_status = sc.nextLine().trim();
					trans_s.listOfTransactionByStatus(t_status);
					break;
				}
				case 8:
					adminLoginMenu(id);
					break;
				case 9:
					System.out.println("\nLogout Succesfull !!");
					displayMenu();
					break;
				default:
					System.out.println("Invalid input, try again!!");
				}
				System.out.println(" Do you want to continue (y/n)");
				f = sc.nextLine().toLowerCase().charAt(0);
			} catch (InputMismatchException e) {
				System.out.println("Input mismatch ,Please enter a valid value.");
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
		} while (f == 'y');
		if (f == 'n') {
			System.out.println("\nClosing Application!!.");
			System.exit(0);
		}
	}

	private void adminAccountMenu(int id) {
		do {
			System.out.println("--------Account MENU--------");
			System.out.println("1. View All Account");
			System.out.println("2. View All Account by Types");
			System.out.println("3. Search Account");
			System.out.println("8. Back");
			System.out.println("9. Logout");
			System.out.println("-------------------------");
			System.out.println("Chose a option");
			try {
				int ch = sc.nextInt();
				sc.nextLine();
				switch (ch) {
				case 1: {
					List<Account> ac = account_s.viewAllAcount();
					if (ac.isEmpty())
						System.out.println("No account exist");
					else {
						System.out.println("Accounts are:-");
						for (Account a : ac)
							System.out.println(a);
						System.out.println("..................................\n\n");
					}
					break;
				}
				case 2: {

					System.out.println("Enter account Type (Saving/Curent)");
					AccountType ab = AccountType.valueOf(sc.nextLine().trim());
					List<Account> ac = account_s.viewByType(ab);
					if (ac.isEmpty())
						System.out.println("No account exist");
					else {
						System.out.println("Accounts are:-");
						for (Account a : ac)
							System.out.println(a);
						System.out.println("..................................\n\n");
					}
					break;
				}
				case 3: {
					System.out.println("Enter account_number:");
					String no = sc.nextLine().trim();
					Account a = account_s.viewAccountByNumber(no);
					if (a != null) {
						System.out.println("Account Detail:-");
						System.out.println(a);
						System.out.println("..................................\n\n");
					} else
						System.out.println("Account not found !!");
					break;
				}
				case 8:
					adminLoginMenu(id);
					break;
				case 9:
					System.out.println("\nLogout Succesfull !!");
					displayMenu();
					break;
				default:
					System.out.println("Invalid input, try again!!");
				}
				System.out.println(" Do you want to continue (y/n)");
				f = sc.nextLine().toLowerCase().charAt(0);
			} catch (InputMismatchException e) {
				System.out.println("Input mismatch ,Please enter a valid value.");
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
		} while (f == 'y');
		if (f == 'n') {
			System.out.println("\nClosing Application!!.");
			System.exit(0);
		}
	}

	private void adminBranchMenu(int id) {
		do {
			System.out.println("--------Branch MENU--------");
			System.out.println("1. Open Branch");
			System.out.println("2. View Branches");
			System.out.println("3. Search Branch");
			System.out.println("4. Update Branch");
			System.out.println("5. Close Branch");
			System.out.println("8. Back");
			System.out.println("9. Logout");
			System.out.println("-------------------------");
			System.out.println("Chose a option");
			try {
				int ch = sc.nextInt();
				switch (ch) {
				case 1: {
					System.out.println("Enter the required details for Branch creation:");
					sc.nextLine();
					System.out.println("Enter Branch Name:");
					String name = sc.nextLine().trim();
					boolean flag = true;
					Admin a = null;
					while (flag) {
						System.out.println("Enter Branch Manager_id:");
						int a_id = sc.nextInt();
						a = admin_s.viewAdminById(a_id);
						if (a == null)
							System.out.println("Admin with this ID : " + a_id + " doesn't exist, try again");
						else
							flag = false;
					}
					sc.nextLine();
					System.out.println("Address\n");
					Address ab = input();
					List<Account> acc = new ArrayList<Account>();
					Branch b = new Branch(name, a, ab, acc);
					b = branch_s.saveBranch(b);
					if (b != null)
						System.out.println("Branch created successfully!!\n" + b);
					else
						System.out.println("Branch cannot be created,try again!");
					break;
				}
				case 2: {
					displayAllBranch();
					break;
				}
				case 3: {
					System.out.println("Enter branch_id:");
					int ids = sc.nextInt();
					Branch ab = branch_s.findBranchById(ids);
					if (ab != null) {
						System.out.println("Branch:-");
						System.out.println(ab);
						System.out.println("..................................\n\n");
					} else
						System.out.println("Branch not found!");
					sc.nextLine();
					break;
				}
				case 4: {
					System.out.println("Enter branch_id whose detail need to be updated:");
					int ids = sc.nextInt();
					Branch c = branch_s.findBranchById(ids);
					if (c == null)
						System.out.println("Branch with this Id does not exist");
					else {
						System.out.println("Enter the required details:-");
						sc.nextLine();
						System.out.println("Enter Branch Name:");
						String name = sc.nextLine().trim();
						System.out.println("Enter Branch Manager_id:");
						int a_id = sc.nextInt();
						Admin a = admin_s.viewAdminById(a_id);
						sc.nextLine();
						System.out.println("Address\n");
						Address ab = input();
						Branch b = new Branch(name.isBlank() ? c.getName() : name, a == null ? c.getManager() : a,
								ab == null ? c.getAddress() : ab, c.getAccounts());
						// Branch b= new Branch(name, a, ab);
						b.setId(ids);
						b = branch_s.updateBranch(b);
						if (b != null)
							System.out.println("Branch updated succesfully");
						else
							System.out.println("Something went wrong");
					}
					break;
				}
				case 5:
					System.out.println("Enter branch_id which is to be closed:");
					int ids = sc.nextInt();
					if (branch_s.findBranchById(ids) == null)
						System.out.println("Invalid Branch..");
					else {
						if (branch_s.deleteBranch(ids))
							System.out.println("Branch deleted succesfully!!");
						else
							System.out.println("Something went wrong");
					}
					sc.nextLine();
					break;
				case 8:
					adminLoginMenu(id);
					break;
				case 9:
					System.out.println("\nLogout Succesfull !!");
					displayMenu();
					break;
				default:
					System.out.println("Invalid input, try again!!");
				}
				System.out.println(" Do you want to continue (y/n)");
				f = sc.nextLine().toLowerCase().charAt(0);
			} catch (InputMismatchException e) {
				System.out.println("Input mismatch ,Please enter a valid value.");
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
		} while (f == 'y');
		if (f == 'n') {
			System.out.println("\nClosing Application!!.");
			System.exit(0);
		}
	}

	public void userMainMenu() {
		do {
			System.out.println("Welcome to User Menu");
			System.out.println("--------User MENU--------");
			System.out.println("1. Register User");
			System.out.println("2. Login");
			System.out.println("9. Exit");
			System.out.println("-------------------------");
			System.out.println("Chose a option");
			try {
				int ch = sc.nextInt();
				sc.nextLine();
				switch (ch) {
				case 1: {
					System.out.println("Enter the required details for User registration:-");
					System.out.println("Enter Name:");
					String name = sc.nextLine().trim();
					System.out.println("Enter email:");
					String email = sc.nextLine().trim();
					System.out.println("Enter contact:");
					long contact = sc.nextLong();
					sc.nextLine();
					System.out.println("Enter username:");
					String u_name = sc.nextLine().trim().toLowerCase();
					System.out.println("Enter Password");
					String pass = sc.nextLine().trim();
					System.out.println("Address\n");
					Address ab = input();
					List<Account> acc = new ArrayList<Account>();
					User a = new User(name, email, contact, u_name, pass, ab, acc);
					User n = user_s.saveUser(a);
					if (n != null)
						System.out.println("User registered successfully!!\n" + n);
					else
						System.out.println("User cannot be registered,try again!");
					break;
				}
				case 2: {
					char tr = 'y';
					boolean flag = false;
					do {
						System.out.println("Enter the username:-");
						String u_name = sc.nextLine().trim().toLowerCase();
						System.out.println("Enter Password");
						String pass = sc.nextLine().trim();
						User a = user_s.getByUsername(u_name);
						if (a != null) {
							if (a.getPassword().equals(pass)) {
								System.out.println("Login Succesfull !!");
								userLoginMenu(a.getId());
								tr = 'n';
							} else {
								System.out.println("Incorrect password !!");
								flag = true;
							}
						} else {
							System.out.println("Invalid username !!");
							flag = true;
						}
						if (flag) {
							System.out.println(" Do you want to try again(y/n)");
							tr = sc.nextLine().toLowerCase().charAt(0);
						}
					} while (tr == 'y');
					break;
				}
				case 9:
					System.out.println("\nClosing Application!!.");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid input, try again!!");
				}
				System.out.println(" Do you want to continue (y/n)");
				f = sc.nextLine().toLowerCase().charAt(0);
			} catch (InputMismatchException e) {
				System.out.println("Input mismatch ,Please enter a valid value.");
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
		} while (f == 'y');
		if (f == 'n')
			System.exit(0);
	}

	public void userLoginMenu(int user_id) {
		User ab = null;
		do {
			System.out.println("--------MENU--------");
			displayProfileMenu();
			System.out.println("5. View All Branch");
			System.out.println("6. Account");
			System.out.println("7. Transaction");
			System.out.println("8. Card");
			System.out.println("9. Logout");
			System.out.println("-------------------------");
			System.out.println("Chose a option");
			try {
				int ch = sc.nextInt();
				ab = user_s.viewUserById(user_id);
				switch (ch) {
				case 1: {
					ab = user_s.viewUserById(user_id);
					if (ab != null) {
						System.out.println("User:-");
						System.out.println(ab);
						System.out.println("..................................\n\n");
					} else
						System.out.println("User not found!");
					sc.nextLine();
					break;
				}
				case 2: {
					User s = user_s.viewUserById(user_id);
					if (s == null)
						System.out.println("User with this Id does not exist");
					else {
						System.out.println("Enter the required details:-");
						sc.nextLine();
						System.out.println("Enter Name:");
						String name = sc.nextLine().trim();
						System.out.println("Enter email:");
						String email = sc.nextLine().trim();
						System.out.println("Enter contact:");
						long contact = sc.nextLong();
						sc.nextLine();
						System.out.println("Enter username:");
						String u_name = sc.nextLine().trim();
						System.out.println("Address\n");
						Address abc = input();
						User a = new User(name.isBlank() ? s.getName() : name, email.isBlank() ? s.getEmail() : email,
								contact == 0 ? s.getContact() : contact, u_name.isBlank() ? s.getUserName() : u_name,
								s.getPassword(), abc == null ? s.getAddress() : abc, s.getAccount());
						a.setId(user_id);
						User n = user_s.update(a);
						if (n != null)
							System.out.println("User updated succesfully");
						else
							System.out.println("Something went wrong");
					}

					break;
				}
				case 3: {
					if (user_s.viewUserById(user_id) == null)
						System.out.println("Invalid User..");
					else {
						if (user_s.deleteUser(user_id))
							System.out.println("User deleted succesfully!!");
						else
							System.out.println("Something went wrong");
					}
					sc.nextLine();
					break;
				}
				case 4: {
					char ts = 'y';
					do {
						System.out.println("Enter Old Password");
						sc.nextLine();
						String o_pass = sc.nextLine().trim();
						System.out.println("Enter New Password");
						String n_pass = sc.nextLine().trim();
						System.out.println("Enter New  Password again");
						String pass = sc.nextLine().trim();
						if (ab.getPassword().equals(o_pass) && pass.equals(n_pass) && !o_pass.equals(n_pass)) {
							ab.setPassword(n_pass);
							User n = user_s.update(ab);
							ts = 'n';
							if (n != null)
								System.out.println("Password updated succesfully");
							else
								System.out.println("Something went wrong");
						} else {
							if (o_pass.equals(n_pass))
								System.out.println("Old and new password cannot be same!!");
							else
								System.out.println("Password doesn't match");
							System.out.println("Do you want to try again (y/n)");
							ts = sc.nextLine().toLowerCase().charAt(0);
						}
					} while (ts == 'y');
					break;
				}
				case 5: {
					displayAllBranch();
					break;
				}
				case 6: {
					userAccountMenu(ab);
					break;
				}
				case 7: {
					userTransactionMenu(ab);
					break;
				}
				case 8: {
					userCardMenu(ab);
					break;
				}
				case 9:
					System.out.println("\nLogout Succesfull !!");
					displayMenu();
					break;
				default:
					System.out.println("Invalid input, try again!!");
				}
				System.out.println(" Do you want to continue (y/n)");
				f = sc.nextLine().toLowerCase().charAt(0);
			} catch (InputMismatchException e) {
				System.out.println("Input mismatch ,Please enter a valid value.");
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
		} while (f == 'y');
		if (f == 'n') {
			System.out.println("\nClosing Application!!.");
			System.exit(0);
		}
	}

	private void userCardMenu(User ab) {
		do {
			System.out.println("--------Card MENU--------");
			System.out.println("1. Issue Card");
			System.out.println("2. View Card Detail");
			System.out.println("3. Return Card");
			System.out.println("8. Back");
			System.out.println("9. Logout");
			System.out.println("-------------------------");
			System.out.println("Chose a option");
			try {
				int ch = sc.nextInt();
				switch (ch) {
				case 1: {
					sc.nextLine();
					System.out.println("Enter the card type (Credit/Debit):");
					String c_type = sc.nextLine().trim();
					String c_no = generateRandomNumber(16);
					String cvc = generateRandomNumber(3);
					Account ac = null;
					if (c_type.equals("Debit")) {
						System.out.println("Enter account_no whose card is to be issued: ");
						String no = sc.nextLine().trim();
						ac = account_s.viewAccountByNumber(no);
					}
					Card c = new Card(c_no, c_type, LocalDate.now().plusYears(4), cvc, ac);
					c = card_s.issueCard(c);
					if (c != null)
						System.out.println("Card issued successfully!!\n Card details: " + c);
					else
						System.out.println("Something went wrong ");
					break;
				}
				case 2: {
					sc.nextLine();
					System.out.println("Enter card_number:");
					String no = sc.nextLine().trim();
					Card a = card_s.viewDetailbyNumber(no);
					if (a != null) {
						System.out.println("Card Detail:-");
						System.out.println(a);
						System.out.println("..................................\n\n");
					} else
						System.out.println("Card not found !!");
					break;
				}
				case 3: {
					sc.nextLine();
					System.out.println("Enter card_number to be deleted:");
					String no = sc.nextLine().trim();
					Card a = card_s.viewDetailbyNumber(no);
					if (a == null)
						System.out.println("Invalid Card..");
					else {
						if (card_s.returnCard(a.getId()))
							System.out.println("Card  deleted succesfully!!");
						else
							System.out.println("Something went wrong");
					}
					break;
				}
				case 8:
					userLoginMenu(ab.getId());
					break;
				case 9:
					System.out.println("\nLogout Succesfull !!");
					displayMenu();
					break;

				default:
					System.out.println("Invalid input, try again!!");
				}
				System.out.println(" Do you want to continue (y/n)");
				f = sc.nextLine().toLowerCase().charAt(0);
			} catch (InputMismatchException e) {
				System.out.println("Input mismatch ,Please enter a valid value.");
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
		} while (f == 'y');
		if (f == 'n') {
			System.out.println("\nClosing Application!!.");
			System.exit(0);
		}
	}

	private void userTransactionMenu(User ab) {
		do {
			System.out.println("--------Transaction MENU--------");
			System.out.println("1. Withdrawl");
			System.out.println("2. Deposit");
			System.out.println("3. View last 10 transaction");
			System.out.println("4. View all Transaction");
			System.out.println("5. Statement");
			System.out.println("6. Search Transaction");
			System.out.println("8. Back");
			System.out.println("9. Logout");
			System.out.println("-------------------------");
			System.out.println("Chose a option");
			try {
				int ch = sc.nextInt();
				switch (ch) {
				case 1: {
					sc.nextLine();
					System.out.println("Enter account_no: ");
					String no = sc.nextLine().trim();
					Account ac = account_s.viewAccountByNumber(no);
					if (ac == null) {
						System.out.println("Account number is invalid, please try again !!");
						break;
					}
					System.out.println("Enter the amount: ");
					double amnt = sc.nextDouble();
					Transactions t = new Transactions("", "", amnt, null, ac);
					trans_s.withdrawService(t);
					sc.nextLine();
					break;
				}
				case 2: {
					sc.nextLine();
					System.out.println("Enter account_no in which amount is to be deposited: ");
					String no = sc.nextLine().trim();
					Account ac = account_s.viewAccountByNumber(no);
					if (ac == null) {
						System.out.println("Account number is invalid, please try again !!");
						break;
					}
					System.out.println("Enter the amount: ");
					double amnt = sc.nextDouble();
					Transactions t = new Transactions("", "", amnt, null, ac);
					trans_s.depositService(t);
					sc.nextLine();
					break;
				}
				case 3: {
					sc.nextLine();
					System.out.println("Enter account_no: ");
					String no = sc.nextLine().trim();
					Account ac = account_s.viewAccountByNumber(no);
					if (ac == null) {
						System.out.println("Account number is invalid, please try again !!");
						break;
					}
					trans_s.printLast10Transactions(ac.getId());
					break;
				}
				case 4: {
					sc.nextLine();
					System.out.println("Enter account_no: ");
					String no = sc.nextLine().trim();
					Account ac = account_s.viewAccountByNumber(no);
					if (ac == null) {
						System.out.println("Account number is invalid, please try again !!");
						break;
					}
					trans_s.viewAllByAccount(ac.getId());
					break;
				}
				case 5: {
					sc.nextLine();
					System.out.println("Enter account_no: ");
					String no = sc.nextLine().trim();
					Account ac = account_s.viewAccountByNumber(no);
					if (ac == null) {
						System.out.println("Account number is invalid, please try again !!");
						break;
					}
					System.out.println("Enter Start Date (yyyy-mm-dd)");
					LocalDate s_date = LocalDate.parse(sc.nextLine().trim());
					System.out.println("Enter End Date (yyyy-mm-dd)");
					LocalDate e_date = LocalDate.parse(sc.nextLine().trim());
					trans_s.printAccountStatement(ac.getId(), s_date, e_date);
					break;
				}
				case 6: {
					System.out.println("Enter transaction_id: ");
					int id = sc.nextInt();
					trans_s.diplayTransactionsById(id);
					sc.nextLine();
					break;
				}
				case 8:
					userLoginMenu(ab.getId());
					break;
				case 9:
					System.out.println("\nLogout Succesfull !!");
					displayMenu();
					break;

				default:
					System.out.println("Invalid input, try again!!");
				}
				System.out.println(" Do you want to continue (y/n)");
				f = sc.nextLine().toLowerCase().charAt(0);
			} catch (InputMismatchException e) {
				System.out.println("Input mismatch ,Please enter a valid value.");
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
		} while (f == 'y');
		if (f == 'n') {
			System.out.println("\nClosing Application!!.");
			System.exit(0);
		}
	}

	private void userAccountMenu(User ab) {
		do {
			System.out.println("--------Account MENU--------");
			System.out.println("1. Create Account");
			System.out.println("2. View Account Detail");
			System.out.println("3. Check Balance");
			System.out.println("4. View All Accounts");
			System.out.println("5. Delete Account");
			System.out.println("8. Back");
			System.out.println("9. Logout");
			System.out.println("-------------------------");
			System.out.println("Chose a option");
			try {
				int ch = sc.nextInt();
				switch (ch) {
				case 1: {
					System.out.println("Enter Account Type (Saving/Current) :-");
					sc.nextLine();
					AccountType type = AccountType.valueOf(sc.nextLine().trim());
					String account_no = generateRandomNumber(12);
					System.out.println("Enter branch_id");
					int id = sc.nextInt();
					Branch b = branch_s.findBranchById(id);
					Account s = new Account(type, 0, LocalDate.now(), account_no, null, b, ab);
					s = account_s.createAccount(s);
					if (s != null)
						System.out.println("Account created successfully!!\n" + s);
					else
						System.out.println("Something went wrong ");
					sc.nextLine();
					break;
				}
				case 2: {
					sc.nextLine();
					System.out.println("Enter account_number:");
					String no = sc.nextLine().trim();
					Account a = account_s.viewAccountByNumber(no);
					if (a != null) {
						System.out.println("Account Detail:-");
						System.out.println(a);
						System.out.println("..................................\n\n");
					} else
						System.out.println("Account not found !!");
					break;
				}
				case 3: {
					sc.nextLine();
					System.out.println("Enter account_number:");
					String no = sc.nextLine().trim();
					Account a = account_s.viewAccountByNumber(no);
					if (a == null) {
						System.out.println("Account number is invalid, please try again !!");
						break;
					}
					System.out.println("Balance : " + account_s.checkBalance(a.getId()));
					break;
				}
				case 4: {
					List<Account> acc = account_s.viewAllAcountByUser(ab.getId());
					if (acc.isEmpty())
						System.out.println("No Account found");
					else {
						System.out.println("Accounts are:-");
						for (Account a : acc)
							System.out.println(a);
						System.out.println("..................................\n\n");
					}
					sc.nextLine();
					break;

				}
				case 5: {
					sc.nextLine();
					System.out.println("Enter account_number to be deleted:");
					String no = sc.nextLine().trim();
					Account a = account_s.viewAccountByNumber(no);
					if (a == null)
						System.out.println("Invalid Account..");
					else {
						if (account_s.deleteAccount(a))
							System.out.println("Account  deleted succesfully!!");
						else
							System.out.println("Something went wrong");
					}
					break;
				}
				case 8:
					userLoginMenu(ab.getId());
					break;
				case 9:
					System.out.println("\nLogout Succesfull !!");
					displayMenu();
					break;

				default:
					System.out.println("Invalid input, try again!!");
				}
				System.out.println(" Do you want to continue (y/n)");
				f = sc.nextLine().toLowerCase().charAt(0);
			} catch (InputMismatchException e) {
				System.out.println("Input mismatch ,Please enter a valid value.");
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("An error Occured:-" + e.getMessage());
			}
		} while (f == 'y');
		if (f == 'n') {
			System.out.println("\nClosing Application!!.");
			System.exit(0);
		}
	}

}
