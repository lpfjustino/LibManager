package management;
import java.util.Date;
import java.util.List;

import users.User;
import books.Item;


public class Library {
	private List<User> users;
	private List<User> suspendedUsers;
	private List<Item> collection;
	private List<Loan> loans;
	
	private Date date;	// dia atual da aplicacao
	
	public void addItem (Item item) {
		// se um item deste mesmo nome existe, só soma uma unidade
	}
	
	public void registerUser (User user) {
		
	}
	
	public void listCollection () {
		
	}
	
	public void listLoans () {
		
	}
	
	
	public void lend (Item item) {
		// adiciona o livro no borrowedBooks
	}
	
	public boolean isSuspended (User user) {
		// ve se o usuario esta na lista de suspensos
		return false;
		
	}
	
	public void timeSuspended () {
			
	}
	
	public void suspendUser (User user) {
		// adiciona o usuario na lista de suspensos
	}

	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getSuspendedUsers() {
		return suspendedUsers;
	}

	public void setSuspendedUsers(List<User> suspendedUsers) {
		this.suspendedUsers = suspendedUsers;
	}

	public List<Item> getCollection() {
		return collection;
	}

	public void setCollection(List<Item> collection) {
		this.collection = collection;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
}
