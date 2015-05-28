package books;

public enum BookType {
    TEXT, GENERAL;
    
    public static String getTypeRepresentation(BookType type) {
        if(type == TEXT) return "text";
        else return "general";
    }
    
    public static BookType getTypeFromText(String text) {
        if(text.equals("text")) return TEXT;
        else return GENERAL;
    }
}
