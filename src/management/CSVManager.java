package management;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import users.Community;
import users.Student;
import users.Teacher;
import users.User;
import users.UserType;

public class CSVManager {
    private static Library library;
    
    public CSVManager(Library lib) {
        library = lib;
    }
    
    private static void assertFileExistence(File file) throws IOException {
        if(!file.exists())
            file.createNewFile();
    }
    
    public static void populateLibrary() throws IOException {
        populateUsers();
        pupulateSuspendedUsers();
        populateCollection();
        populateLoans();
    }

    private static void populateUsers() throws IOException {
        fetchStudents();
        fetchTeachers();
        fetchCommunities();
    }
    
    private static void fetchStudents() throws IOException {
        File studentsCSV = new File("students.csv");
        assertFileExistence(studentsCSV);
        
        try (CSVReader csvReader = new CSVReader(new FileReader(studentsCSV))) {
            String[] row = null;
            while((row = csvReader.readNext()) != null) {
                Student student = new Student(Integer.valueOf(row[0]), row[1]);
                library.getUsers().add(student);
            }
            
            csvReader.close();
        }
    }
    
    private static void fetchTeachers() throws IOException {
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
    
    private static void fetchCommunities() throws IOException {
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

    private static void pupulateSuspendedUsers() throws IOException {
        File suspendedCSV = new File("suspended.csv");
        assertFileExistence(suspendedCSV);
    }
    
    private static void populateCollection() throws IOException {
        File collectionCSV = new File("collection.csv");
        assertFileExistence(collectionCSV);
    }

    private static void populateLoans() throws IOException {
        File loansCSV = new File("loans.csv");
        assertFileExistence(loansCSV);
    }
    
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
    
    public static void includeStudent(User user) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("students.csv"));
        String[] content = (user.name + "," + user.LOANS_LIMIT + "," +
                user.LOAN_TERM).split(",");
        writer.writeNext(content);
        writer.close();
    }
    
    public static void includeTeacher(User user) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("teachers.csv"));
        String[] content = (user.name + "," + user.LOANS_LIMIT + "," +
                user.LOAN_TERM).split(",");
        writer.writeNext(content);
        writer.close();
    }
    
    public static void includeCommunity(User user) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("communities.csv"));
        String[] content = (user.name + "," + user.LOANS_LIMIT + "," +
                user.LOAN_TERM).split(",");
        writer.writeNext(content);
        writer.close();
    }
}
