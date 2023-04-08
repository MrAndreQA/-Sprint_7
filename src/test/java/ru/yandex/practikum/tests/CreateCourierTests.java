package ru.yandex.practikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.yandex.practikum.BaseScript;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static ru.yandex.practikum.steps.CourierSteps.*;

public class CreateCourierTests extends BaseScript {
    @Test
    @DisplayName("Создание курьера")
    @Description("Базовый позитивный тест создания курьера")
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
    @DisplayName("Невозможно создать 2-х одинаковых курьеров")
    @Description("Негативный тест создания двух идентичных курьеров")
    public void checkNotCreateTwoEqualCouriers() {
        createCourier(TEST_LOGIN, TEST_PASSWORD, TEST_FIRST_NAME)
                .then()
                .assertThat()
                .statusCode(HTTP_CREATED)
                .and()
                .assertThat()
                .body("ok", is(true));
        createCourier(TEST_LOGIN, TEST_PASSWORD, TEST_FIRST_NAME)
                .then()
                .assertThat()
                .statusCode(HTTP_CONFLICT)
                .and()
                .assertThat()
                .body("message", is("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Курьер создается только если указаны все обязательные поля")
    @Description("Курьер может быть создан без заполнения поля firstName")
    public void checkCourierCreatedWithMandatoryFields() {
        createCourier(TEST_LOGIN, TEST_PASSWORD, null)
                .then()
                .assertThat()
                .statusCode(HTTP_CREATED)
                .and()
                .assertThat()
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Тело запроса не содержит поле с паролем")
    @Description("Должна возвращаться ошибка, если тело запроса не содержит поле с паролем")
    public void checkErrorIfInBodyNoPassField() {
        createCourier(TEST_LOGIN, null, TEST_FIRST_NAME)
                .then()
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Тело запроса не содержит поле с логином")
    @Description("Должна возвращаться ошибка, если тело запроса не содержит поле с логином")
    public void checkErrorIfInBodyNoLoginField() {
        createCourier(null, TEST_PASSWORD, TEST_FIRST_NAME)
                .then()
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера с повторяющимся логином")
    @Description("Должна возвращаться ошибка при создании курьера с уже существующим логином. Тело ответа полностью идентично документации")
    public void checkErrorIfCourierCreateWithExistLogin() {
        createCourier(TEST_LOGIN, TEST_PASSWORD, TEST_FIRST_NAME)
                .then()
                .assertThat()
                .statusCode(HTTP_CREATED)
                .and()
                .assertThat()
                .body("ok", is(true));
        createCourier(TEST_LOGIN, TEST_PASSWORD + "new", TEST_FIRST_NAME)
                .then()
                .assertThat()
                .statusCode(HTTP_CONFLICT)
                .and()
                .assertThat()
                .body("message", is("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void deleteData() {
        deleteCourier(TEST_LOGIN, TEST_PASSWORD);
    }
}
