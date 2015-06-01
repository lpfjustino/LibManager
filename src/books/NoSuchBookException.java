package books;

public class NoSuchBookException extends RuntimeException {

    public NoSuchBookException() {
    }

    public NoSuchBookException(Book book) {
        super("The book " + book + " doesn't exit");
    }
}
