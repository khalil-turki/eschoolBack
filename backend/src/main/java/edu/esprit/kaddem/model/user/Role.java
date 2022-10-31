package edu.esprit.kaddem.model.user;

public enum Role {
    ROLE_ADMIN(Values.ROLE_ADMIN),
    ROLE_PROFESSEUR(Values.ROLE_PROFESSEUR),
    ROLE_PARENT(Values.ROLE_PARENT),
    ROLE_ETUDIANT(Values.ROLE_ETUDIANT);

    private String value;

    Role(String string) {
        this.value = string;
    }

    public static class Values {
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String ROLE_PROFESSEUR = "ROLE_PROFESSEUR";
        public static final String ROLE_PARENT = "ROLE_PARENT";
        public static final String ROLE_ETUDIANT = "ROLE_ETUDIANT";
    }
}
