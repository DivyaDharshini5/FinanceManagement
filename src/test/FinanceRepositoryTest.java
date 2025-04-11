package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import Model.Expenses;
import Model.User;
import dao.FinanceRepositoryImpl;
import exception.ExpenseNotFoundException;
import exception.UserNotFoundException;

class FinanceRepositoryTest {
	 FinanceRepositoryImpl repository = new FinanceRepositoryImpl();
@Test
@Disabled
	void testuseradded() {
		User user = new User(201,"kavya","kav788","kaviya@gmail.com");
		boolean result=repository.createUser(user);
		assertTrue(result);
	}
	@Test
	@Disabled
	void testexpenseadded() {
		Expenses expense= new Expenses(401,90000,104,6,Date.valueOf("2024-09-17"),"Monthly rent");
		boolean result=repository.createExpense(expense);
		assertTrue(result);
	}
	@Test
	@Disabled
	void testuserdeleted() throws UserNotFoundException {
		
//		User user = new User(204,"kavya","kav788","kaviya@gmail.com");
//		repository.createUser(user);
		boolean result=repository.deleteUser(201);
		assertTrue(result);
		
	}
	@Disabled
void testexpensedeleted() throws ExpenseNotFoundException {
	Expenses expense= new Expenses(404,90000,106,6,Date.valueOf("2024-09-17"),"Monthly rent");
	repository.createExpense(expense);
		boolean result=repository.deleteExpense(404);
		assertTrue(result);
	}
	@Test
	void testexpenseupdated() {
		Expenses expense = new Expenses(401, 104, 9500, 6, Date.valueOf("2025-05-07"), "Updated Rent");
		boolean result = repository.updateExpense(104, expense);
		assertTrue(result, "Update should return true if expense exists and is updated");
		
	}

}
