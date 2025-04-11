package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Expenses;
import Model.User;
import dao.IFinanceRepository;
import exception.ExpenseNotFoundException;
import exception.UserNotFoundException;
import util.DBConnUtil;

public class FinanceRepositoryImpl implements IFinanceRepository {
	public boolean createUser(User user) {
		boolean isadded = false;
		try (Connection con = DBConnUtil.getConnection("src/db.properties")) {
			String query = "INSERT INTO users (user_id, username, password, email) VALUES (?, ?, ?, ?)";
			PreparedStatement stat = con.prepareStatement(query);
			stat.setInt(1, user.getUser_id());
			stat.setString(2, user.getUser_name());
			stat.setString(3, user.getPassword());
			stat.setString(4, user.getEmail());
			int rowsaffected = stat.executeUpdate();
			if (rowsaffected > 0) {
				isadded = true;
			}
		} catch (Exception e) {
			System.out.println("Error in createUser: " + e.getMessage());
		}
		return isadded;
	}

	
	public boolean createExpense(Expenses expense) {
		boolean isexpenseadded = false;
		try (Connection con = DBConnUtil.getConnection("src/db.properties")) {
			String query = "INSERT INTO expenses (expense_id, user_id, amount, category_id, date, description) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stat = con.prepareStatement(query);
			stat.setInt(1, expense.getExpense_id());
			stat.setInt(2, expense.getUser_id());
			stat.setDouble(3, expense.getAmount());
			stat.setInt(4, expense.getCategory_id());
			stat.setDate(5, expense.getDate());
			stat.setString(6, expense.getDescription());
			int rowsaffected = stat.executeUpdate();
			if (rowsaffected > 0) {
				isexpenseadded = true;
			}
		} catch (Exception e) {
			System.out.println("Error in createExpense: " + e.getMessage());
		}
		return isexpenseadded;
	}

	public boolean deleteUser(int user_id) throws UserNotFoundException{
		try (Connection con = DBConnUtil.getConnection("src/db.properties")) {
			String query = "DELETE FROM users WHERE user_id = ?";
			PreparedStatement stat = con.prepareStatement(query);
			stat.setInt(1, user_id);
			int c = stat.executeUpdate();
			if (c == 0) {
	            throw new UserNotFoundException("User with ID " + user_id + " not found.");
	        }
	        return true;
	    } catch (UserNotFoundException e) {
	        throw e;
	    } catch (Exception e) {
	        System.out.println("Error in deleteUser: " + e.getMessage());
	    }
	    return false;
	}
	public boolean deleteExpense(int expense_id) throws ExpenseNotFoundException {
		try (Connection con = DBConnUtil.getConnection("src/db.properties")) {
			String query = "DELETE FROM expenses WHERE expense_id = ?";
			PreparedStatement stat = con.prepareStatement(query);
			stat.setInt(1, expense_id);
			int c = stat.executeUpdate();
			if (c == 0) {
	            throw new ExpenseNotFoundException("Expense with ID " + expense_id + " not found.");
	        }
	        return true;
	    } catch (ExpenseNotFoundException e) {
	        throw e;
	    } catch (Exception e) {
	        System.out.println("Error in deleteExpense: " + e.getMessage());
	    }
	    return false;
	}

	
	public List<Expenses> getAllExpenses(int user_id) {
		List<Expenses> expenseList = new ArrayList<>();
		try (Connection con = DBConnUtil.getConnection("src/db.properties")) {
			String query = "SELECT * FROM expenses WHERE user_id = ?";
			PreparedStatement stat = con.prepareStatement(query);
			stat.setInt(1, user_id);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Expenses e = new Expenses();
				e.setExpense_id(rs.getInt("expense_id"));
				e.setUser_id(rs.getInt("user_id"));
				e.setAmount(rs.getDouble("amount"));
				e.setCategory_id(rs.getInt("category_id"));
				e.setDate(rs.getDate("date"));
				e.setDescription(rs.getString("description"));
				expenseList.add(e);
			}
		} catch (Exception e) {
			System.out.println("Error in getAllExpenses: " + e.getMessage());
		}
		return expenseList;
	}

	
	public boolean updateExpense(int user_id, Expenses expense) {
		boolean isUpdated = false;
		try (Connection con = DBConnUtil.getConnection("src/db.properties")) {
			String query = "UPDATE expenses SET amount = ?, category_id = ?, date = ?, description = ? WHERE user_id = ? AND expense_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDouble(1, expense.getAmount());
			ps.setInt(2, expense.getCategory_id());
			ps.setDate(3, expense.getDate());
			ps.setString(4, expense.getDescription());
			ps.setInt(5, user_id);
			ps.setInt(6, expense.getExpense_id());
			int rowsAffected = ps.executeUpdate();
			isUpdated = rowsAffected > 0;

		} catch (Exception e) {
			System.out.println("Error in updateExpense: " + e.getMessage());
		}
		return isUpdated;
	}
}
