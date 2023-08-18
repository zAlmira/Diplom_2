package site.nomoreparties.stellarburgers.model;

public class LoginResponseUser {
    private String email;
    private String name;

    public LoginResponseUser(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public LoginResponseUser() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
