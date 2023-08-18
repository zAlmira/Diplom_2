import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.client.LoginClient;
import site.nomoreparties.stellarburgers.client.UpdateUserClient;
import site.nomoreparties.stellarburgers.model.LoginModel;
import site.nomoreparties.stellarburgers.model.LoginResponseModel;
import site.nomoreparties.stellarburgers.model.UpdateUserModel;

import static org.hamcrest.core.IsEqual.equalTo;

public class UpdateUserTest extends BaseTest {
    @Before
    public void setUp() {
        createUser();
    }

    @Test
    @DisplayName("Успешное изменение логина и имени пользователя")
    public void doUpdateUserWithAuthSuccess200() {
        LoginClient loginClient = new LoginClient();
        Response loginResponse = loginClient.doLogin(new LoginModel(EMAIL, PASSWORD));
        LoginResponseModel loginResponseModel = loginResponse.body().as(LoginResponseModel.class);
        UpdateUserClient updateUserClient = new UpdateUserClient();
        Response updateUserResponse = updateUserClient.doUpdateUserWithAuth(loginResponseModel.getAccessTokenWithoutBearer(loginResponseModel.getAccessToken()),
                new UpdateUserModel(EMAIL + "updated", NAME + "updated"));
        updateUserResponse.then().statusCode(200)
                .and().assertThat().body("success", equalTo(true))
                .and().assertThat().body("user.email", equalTo(EMAIL + "updated"))
                .and().assertThat().body("user.name", equalTo(NAME + "updated"));
    }

    @Test
    @DisplayName("Изменение логина и имени пользователя без авторизации")
    public void doUpdateUserWithoutAuth401() {
        UpdateUserClient updateUserClient = new UpdateUserClient();
        Response updateUserResponse = updateUserClient.doUpdateUserWithoutAuth(new UpdateUserModel(EMAIL + "new", NAME + "new"));
        updateUserResponse.then().statusCode(401)
                .and().assertThat().body("success", equalTo(false))
                .and().assertThat().body("message", equalTo("You should be authorised"));

    }

    @After
    public void deleteTestData() {
        deleteUser(EMAIL + "updated", PASSWORD);
        deleteUser(EMAIL, PASSWORD);
    }

}
