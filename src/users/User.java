package users;

public abstract class User implements Client {
    int id;
    public int LOANS_LIMIT;  // limite de livros locados
    public int LOAN_TERM;    // limite de dias de permanencia
}
