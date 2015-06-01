package users;
import books.Book;


public class Community extends User {
    public Community(int id, String name) {
        this.name = name;
        this.id = id;

        LOANS_LIMIT = 2;
        LOAN_TERM = 15;
    } 

    @Override
    public void borrow(Book book) {
        // TODO Auto-generated method stub
    }

    @Override
    public int getLoansLimit() {
        return LOANS_LIMIT;
    }

    @Override
    public int getLoanTerm() {
        return LOAN_TERM;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getID() {
        return id;
    }
}
