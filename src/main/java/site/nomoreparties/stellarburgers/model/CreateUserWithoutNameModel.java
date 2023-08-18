package site.nomoreparties.stellarburgers.model;

public class CreateUserWithoutNameModel {
    private String password;
    private String email;

    public CreateUserWithoutNameModel(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public CreateUserWithoutNameModel() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
