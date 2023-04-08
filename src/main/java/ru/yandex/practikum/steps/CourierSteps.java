package ru.yandex.practikum.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practikum.model.CourierModel;

import static io.restassured.RestAssured.given;
import static ru.yandex.practikum.constant.Endpoints.*;

public class CourierSteps {

    @Step("Create courier")
    public static Response createCourier(String login, String password, String firstName) {
        var courier = new CourierModel(login, password, firstName);
        return given()
                .body(courier)
                .when()
                .post(COURIER)
                .then()
                .extract().response();
    }

    @Step("Create courier")
    public static Response createCourier(CourierModel courier) {
        return given()
                .body(courier)
                .when()
                .post(COURIER)
                .then()
                .extract().response();
    }

    @Step("Login courier. Send POST request to /api/v1/courier/login")
    public static Response loginCourier(String login, String password) {
        var courier = new CourierModel(login, password);
        return given()
                .body(courier)
                .when()
                .post(LOGIN)
                .then()
                .extract().response();
    }

    @Step("Delete courier. Send DELETE request to /api/v1/courier/:id")
    public static Response deleteCourier(String login, String password) {
        return given()
                .when()
                .delete(String.format(COURIER_ID, loginCourier(login, password).jsonPath().getString("id")))
                .then()
                .extract().response();
    }

    @Step("Delete courier by his id. Send DELETE request to /api/v1/courier/:id")
    public static Response deleteCourier(String id) {
        return given()
                .when()
                .delete(String.format(COURIER_ID, id))
                .then()
                .extract().response();
    }

}
