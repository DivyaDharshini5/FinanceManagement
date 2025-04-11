
package Model;
import java.sql.Date;
public class Expenses  {
private int expense_id;
double amount;
int user_id;
int category_id;
Date date;
String description;
public Expenses() {
	
}
public Expenses(int expense_id, double amount, int user_id, int category_id, Date date, String description) {
	super();
	this.expense_id = expense_id;
	this.amount = amount;
	this.user_id = user_id;
	this.category_id = category_id;
	this.date = date;
	this.description = description;
}
@Override
public String toString() {
	return "Expense [expense_id=" + expense_id + ", amount=" + amount + ", user_id=" + user_id + ", category_id="
			+ category_id + ", date=" + date + ", description=" + description + "]";
}
public int getExpense_id() {
	return expense_id;
}
public void setExpense_id(int expense_id) {
	this.expense_id = expense_id;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public int getCategory_id() {
	return category_id;
}
public void setCategory_id(int category_id) {
	this.category_id = category_id;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}



}
