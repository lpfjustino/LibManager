package users;
import books.Book;


public class Student extends User {
    public Student(int id, String name) {
        this.name = name;
        this.id = id;

        LOANS_LIMIT = 4;
        LOAN_TERM = 15;
    }

    @Override
    public void borrow(Book book) {

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
}
