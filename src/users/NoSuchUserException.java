package users;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException() {
    }

    public NoSuchUserException(User user) {
        super("The user " + user + " doesn't exit");
    }
}
