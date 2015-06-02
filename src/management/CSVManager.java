package management;

import books.Book;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import users.Community;
import users.Student;
import users.Teacher;
import users.User;
import users.UserType;
import books.BookType;
import java.util.Calendar;
import java.util.Map;

public class CSVManager {
    private static Library library;
    
    public CSVManager(Library lib) {
        library = lib;
    }
    
    // Verifica se o arquivo existe, e o cria caso não exista
    private static void assertFileExistence(File file) throws IOException {
        if(!file.exists())
            file.createNewFile();
    }
    
    // Preenche todos os campos da biblioteca
    public void populateLibrary() throws IOException {
        populateUsers();
        populateSuspendedUsers();
        populateCollection();
        populateLoans();
    }

    // Preenche a lista de usuários da biblioteca com todos os usuários
    private void populateUsers() throws IOException {
        fetchStudents();
        fetchTeachers();
        fetchCommunities();
    }
    
    // Preenche a lista de usuários da biblioteca com os alunos já
    // existentes no arquivo
    private void fetchStudents() throws IOException {
        File studentsCSV = new File("students.csv");
        assertFileExistence(studentsCSV);
        
        try (CSVReader csvReader = new CSVReader(new FileReader(studentsCSV))) {
            String[] row;
            while((row = csvReader.readNext()) != null) {
                Student student = new Student(Integer.valueOf(row[0]), row[1]);
                library.getUsers().add(student);
            }
            
            csvReader.close();
        }
    }
    
    // Preenche a lista de usuários da biblioteca com os professores já
    // existentes no arquivo
    private void fetchTeachers() throws IOException {
        File teachersCSV = new File("teachers.csv");
        assertFileExistence(teachersCSV);
        
        try (CSVReader csvReader = new CSVReader(new FileReader(teachersCSV))) {
            String[] row = null;
            while((row = csvReader.readNext()) != null) {
                Teacher teacher = new Teacher(Integer.valueOf(row[0]), row[1]);
                library.getUsers().add(teacher);
            }
            
            csvReader.close();
        }
    }
    
    // Preenche a lista de usuários da biblioteca com as comunidades já
    // existentes no arquivo
    private void fetchCommunities() throws IOException {
        File communitiesCSV = new File("communities.csv");
        assertFileExistence(communitiesCSV);
        
        try (CSVReader csvReader = new CSVReader(
                new FileReader(communitiesCSV), ',')) {
            String[] row = null;
            while((row = csvReader.readNext()) != null) {
                Community community = new Community(Integer.valueOf(row[0]), row[1]);
                library.getUsers().add(community);
            }
            
            csvReader.close();
        }
    }

