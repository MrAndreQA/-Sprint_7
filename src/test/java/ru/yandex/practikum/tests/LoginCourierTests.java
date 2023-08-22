package ru.yandex.practikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practikum.BaseScript;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.practikum.steps.CourierSteps.*;

public class LoginCourierTests extends BaseScript {
    @Before
    @Description("Создание курьера до проведения теста по авторизации")
    public void checkCourierCreation() {
        createCourier(TEST_LOGIN, TEST_PASSWORD, TEST_FIRST_NAME)
                .then()
                .assertThat()
                .statusCode(HTTP_CREATED)
                .and()
                .assertThat()
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Авторизация курьера")
    @Description("Базовый позитивный тест. Курьер может авторизоваться, успешный запрос возвращает id")
    public void checkCourierAuthenticated() {
        loginCourier(TEST_LOGIN, TEST_PASSWORD)
                .then()
                .assertThat()
                .body("id", notNullValue())
                .and()
                .statusCode(HTTP_OK);
    }

    @Test
    @DisplayName("Авторизация без указания пароля")
    @Description("Должна возвращаться ошибка - при попытке авторизации без пароля")
    public void checkCourierNotLoginWithoutPassword() {
        loginCourier(TEST_LOGIN, null)
                .then()
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(HTTP_NOT_FOUND);
    }

    @Test
    @DisplayName("Авторизация с неверным паролем")
    @Description("Должна возвращаться ошибка - при попытке авторизации с неверным паролем")
    public void checkCourierNotLoginWithIncorrectPassword() {
        loginCourier(TEST_LOGIN, TEST_PASSWORD + "new")
                .then()
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(HTTP_NOT_FOUND);
    }

    @Test
    @DisplayName("Авторизация без указания логина")
    @Description("Должна возвращаться ошибка - при попытке авторизации без логина")
    public void checkErrorIfBodyHasNoMandatoryField() {
        loginCourier(null, TEST_PASSWORD)
                .then()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(HTTP_BAD_REQUEST);
    }

    @Test
    @DisplayName("Авторизация с неверным логином")
    @Description("Должна возвращаться ошибка - при попытке авторизации с неверным логином")
    public void checkErrorForLoginNonExistingUser() {
        loginCourier(TEST_LOGIN + "new", TEST_PASSWORD)
                .then()
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(HTTP_NOT_FOUND);
    }

    @After
    public void deleteData() {
        deleteCourier(TEST_LOGIN, TEST_PASSWORD);
    }
}
