import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import site.nomoreparties.stellarburgers.client.LoginClient;
import site.nomoreparties.stellarburgers.model.LoginModel;

import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {
    private String email;
    private String password;
    private String parameter;
    private String parameterValue;
    private int expectedCode;


    public LoginTest(String email, String password, String parameter, String parameterValue, int expectedCode) {
        this.email = email;
        this.password = password;
        this.parameter = parameter;
        this.parameterValue = parameterValue;
        this.expectedCode = expectedCode;
    }

    @Parameterized.Parameters(name = "Проверка авторизации {0} {1} {4}")
    public static Object[][] dataForMakeTest() {
        return new Object[][]{
                {"almoon@almoon.ru", "almoonpassword", "user.email", "almoon@almoon.ru", 200},
                {"incorrect@almoon.ru", "almoonpassword", "message", "email or password are incorrect", 401},
                {"almoon@almoon.ru", "incorrectpassword", "message", "email or password are incorrect", 401},
        };
    }

    @Before
    public void setUp() {
        createUser();
    }

    @Test
    public void doPostLogin() {
        LoginClient loginClient = new LoginClient();
        Response loginResponse = loginClient.doLogin(new LoginModel(email, password));
        loginResponse.then().statusCode(expectedCode).and().assertThat().body(parameter, equalTo(parameterValue));
    }

    @After
    public void deleteTestData() {
        deleteUser(EMAIL, PASSWORD);
    }
}
