package edu.esprit.kaddem.model.user;

public enum Gender {
    MALE(Values.MALE),
    FEMALE(Values.FEMALE),
    UNSPECIFIED(Values.UNSPECIFIED);

    private String value;

    Gender(String string) {
        this.value = string;
    }

    public static class Values {
        public static final String MALE = "MALE";
        public static final String FEMALE = "FEMALE";
        public static final String UNSPECIFIED = "UNSPECIFIED";
    }
}
