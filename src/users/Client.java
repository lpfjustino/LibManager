package users;
import books.Book;

public interface Client {
	public abstract void borrow(Book book);
        public String getName();
	public int getLoansLimit();
	public int getLoanTerm();
}
