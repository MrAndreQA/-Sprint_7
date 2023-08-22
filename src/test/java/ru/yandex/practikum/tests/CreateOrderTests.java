package ru.yandex.practikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practikum.BaseScript;
import ru.yandex.practikum.model.OrderModel;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.practikum.steps.OrderSteps.cancelOrder;
import static ru.yandex.practikum.steps.OrderSteps.createOrder;

@RunWith(Parameterized.class)
public class CreateOrderTests extends BaseScript {

    private static final List<OrderModel> ORDERS = List
            .of(new OrderModel(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_ADDRESS, TEST_METRO_STATION, TEST_PHONE, TEST_RENT_TIME, TEST_DELIVERY_DATE, TEST_COMMENT, TEST_COLOR_BLACK),
                    new OrderModel(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_ADDRESS, TEST_METRO_STATION, TEST_PHONE, TEST_RENT_TIME, TEST_DELIVERY_DATE, TEST_COMMENT, TEST_COLOR_GREY),
                    new OrderModel(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_ADDRESS, TEST_METRO_STATION, TEST_PHONE, TEST_RENT_TIME, TEST_DELIVERY_DATE, TEST_COMMENT, TEST_COLORS),
                    new OrderModel(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_ADDRESS, TEST_METRO_STATION, TEST_PHONE, TEST_RENT_TIME, TEST_DELIVERY_DATE, TEST_COMMENT));
    private final OrderModel order;


    public CreateOrderTests(OrderModel order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static List<OrderModel> getOrderCreationTestData() {
        return ORDERS;
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Позитивный тест создания заказа с параметризацией")
    public void checkOrderCreate() {
        var response = createOrder(order);
        response.then()
                .assertThat()
                .body("track", notNullValue())
                .and()
                .statusCode(HTTP_CREATED);
        order.setTrack(response.jsonPath().getString("track"));
    }

    @After
    public void deleteData() {
        cancelOrder(order.getTrack());
    }
}
