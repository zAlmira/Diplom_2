package site.nomoreparties.stellarburgers.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.model.CreateUserModel;
import site.nomoreparties.stellarburgers.model.CreateUserWithoutNameModel;

import static io.restassured.RestAssured.given;

public class CreateUserClient extends BaseClient {
    private final String URL;

    public CreateUserClient() {
        super();
        URL = BASE_URI + "api/auth/register";
    }

    @Step("Создание пользователя")
    public Response doCreateUser(CreateUserModel createUserModel) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(createUserModel)
                .when()
                .post(URL);
    }

    @Step("Создание пользователя без имени")
    public Response doCreateUserWithoutName(CreateUserWithoutNameModel createUserWithoutNameModel) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(createUserWithoutNameModel)
                .when()
                .post(URL);
    }
}
