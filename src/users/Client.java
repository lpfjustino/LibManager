package users;
import books.Book;


public interface Client {
	
	public void borrow (Book book);
	
	public int getLoansLimit ();
	
	public int getLoanTerm ();
}
