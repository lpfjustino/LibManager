package users;
import books.Book;


public class Teacher extends User {
    public Teacher(int id, String name) {
        this.name = name;
        this.id = id;

        LOANS_LIMIT = 6;
        LOAN_TERM = 60;
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
