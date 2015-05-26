package users;

public abstract class User implements Client {
    int id;
    public String name;
    public int LOANS_LIMIT;  // Limite de livros locados
    public int LOAN_TERM;    // Limite de dias de permanência
}
