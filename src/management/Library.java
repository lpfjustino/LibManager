package management;
import books.Book;
import books.BookType;
import books.NoSuchBookException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import users.User;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import javafx.scene.control.Alert;
import users.Community;
import users.NoSuchUserException;
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

    // Adiciona ou atualiza o livro no acervo e no arquivo de registros
    public void addToCollection(Book book, int quantity) throws IOException {
        // Aumenta a quantidade de livros, caso este já esteja contido no acervo
        if(collection.containsKey(book)) {
            int oldQuantity = collection.get(book);
            collection.replace(book, oldQuantity + quantity);
            database.updateCollection(book, oldQuantity + quantity);
        }
        // Caso contrário, insere-o no acervo
        else {
            collection.put(book, quantity);
            database.includeInCollection(book, quantity);
        }
    }

    // Insere o usuário na lista de usuários e no arquivo de registros
    public void registerUser(User user, UserType type) throws IOException {
        users.add(user);
        CSVManager.includeUser(user, type);
    }

    // Verifica e empresta o livro para o usuário caso ele possa emprestá-lo,
    // inserindo-o no arquivo de registros 
    //
    // Retorna true se o usuário conseguiu emprestar o livro
    public boolean lend (Loan loan) throws IOException{
        if(canLend(loan.getBook(), loan.getBorrower())) {
            loans.add(loan);
            CSVManager.includeLoan(loan);
            return true;
        } else {
            return false;
        }
    }

    // Verifica se o usuário pode emprestar o livro
    public boolean canLend(Book book, User user) {
        return (currentlyBorrowed(user) < user.LOANS_LIMIT &&
                !isSuspended(user) &&
                isEligible(book, user) &&
                !isCurrentlyBorrowed(book, user));
    }
    
    // Retorna true se o usuário pode emprestar um determinado tipo de livro
    public boolean isEligible(Book book, User user) {
        if(user instanceof Community && 
                book.getType() == BookType.TEXT)
            return false;
        else
            return true;
    }

    // Retorna quantos livros o usuário já emprestou
    public int currentlyBorrowed(User user) {
        int borrowedCount = (int) loans
                .stream()
                .filter(s -> s.getBorrower().equals(user))
                .count();
        return borrowedCount;
    }
    
    // Retorna se o usuário já não emprestou uma cópia de um dado livro
    public boolean isCurrentlyBorrowed(Book book, User borrower) {
        long loanCount = loans
                .stream()
                .filter(
                    (Loan loan) -> loan.getBook().equals(book)
                            && loan.getBorrower().equals(borrower)
                )
                .count();
        
        return loanCount > 0;
    }
    
    // Trata da devolução do empréstimo de um determinado livro
    public boolean returnBook(Book book, User borrower) throws IOException {
        Optional<Loan> newReturn = loans
                .stream()
                .filter(
                    (Loan loan) -> loan.getBook().equals(book)
                            && loan.getBorrower().equals(borrower)
                )
                .findFirst();
        
        if(newReturn.isPresent()){
            Loan foundReturn = newReturn.get();
            loans.remove(foundReturn);
        }
        
        CSVManager.returnBook();
        return true;
    }

    // Verifica se o usuário está suspenso
    public boolean isSuspended (User user) {
        return suspendedUsers.containsKey(user);
    }

    // Retorna a data que corresponde ao tempo pelo qual o usuário está suspenso
    public Date timeSuspended (User user) {
        return suspendedUsers.get(user);
    }

    // Suspende o usuário, inserindo-o na lista de suspensos e insere-o no
    // arquivo de registros
    public void suspendUser (User user, Date date) throws IOException {
        if(!isSuspended(user))
            suspendedUsers.put(user, date);
        
        database.includeSuspension(user, date);
    }

    // Retorna um usuário dado seu identificador
    public User getUser (int id) throws NoSuchUserException {
        Optional<User> result = users
                .stream()
                .filter( user -> user.id == id )
                .findAny();
        
        if(result.isPresent()) return result.get();
        else throw new NoSuchUserException();
    }
    
    // Retorna um livro dado seu identificador
    public Book getBook (int id) throws NoSuchBookException {
        Optional<Entry<Book, Integer>> resultEntry = collection.entrySet()
                .stream()
                .filter(
                        (Entry<Book, Integer> entry) -> {
                            return entry.getKey().getId() == id;
                        }
                )
                .findAny();
        
        if(resultEntry.isPresent()) {
            Entry<Book,Integer> resultBook = resultEntry.get();
            return resultBook.getKey();
        }
        
        else throw new NoSuchBookException();
    }
    
    // Mostra uma caixa de texto exibindo alguma mensagem
    public void showDialog(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }
    
    // Retorna em número de dias que dista duas datas a serem comparadas
    public int daysSpan(Date d1, Date d2) {
        final long numberOfMSInADay = 1000*60*60*24;
        long span = Math.abs((d2.getTime() - d1.getTime()) / numberOfMSInADay);
        return (int) span;
    }
    
    // Retorna uma data que dista uma quantidade de dias de uma data base
    // passados por parâmetro
    public Date sumDays(Date base, int days) {
        final long numberOfMSInADay = 1000*60*60*24;
        long dayInMS = base.getTime() + days * numberOfMSInADay;
        return new Date(dayInMS);
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