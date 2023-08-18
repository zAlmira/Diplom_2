package site.nomoreparties.stellarburgers.model;

public class CreateUserResponseModel {
    private String accessToken;

    public CreateUserResponseModel(String accessToken) {
        this.accessToken = accessToken;
    }

    public CreateUserResponseModel() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String token) {
        this.accessToken = token;
    }

}
