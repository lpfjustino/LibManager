package books;

public enum BookType {
    TEXT, GENERAL;
    
    public static String getTypeRepresentation(BookType type) {
        if(type == TEXT) return "Text";
        else return "General";
    }
    
    public static BookType getTypeFromText(String text) {
        if(text.equals("Text")) return TEXT;
        else return GENERAL;
    }
    
    @Override
    public String toString() {
        return getTypeRepresentation(this);
    }
}
