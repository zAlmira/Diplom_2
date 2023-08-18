package site.nomoreparties.stellarburgers.model;

public class LoginResponseModel {
    private String accessToken;
    private String refreshToken;
    private String success;
    private LoginResponseUser user;

    public LoginResponseModel(String accessToken, String refreshToken, String success, LoginResponseUser user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.success = success;
        this.user = user;
    }

    public LoginResponseModel() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenWithoutBearer(String accessToken) {
        String[] accessTokenWithoutBearer = accessToken.split(" ");
        return accessTokenWithoutBearer[1];
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public LoginResponseUser getUser() {
        return user;
    }

    public void setUser(LoginResponseUser user) {
        this.user = user;
    }


}
