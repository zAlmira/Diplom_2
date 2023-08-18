package site.nomoreparties.stellarburgers.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteUserClient extends BaseClient {
    private final String URL;

    public DeleteUserClient() {
        super();
        URL = BASE_URI + "api/auth/user";
    }

    @Step("Удаление пользователя")
    public Response doDeleteUser(String accessToken) {
        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(accessToken)
                .delete(URL);
    }


}
