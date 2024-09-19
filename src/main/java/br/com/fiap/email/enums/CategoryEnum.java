package br.com.fiap.email.enums;

public enum CategoryEnum {
    SOCIAL("social", 1),
    WORK("work",2),
    SPAM("spam",3),
    URGENT("urgent",4);


    private final int code;
    private final String description;

    CategoryEnum(String description, int code){
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
