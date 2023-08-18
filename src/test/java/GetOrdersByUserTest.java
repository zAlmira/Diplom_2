import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.client.GetOrdersByUserClient;
import site.nomoreparties.stellarburgers.client.LoginClient;
import site.nomoreparties.stellarburgers.client.MakeOrderClient;
import site.nomoreparties.stellarburgers.model.LoginModel;
import site.nomoreparties.stellarburgers.model.LoginResponseModel;
import site.nomoreparties.stellarburgers.model.MakeOrderModel;

import java.util.ArrayList;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class GetOrdersByUserTest extends BaseTest {
    ArrayList<String> ingredients = new ArrayList<>();

    @Before
    public void setUp() {
        createUser();
        ingredients.add("61c0c5a71d1f82001bdaaa70");
        ingredients.add("61c0c5a71d1f82001bdaaa73");
        LoginClient loginClient = new LoginClient();
        Response loginResponse = loginClient.doLogin(new LoginModel(EMAIL, PASSWORD));
        LoginResponseModel loginResponseModel = loginResponse.body().as(LoginResponseModel.class);
        MakeOrderClient makeOrderWithAuthClient = new MakeOrderClient();
        makeOrderWithAuthClient.doCreateOrderWithAuth(loginResponseModel.getAccessTokenWithoutBearer(loginResponseModel.getAccessToken()),
                new MakeOrderModel(ingredients));
    }

    @Test
    @DisplayName("Успешное получение заказов конкретного пользователя")
    public void doGetOrdersByUserWithAuth200() {
        LoginClient loginClient = new LoginClient();
        Response loginResponse = loginClient.doLogin(new LoginModel(EMAIL, PASSWORD));
        LoginResponseModel loginResponseModel = loginResponse.body().as(LoginResponseModel.class);
        GetOrdersByUserClient getOrdersByUserClient = new GetOrdersByUserClient();
        Response getOrdersByUserClientResponse = getOrdersByUserClient.getOrdersByUserWithAuth(loginResponseModel.getAccessTokenWithoutBearer(loginResponseModel.getAccessToken()));
        getOrdersByUserClientResponse.then().statusCode(200).and().assertThat().body("orders", notNullValue());
    }

    @Test
    @DisplayName("Получение заказов для неавторизованного пользователя")
    public void doGetOrdersByUserWithoutAuth200() {
        GetOrdersByUserClient getOrdersByUserClient = new GetOrdersByUserClient();
        Response getOrdersByUserClientResponse = getOrdersByUserClient.getOrdersByUserWithoutAuth();
        getOrdersByUserClientResponse.then().statusCode(401)
                .and().assertThat().body("success", equalTo(false))
                .and().assertThat().body("message", equalTo("You should be authorised"));
    }

    @After
    public void deleteTestData() {
        ingredients.clear();
        deleteUser(EMAIL, PASSWORD);
    }

}
