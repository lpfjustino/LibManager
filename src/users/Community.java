package users;
import books.Book;


public class Community extends User {
	private final String name;
	
	public static final int LOANS_LIMIT = 2;	// limite de livros locados
	public static final int LOAN_TERM = 15;	// limite de dias de permanencia
	
	
	public Community(int id, String name) {
		this.name = name;
		this.id = id;
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
	
}
