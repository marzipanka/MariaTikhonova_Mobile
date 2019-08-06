package enums;

public enum PropertyFile {

    WEB(".\\webtest.properties"),
    NATIVE(".\\nativetest.properties");

    private final String item;

    PropertyFile(String item){
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}
