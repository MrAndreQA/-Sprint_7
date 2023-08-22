package ru.yandex.practikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.practikum.BaseScript;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.practikum.steps.OrderSteps.getOrders;

public class OrderListTests extends BaseScript {

    @Test
    @DisplayName("Получить список заказов")
    @Description("Базовый тест по получению списка заказов. В теле ответа возвращается список заказов")
    public void checkOrderList() {
        getOrders()
                .then()
                .assertThat()
                .statusCode(HTTP_OK)
                .assertThat()
                .body(notNullValue());
    }
}
