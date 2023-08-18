package site.nomoreparties.stellarburgers.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.model.LoginModel;

import static io.restassured.RestAssured.given;

public class LoginClient extends BaseClient {
    private final String URL;

    public LoginClient() {
        super();
        URL = BASE_URI + "api/auth/login";
    }

    @Step("Авторизация пользователя")
    public Response doLogin(LoginModel loginModel) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(loginModel)
                .when()
                .post(URL);
    }
}
