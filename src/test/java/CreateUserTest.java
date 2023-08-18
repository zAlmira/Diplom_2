import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import site.nomoreparties.stellarburgers.client.CreateUserClient;
import site.nomoreparties.stellarburgers.model.CreateUserModel;
import site.nomoreparties.stellarburgers.model.CreateUserWithoutNameModel;

import static org.hamcrest.core.IsEqual.equalTo;

public class CreateUserTest extends BaseTest {
    private Response getCreateUniqueUserResponse;

    @Test
    @DisplayName("Успешная регистрация уникального пользователя")
    public void doPostCreateUniqueUser200() {
        CreateUserClient createUserClient = new CreateUserClient();
        getCreateUniqueUserResponse = createUserClient.doCreateUser(new CreateUserModel(EMAIL, PASSWORD, NAME));
        getCreateUniqueUserResponse.then().statusCode(200)
                .and().assertThat().body("success", equalTo(true))
                .and().assertThat().body("user.email", equalTo(EMAIL))
                .and().assertThat().body("user.name", equalTo(NAME));
    }

    @Test
    @DisplayName("Регистрация существующего пользователя")
    public void doPostCreateExistableUser403() {
        CreateUserClient createUserClient = new CreateUserClient();
        getCreateUniqueUserResponse = createUserClient.doCreateUser(new CreateUserModel(EMAIL, PASSWORD, NAME));
        Response getCreateExistableUserResponse = createUserClient.doCreateUser(new CreateUserModel(EMAIL, PASSWORD, NAME));
        getCreateExistableUserResponse.then().statusCode(403)
                .and().assertThat().body("success", equalTo(false))
                .and().assertThat().body("message", equalTo("User already exists"));
    }

    @Test
    @DisplayName("Регистрация без заполненного обязательного поля")
    public void doPostCreateUserWithoutName403() {
        CreateUserClient createUserClient = new CreateUserClient();
        getCreateUniqueUserResponse = createUserClient.doCreateUserWithoutName(new CreateUserWithoutNameModel(EMAIL, PASSWORD));
        getCreateUniqueUserResponse.then().statusCode(403)
                .and().assertThat().body("success", equalTo(false))
                .and().assertThat().body("message", equalTo("Email, password and name are required fields"));
    }


    @After
    public void deleteTestData() {
        deleteUser(EMAIL, PASSWORD);
    }


}
