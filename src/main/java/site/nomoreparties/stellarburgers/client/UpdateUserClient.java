package site.nomoreparties.stellarburgers.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.model.UpdateUserModel;

import static io.restassured.RestAssured.given;

public class UpdateUserClient extends BaseClient {
    private final String URL;

    public UpdateUserClient() {
        super();
        URL = BASE_URI + "api/auth/user";
    }

    @Step("Обновление данных о пользователе с авторизацией")
    public Response doUpdateUserWithAuth(String accessToken, UpdateUserModel updateUserModel) {
        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(accessToken)
                .and()
                .body(updateUserModel)
                .patch(URL);
    }

    @Step("Обновление данных о пользователе без авторизации")
    public Response doUpdateUserWithoutAuth(UpdateUserModel updateUserModel) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(updateUserModel)
                .patch(URL);
    }
}
