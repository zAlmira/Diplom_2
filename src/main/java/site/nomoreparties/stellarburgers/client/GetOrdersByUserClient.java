package site.nomoreparties.stellarburgers.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetOrdersByUserClient extends BaseClient {
    private final String URL;

    public GetOrdersByUserClient() {
        super();
        URL = BASE_URI + "api/orders";
    }

    @Step("Получение заказов конкретного пользователя")
    public Response getOrdersByUserWithAuth(String accessToken) {
        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(accessToken)
                .when()
                .get(URL);
    }

    @Step("Получение заказов конкретного пользователя")
    public Response getOrdersByUserWithoutAuth() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(URL);
    }
}
