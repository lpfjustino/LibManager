package management;
import books.Book;
import java.util.Date;
import java.util.List;
import users.User;
import java.util.Map;


public class Library {
    private List<User> users;
    private Map<User, Date> suspendedUsers;
    private Map<Book, Integer> collection;
    private List<Loan> loans;

    private Date date;      // dia atual da aplicacao

    public void addToCollection(Book book, int quantity) {
        if(collection.containsKey(book)) {
            int oldQuantity = collection.get(book);
            collection.replace(book, oldQuantity+1);
        } else {
            collection.put(book, quantity);
        }
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public void listCollection() {

    }

    public void listLoans() {

    }

    public void lend (Book book, User user){
        if(canLend(user))
            user.borrow(book);
    }

    public boolean canLend(User user) {
        return (currentlyBorrowed(user) < user.LOANS_LIMIT &&
                !isSuspended(user));
    }

    public int currentlyBorrowed(User user) {
        int borrowedCount = (int) loans
                .stream()
                .filter(s -> s.getBorrower().equals(user))
                .count();
        return borrowedCount;
    }

    public boolean isSuspended (User user) {
        return suspendedUsers.containsKey(user);
    }

    public Date timeSuspended (User user) {
        return suspendedUsers.get(user);
    }

    public void suspendUser (User user, Date date) {
        if(!isSuspended(user))
            suspendedUsers.put(user, date);
    }

    public List<User> getUsers() {
        return users;
    }
    
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Map<User, Date> getSuspendedUsers() {
        return suspendedUsers;
    }

    public void setSuspendedUsers(Map<User, Date> suspendedUsers) {
        this.suspendedUsers = suspendedUsers;
    }

    public Map<Book, Integer> getCollection() {
        return collection;
    }

    public void setCollection(Map<Book, Integer> collection) {
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
