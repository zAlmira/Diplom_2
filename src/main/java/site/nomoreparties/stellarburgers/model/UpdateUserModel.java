package site.nomoreparties.stellarburgers.model;

public class UpdateUserModel {
    private String email;
    private String name;


    public UpdateUserModel(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public UpdateUserModel() {
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
