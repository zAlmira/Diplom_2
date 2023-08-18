import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.client.LoginClient;
import site.nomoreparties.stellarburgers.client.MakeOrderClient;
import site.nomoreparties.stellarburgers.model.LoginModel;
import site.nomoreparties.stellarburgers.model.LoginResponseModel;
import site.nomoreparties.stellarburgers.model.MakeOrderModel;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;


public class MakeOrderTest extends BaseTest {
    private ArrayList<String> ingredients = new ArrayList<>();

    @Before
    public void setUp() {
        createUser();
    }

    @Test
    @DisplayName("Успешное оформление заказа с авторизацией и с ингредиентами")
    public void doMakeOrderSuccess200() {
        ingredients.add("61c0c5a71d1f82001bdaaa70");
        ingredients.add("61c0c5a71d1f82001bdaaa73");
        LoginClient loginClient = new LoginClient();
        Response loginResponse = loginClient.doLogin(new LoginModel(EMAIL, PASSWORD));
        LoginResponseModel loginResponseModel = loginResponse.body().as(LoginResponseModel.class);
        MakeOrderClient makeOrderWithAuthClient = new MakeOrderClient();
        Response makeOrderResponse = makeOrderWithAuthClient.doCreateOrderWithAuth(loginResponseModel.getAccessTokenWithoutBearer(loginResponseModel.getAccessToken()),
                new MakeOrderModel(ingredients));
        makeOrderResponse.then().statusCode(200).and().assertThat().body("order", notNullValue());

    }

    @Test
    @DisplayName("Оформление заказа без ингредиентов")
    public void doMakeOrderWithoutIngredient400() {
        ArrayList<String> ingredients = new ArrayList<>();
        LoginClient loginClient = new LoginClient();
        Response loginResponse = loginClient.doLogin(new LoginModel(EMAIL, PASSWORD));
        LoginResponseModel loginResponseModel = loginResponse.body().as(LoginResponseModel.class);
        MakeOrderClient makeOrderWithAuthClient = new MakeOrderClient();
        Response makeOrderResponse = makeOrderWithAuthClient.doCreateOrderWithAuth(loginResponseModel.getAccessTokenWithoutBearer(loginResponseModel.getAccessToken()),
                new MakeOrderModel(ingredients));
        makeOrderResponse.then().statusCode(400)
                .and().assertThat().body("success", equalTo(false))
                .and().assertThat().body("message", equalTo("Ingredient ids must be provided"));

    }

    @Test
    @DisplayName("Оформление заказа с неверным хешем ингредиентов")
    public void doMakeOrderWithIncorrectIngredientHash500() {
        ingredients.add("aaaaa");
        LoginClient loginClient = new LoginClient();
        Response loginResponse = loginClient.doLogin(new LoginModel(EMAIL, PASSWORD));
        LoginResponseModel loginResponseModel = loginResponse.body().as(LoginResponseModel.class);
        MakeOrderClient makeOrderWithAuthClient = new MakeOrderClient();
        Response makeOrderResponse = makeOrderWithAuthClient.doCreateOrderWithAuth(loginResponseModel.getAccessTokenWithoutBearer(loginResponseModel.getAccessToken()),
                new MakeOrderModel(ingredients));
        makeOrderResponse.then().statusCode(500).and().assertThat().body(containsString("Internal Server Error"));
    }

    @Test
    @DisplayName("Оформление заказа без авторизации")
    public void doMakeOrderWithoutAuth200() {
        ingredients.add("61c0c5a71d1f82001bdaaa70");
        MakeOrderClient makeOrderWithoutAuthClient = new MakeOrderClient();
        Response makeOrderResponse = makeOrderWithoutAuthClient.doCreateOrderWithoutAuth(new MakeOrderModel(ingredients));
        makeOrderResponse.then().statusCode(200).and().assertThat().body("order", notNullValue());
    }


    @After
    public void deleteTestData() {
        ingredients.clear();
        deleteUser(EMAIL, PASSWORD);
    }
}
