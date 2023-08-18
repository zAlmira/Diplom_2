import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.client.CreateUserClient;
import site.nomoreparties.stellarburgers.client.DeleteUserClient;
import site.nomoreparties.stellarburgers.client.LoginClient;
import site.nomoreparties.stellarburgers.model.CreateUserModel;
import site.nomoreparties.stellarburgers.model.LoginModel;
import site.nomoreparties.stellarburgers.model.LoginResponseModel;

public class BaseTest {

    protected final String EMAIL = "almoon@almoon.ru";
    protected final String PASSWORD = "almoonpassword";
    protected final String NAME = "almoon";

    protected void createUser() {
        CreateUserClient createUserClient = new CreateUserClient();
        Response getCreateUniqueUserResponse = createUserClient.doCreateUser(new CreateUserModel(EMAIL, PASSWORD, NAME));
    }


    protected void deleteUser(String email, String password) {
        LoginClient loginClient = new LoginClient();
        Response loginResponse = loginClient.doLogin(new LoginModel(email, password));
        LoginResponseModel loginResponseModel = loginResponse.body().as(LoginResponseModel.class);
        if (loginResponseModel.getSuccess().equals("true")) {
            DeleteUserClient deleteUserClient = new DeleteUserClient();
            deleteUserClient.doDeleteUser(loginResponseModel.getAccessTokenWithoutBearer(loginResponseModel.getAccessToken()));
        }
    }
}
