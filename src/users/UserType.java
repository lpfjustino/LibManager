package users;

public enum UserType {
    STUDENT, TEACHER, COMMUNITY;
    
    public static String getTypeRepresentation(UserType type) {
        String representation = "";
        switch(type) {
            case STUDENT:
                representation = "Student";
                break;
            
            case TEACHER:
                representation = "Teacher";
                break;
            
            case COMMUNITY:
                representation = "Community";
                break;
        }
        
        return representation;
    }
    
    public static UserType getTypeFromText(String representation) {
        UserType type = null;
        switch(representation) {
            case "Student":
                type = STUDENT;
                break;
            
            case "Teacher":
                type = TEACHER;
                break;
            
            case "Community":
                type = COMMUNITY;
                break;
        }
        
        return type;
    }
}