    // Recupera os alunos suspensos presentes no arquivo
    private void populateSuspendedUsers() throws IOException {
        File suspendedCSV = new File("suspended.csv");
        assertFileExistence(suspendedCSV);
        
        try (CSVReader csvReader = new CSVReader(
                new FileReader(suspendedCSV), ',')) {
            String[] row = null;
            while((row = csvReader.readNext()) != null) {
                try {
                    // Configura o usuário e a data
                    User user = userFromCSV(row);
                    String dateString = row[2]+"/"+row[3]+"/"+row[4];
                    Date date = new SimpleDateFormat("dd/MM/yyyy")
                            .parse(dateString);
                    library.getSuspendedUsers().put(user, date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
            csvReader.close();
        }
    }
    
    // Recupera os itens da coleção presentes no arquivo
    private void populateCollection() throws IOException {
        File collectionCSV = new File("collection.csv");
        assertFileExistence(collectionCSV);
        
        CSVReader reader = new CSVReader(new FileReader(collectionCSV));
        String[] nextLine;
        
        while ((nextLine = reader.readNext()) != null) {
            Map<Book, Integer> collection = library.getCollection();
            collectionAddFromCSV(collection, nextLine);
        }
        
        reader.close();
    }

    // Recupera os empréstimos presentes no arquivo
    private void populateLoans() throws IOException {
        File loansCSV = new File("loans.csv");
        assertFileExistence(loansCSV);
        
        CSVReader reader = new CSVReader(new FileReader(loansCSV));
        String[] nextLine;
        
        while ((nextLine = reader.readNext()) != null) {
            Loan loan = loanFromCSV(nextLine);
            library.getLoans().add(loan);
        }
        
        reader.close();
    }
    
    // Guarda em um registro o último acesso feito
    public static void registerLastAccess(Date date) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("last_access.csv"));
        
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(date);
        int day = localCalendar.get(Calendar.DATE);
        int month = localCalendar.get(Calendar.MONTH) + 1;
        int year = localCalendar.get(Calendar.YEAR);
        
        String[] content =  (day + "," +
                month + "," +
                year).split(",");
        
        writer.writeNext(content);
        writer.close();
    }
    
    
    ////////////////////////////////////////////////////////
    // Funções para gerenciar diretamente os arquivos CSV //
    ////////////////////////////////////////////////////////
    
    // Adiciona no arquivo de registros o usuário de um dado tipo
    public static void includeUser(User user, UserType type) throws IOException {
        switch(type) {
            case STUDENT:
                includeStudent(user);
                break;
            case TEACHER:
                includeTeacher(user);
                break;
            case COMMUNITY:
                includeCommunity(user);
                break;
        }
    }
    
    // Adiciona no arquivo de registros o usuário passado por parâmetro
    public static void includeStudent(User user) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("students.csv", true));
        String[] content = userRowFormat(user);
        writer.writeNext(content);
        writer.close();
    }
    
    // Adiciona no arquivo de registros o usuário passado por parâmetro
    public static void includeTeacher(User user) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("teachers.csv", true));
        String[] content = userRowFormat(user);
        writer.writeNext(content);
        writer.close();
    }
    
    // Adiciona no arquivo de registros o usuário passado por parâmetro
    public static void includeCommunity(User user) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("communities.csv", true));
        String[] content = userRowFormat(user);
        writer.writeNext(content);
        writer.close();
    }
    
    // Adiciona no arquivo de registros o livro passado por parâmetro
    public void includeInCollection(Book book, int qty) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("collection.csv", true));
        String[] content = bookRowFormat(book, qty);
        writer.writeNext(content);
        writer.close();
    }
    
    // Substitui o registro do livro passado por parâmetro por um com a nova quantidade
    public void updateCollection(Book book, int newQuantity) throws IOException {
        File file = new File("collection.csv");
        CSVReader reader = new CSVReader(new FileReader(file));
        String[] nextLine;
        
        // Percorre cada linha da coleção até encontrar o livro dado
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine[0].matches(String.valueOf(book.getId()))) {
                // Apaga o antigo arquivo e o reescreve todos os itens
                CSVWriter writer = new CSVWriter(new FileWriter(file));

                library.getCollection().entrySet()
                        .stream()
                        .forEach(
                            (entry) -> {
                                String[] content = bookRowFormat(
                                        entry.getKey(),
                                        newQuantity
                                        );
                                writer.writeNext(content);
                            }
                        );
                writer.close();
            }
        }
    }
    
    // Adiciona no arquivo de registros o empréstimo passado por parâmetro
    public static void includeLoan(Loan loan) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("loans.csv", true));
        String[] content = loanRowFormat(loan);
        writer.writeNext(content);
        writer.close();
    }
    
    // Insere o usuário na lista de usuários suspensos
    public static void includeSuspension(User user, Date date) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("suspended.csv", true));
        String[] content = suspensionRowFormat(user, date);
        writer.writeNext(content);
        writer.close();
    }
    
    // Remove a linha do empréstimo do arquivo de registros
    public static void returnBook() throws IOException {
        File file = new File("loans.csv");

        // Apaga o antigo arquivo e o reescreve todos os itens
        CSVWriter writer = new CSVWriter(new FileWriter(file));

        library.getLoans()
                .stream()
                .forEach(
                    (entry) -> {
                        String[] content = loanRowFormat(entry);
                        writer.writeNext(content);
                    }
                );
        writer.close();
    }
    
    ////////////////////////
    // Funções auxiliares //
    ////////////////////////
    
    // Verifica se o usuário encontra-se em um dado arquivo de registros
    private static boolean containsUser(File file, int id) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(file));
        String [] nextLine;
        boolean found = false;
        
        // Percorre o arquivo file até encontrar o usuário
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine[0].equals(String.valueOf(id))) found = true;
        }
        
        return found;
    }
    
    // Retorna a string que corresponde a um registro no arquivo
    private static String[] bookRowFormat(Book book, int qty) {
        return (book.getId() + "," + book.getTitle() + ","
                + book.getAuthor() + ","
                + BookType.getTypeRepresentation(book.getType()) + "," +
                String.valueOf(qty)).split(",");
    }
    
    // Retorna a string que corresponde a um registro no arquivo
    private static String[] userRowFormat(User user) {
        return (user.id + "," +
                user.name).split(",");
    }
    
    // Retorna a string que corresponde a um registro no arquivo
    private static String[] loanRowFormat(Loan loan) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(loan.getExpirationDate());
        int day = localCalendar.get(Calendar.DATE);
        int month = localCalendar.get(Calendar.MONTH) + 1;
        int year = localCalendar.get(Calendar.YEAR);
        
        return (loan.getBook().getId() + "," +
                loan.getBook().getTitle()+ "," +
                loan.getBook().getAuthor()+ "," +
                BookType.getTypeRepresentation(loan.getBook().getType())+ "," +
                loan.getBorrower().id + "," +
                loan.getBorrower().getName() + "," +
                day + "," +
                month + "," +
                year).split(",");
    }
    
    // Retorna a string que corresponde a uma suspensão no arquivo
    private static String[] suspensionRowFormat(User user, Date date) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(date);
        int day = localCalendar.get(Calendar.DATE);
        int month = localCalendar.get(Calendar.MONTH) + 1;
        int year = localCalendar.get(Calendar.YEAR);
        
        return (user.id + "," +
                user.name + "," +
                day + "," +
                month + "," +
                year).split(",");
    }
    
    // Verifica qual é o tipo do usuário e retorna um objeto do tipo apropriado
    private static User buildUser(int id, String name) throws IOException {
        File studentsCsv = new File("students.csv");
        File teachersCsv = new File("teachers.csv");
        File communitiesCsv = new File("communities.csv");
        
        if(containsUser(studentsCsv, id)) return new Student(id, name);
        if(containsUser(teachersCsv, id)) return new Teacher(id, name);
        if(containsUser(communitiesCsv, id)) return new Community(id, name);
        else return null;
    }
    
    // Retorna a string que corresponde a um usuario
    private static User userFromCSV(String[] row) throws IOException {
        return buildUser(Integer.valueOf(row[0]), row[1]);
    }
    
    // Retorna a string que corresponde a um usuario
    private static Loan loanFromCSV(String[] row) throws IOException {
        Loan loan = new Loan();
        loan.setBook(bookFromCSV(row));
        loan.setBorrower(borrowerFromCSV(row));
        loan.setExpirationDate(expirationDateFromCSV(row));
        
        return loan;
    }
    
    // Retorna o livro correspondente a um registro no arquivo CSV dos empréstimos
    private static Book bookFromCSV(String[] row) {
        Book book = new Book(Integer.valueOf(row[0]));
        book.setTitle(row[1]);
        book.setAuthor(row[2]);
        book.setType(BookType.getTypeFromText(row[3]));
        
        return book;
    }
    
    // Retorna o usuário correspondente a um registro no arquivo CSV dos empréstimos
    private static User borrowerFromCSV(String[] row) throws IOException {
        return buildUser(Integer.valueOf(row[4]), row[5]);
    }
    
    // Retorna a data de expiração correspondente a um registro no arquivo CSV dos empréstimos
    private static Date expirationDateFromCSV(String[] row) throws IOException {
        int day = Integer.valueOf(row[6]);
        int month = Integer.valueOf(row[7]);
        int year = Integer.valueOf(row[8]);
        String dateString = day + "/" + month + "/" + year;
        
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
            return date;
        } catch (ParseException ex) { }
        
        return null;
    }
    
    // Retorna a string que corresponde a um usuario
    private static void collectionAddFromCSV(Map<Book, Integer> collection, 
                                          String[] row) throws IOException {

        Book book = bookFromCSV(row);
        Integer quantity = Integer.valueOf(row[4]);
        
        collection.put(book, quantity);
    }
    
    // Retorna a data do último acesso feito à biblioteca
    public static Date lastAccessFromCSV() throws IOException {
        File loansCSV = new File("last_access.csv");
        
        // Caso seja o primeiro acesso, cria-se uma data fictícia tal que um
        // acesso anterior não possa existir
        if(!loansCSV.exists()) registerLastAccess(new Date(0));
        
        CSVReader reader = new CSVReader(new FileReader(loansCSV));
        String[] row = reader.readNext();
        
        int day = Integer.valueOf(row[0]);
        int month = Integer.valueOf(row[1]);
        int year = Integer.valueOf(row[2]);
        String dateString = day + "/" + month + "/" + year;
        
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
            return date;
        } catch (ParseException ex) { }
        
        return null;
    }
    
}
