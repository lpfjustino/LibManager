package books;


public class Item {
	private Book book;
	private int quantity;
	
	public Item (Book b, int qty) {
		setBook(b);
		setQuantity(qty);
	}

	int getQuantity() {
		return quantity;
	}

	void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	Book getBook() {
		return book;
	}

	void setBook(Book book) {
		this.book = book;
	}
}
