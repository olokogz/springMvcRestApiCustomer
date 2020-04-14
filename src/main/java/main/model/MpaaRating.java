package main.model;

public enum MpaaRating {
    G("G"),
    PG("PG"),
    R("R"),
    NC17("NC-17"),
    PG13("PG-13");

    MpaaRating(String realnName) {
        this.realName = realnName;
    }

    public String getRealName() {
        return realName;
    }

    private String realName;
}
