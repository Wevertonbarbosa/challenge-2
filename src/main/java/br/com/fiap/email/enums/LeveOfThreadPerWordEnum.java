package br.com.fiap.email.enums;

public enum LeveOfThreadPerWordEnum {
    DANGEROUS(100),
    HIGH(70),
    NORMAL(40);

    private final int points;


    LeveOfThreadPerWordEnum(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
