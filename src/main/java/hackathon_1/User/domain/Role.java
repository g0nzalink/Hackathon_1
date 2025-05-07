package hackathon_1.User.domain;


public enum Role {
    SPARKY_ADMIN,
    COMPANY_ADMIN,
    USER;

    public String getAuthority() {
        return "ROLE_" + name();
    }
}