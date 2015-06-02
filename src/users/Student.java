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
    
    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        return true;
    }
    
    @Override
    public String toString() {
        return "("+ this.getID() + ") " + this.getName();
    }
}
