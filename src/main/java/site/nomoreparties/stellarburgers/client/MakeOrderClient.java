package site.nomoreparties.stellarburgers.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.model.MakeOrderModel;

import static io.restassured.RestAssured.given;

public class MakeOrderClient extends BaseClient {
    private final String URL;

    public MakeOrderClient() {
        super();
        URL = BASE_URI + "api/orders";
    }

    @Step("Создание заказа с авторизацией")
    public Response doCreateOrderWithAuth(String accessToken, MakeOrderModel makeOrderModel) {
        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(accessToken)
                .and()
                .body(makeOrderModel)
                .when()
                .post(URL);
    }

    @Step("Создание заказа без авторизации")
    public Response doCreateOrderWithoutAuth(MakeOrderModel makeOrderModel) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(makeOrderModel)
                .when()
                .post(URL);
    }
}
