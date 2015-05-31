package management;
import books.Book;
import books.BookType;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import users.User;
import java.util.Map;
import java.util.Optional;
import users.UserType;

public class Library {
    private ArrayList<User> users;
    private HashMap<User, Date> suspendedUsers;
    private HashMap<Book, Integer> collection;
    private ArrayList<Loan> loans;
    private Date date;
    private CSVManager database;
    
    public Library (Date currentDate) throws IOException {
        users = new ArrayList<>();
        suspendedUsers = new HashMap<>();
        collection = new HashMap<>();
        loans = new ArrayList<>();
        database = new CSVManager(this);
        date = currentDate;

        database.populateLibrary();
    }

    public void addToCollection(Book book, int quantity) throws IOException {
        if(collection.containsKey(book)) {
            System.out.println("SUBST");
            int oldQuantity = collection.get(book);
            collection.replace(book, oldQuantity + quantity);
            database.updateCollection(book, oldQuantity + quantity);
        } else {
            collection.put(book, quantity);
            database.includeInCollection(book, quantity);
        }
    }

    public void registerUser(User user, UserType type) throws IOException {
        users.add(user);
        CSVManager.includeUser(user, type);
    }

    public void listUsers() {
        users
            .stream()
            .forEach(
                    (entry) -> {
                        System.out.println(String.valueOf(entry.id)
                                + " " + entry.name);
                    }
            );
    }
    
    public void listCollection() {
        collection.entrySet()
                .stream()
                .forEach((entry) -> {
                    System.out.println("Id: " + entry.getKey().getId() +
                        "\nTitle: " + entry.getKey().getTitle() +
                        "\nAuthor: " + entry.getKey().getAuthor() +
                        "\nType: " + BookType.getTypeRepresentation(entry.getKey().getType()) +
                        "\nQty: " + entry.getValue());
                });
    }

    public void listLoans() {
        loans
            .stream()
            .forEach((loan) -> {
                String expirationDate = new SimpleDateFormat("dd/MM/yyyy")
                        .format(loan.getExpirationDate());
                System.out.println("Book: " + loan.getBook().getTitle() +
                    "\nBorrower: " + loan.getBorrower().getName() +
                    "\nExpiration: " + expirationDate + "\n");
            });
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

    public void suspendUser (User user, Date date) throws IOException {
        if(!isSuspended(user))
            suspendedUsers.put(user, date);
        
        database.includeSuspension(user, date);
    }

    public User getUser (int id) {
        Optional<User> result = users
                .stream()
                .filter( user -> user.id == id )
                .findAny();
        
        if(result.isPresent()) return result.get();
        else return null;
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public Map<User, Date> getSuspendedUsers() {
        return suspendedUsers;
    }

    public void setSuspendedUsers(HashMap<User, Date> suspendedUsers) {
        this.suspendedUsers = suspendedUsers;
    }

    public Map<Book, Integer> getCollection() {
        return collection;
    }

    public void setCollection(HashMap<Book, Integer> collection) {
        this.collection = collection;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}