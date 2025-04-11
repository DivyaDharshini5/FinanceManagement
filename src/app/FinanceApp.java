package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import Model.Expenses;
import Model.User;
import dao.FinanceRepositoryImpl;

public class FinanceApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FinanceRepositoryImpl repository = new FinanceRepositoryImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("\nMenu");
            System.out.println("1. Add User");
            System.out.println("2. Add Expense");
            System.out.println("3. Delete User");
            System.out.println("4. Delete Expense");
            System.out.println("5. Update Expense");
            System.out.println("6. Unit Testing");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); 
                continue;
            }

            sc.nextLine(); 
            switch (choice) {
                case 1: {
                    try {
                        System.out.print("Enter user_id: ");
                        int user_id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter username: ");
                        String username = sc.nextLine();

                        System.out.print("Enter password: ");
                        String password = sc.nextLine();

                        System.out.print("Enter email: ");
                        String email = sc.nextLine();

                        User user = new User(user_id, username, password, email);
                        boolean success = repository.createUser(user);
                        if(success) {
                        	System.out.println("User created successfully");
                        }
                        else {
                        	System.out.println("Failed to create user");
                        }

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;
                }

                case 2: {
                    try {
                        System.out.print("Enter expense_id: ");
                        int expense_id = sc.nextInt();

                        System.out.print("Enter user_id: ");
                        int user_id = sc.nextInt();

                        System.out.print("Enter amount: ");
                        double amount = sc.nextDouble();

                        System.out.print("Enter category_id: ");
                        int category_id = sc.nextInt();

                        sc.nextLine(); 
                        System.out.print("Enter date (yyyy-MM-dd): ");
                        String dateStr = sc.nextLine();
                        java.sql.Date date = new java.sql.Date(sdf.parse(dateStr).getTime());

                        System.out.print("Enter description: ");
                        String description = sc.nextLine();

                        Expenses expense = new Expenses(expense_id, amount, user_id, category_id, date, description);
                        boolean success = repository.createExpense(expense);
                        if(success) {
                        	System.out.println("Expense created successfully");
                        }
                        else {
                        	System.out.println("Failed to create expense");
                        }
                    } catch (ParseException pe) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        sc.nextLine(); 
                    }
                    break;
                }

                case 3: {
                	try {
                    System.out.print("Enter user_id to delete: ");
                    int user_id = sc.nextInt();
                    boolean success = repository.deleteUser(user_id);
                    System.out.println(success ? "User deleted successfully" : "Failed to delete user");}
                    catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid user_id.");
                        sc.nextLine(); 
                    }
                    break;
                }

                case 4: {
                	try {
                		
                	
                    System.out.print("Enter expense_id to delete: ");
                    int expense_id = sc.nextInt();
                    boolean success = repository.deleteExpense(expense_id);
                    if(success) {
                    	System.out.println("Expense deleted successfully");
                    }else {
                    	System.out.println("Failed to delete expense");
                    }}
                    catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid expense_id.");
                        sc.nextLine(); 
                    }
                    break;
                }

                case 5: {
                    try {
                        System.out.print("Enter amount: ");
                        double amount = sc.nextDouble();

                        System.out.print("Enter category_id: ");
                        int category_id = sc.nextInt();

                        sc.nextLine(); 
                        System.out.print("Enter date (yyyy-MM-dd): ");
                        String dateStr = sc.nextLine();
                        java.sql.Date date = new java.sql.Date(sdf.parse(dateStr).getTime());

                        System.out.print("Enter description: ");
                        String description = sc.nextLine();

                        System.out.print("Enter user_id: ");
                        int user_id = sc.nextInt();

                        System.out.print("Enter expense_id to update: ");
                        int expense_id = sc.nextInt();

                        Expenses expense = new Expenses(expense_id, amount, user_id, category_id, date, description);
                        boolean success = repository.updateExpense(user_id, expense);
                        if(success) {
                        	System.out.println("Expense updated successfully");
                        }
                        else {
                        	System.out.println("Failed to update expense");
                        }
                        
                    } catch (ParseException pe) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;
                }

                case 6:
                    

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
