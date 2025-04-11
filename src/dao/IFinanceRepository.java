package dao;
import java.util.List;
import Model.Expenses;
import Model.User;
import exception.ExpenseNotFoundException;
import exception.UserNotFoundException;

public interface IFinanceRepository {
boolean createUser(User user) ;
boolean createExpense(Expenses expense);
boolean deleteUser(int user_id) throws UserNotFoundException ;
boolean deleteExpense(int expense_id) throws ExpenseNotFoundException;
List<Expenses> getAllExpenses(int user_id);
boolean updateExpense(int user_id,Expenses expense);
}
