package site.nomoreparties.stellarburgers.model;

public class DeleteModel {
    private String authorization;

    public DeleteModel(String authorization) {
        this.authorization = authorization;
    }

    public DeleteModel() {
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
