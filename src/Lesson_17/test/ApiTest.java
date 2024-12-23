import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGetRequest() {
        // Выполнение GET-запроса
        Response response = given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200) // Проверка кода ответа
                .extract().response();

        // Логирование всех заголовков для отладки
        System.out.println("Response Headers: " + response.getHeaders());

        // Логирование статуса ответа
        System.out.println("Response Status Code: " + response.getStatusCode());

        // Проверка кода ответа
        assertEquals(200, response.getStatusCode());

        // Проверки для аргументов
        String foo1 = response.jsonPath().getString("args.foo1");
        String foo2 = response.jsonPath().getString("args.foo2");

        System.out.println("foo1: " + foo1);
        System.out.println("foo2: " + foo2);

        assertEquals("bar1", foo1);
        assertEquals("bar2", foo2);

        // Проверки для заголовков
        String host = response.jsonPath().getString("headers.host");
        System.out.println("Host: " + host);
        assertEquals("postman-echo.com", host);

        String xRequestStart = response.jsonPath().getString("headers.x-request-start");
        System.out.println("x-request-start: " + xRequestStart);
        assertNotNull(xRequestStart);

        String connection = response.jsonPath().getString("headers.connection");
        System.out.println("connection: " + connection);
        assertEquals("close", connection);

        String xForwardedProto = response.jsonPath().getString("headers.x-forwarded-proto");
        System.out.println("X-Forwarded-Proto: " + xForwardedProto);
        assertEquals("https", xForwardedProto);

        String xForwardedPort = response.jsonPath().getString("headers.x-forwarded-port");
        System.out.println("X-Forwarded-Port: " + xForwardedPort);
        assertEquals("443", xForwardedPort);

        String xAmznTraceId = response.jsonPath().getString("headers.x-amzn-trace-id");
        System.out.println("X-Amzn-Trace-Id: " + xAmznTraceId);
        assertNotNull(xAmznTraceId);

        String userAgent = response.jsonPath().getString("headers.user-agent");
        System.out.println("User-Agent: " + userAgent);
        assertNotNull(userAgent);

        String accept = response.jsonPath().getString("headers.accept");
        System.out.println("Accept: " + accept);
        assertEquals("*/*", accept);

        String cacheControl = response.jsonPath().getString("headers.cache-control");
        System.out.println("cache-control: " + cacheControl);

        String postmenToken = response.jsonPath().getString("headers.postman-token");
        System.out.println("postman-token: " + postmenToken);

        String acceptEncoding = response.jsonPath().getString("headers.accept-encoding");
        System.out.println("accept-encoding: " + acceptEncoding);

        // Проверка URL
        String url = response.jsonPath().getString("url");
        System.out.println("URL: " + url);
        assertEquals("https://postman-echo.com/get?foo1=bar1&foo2=bar2", url);
    }

    @Test
    public void testPostRequestText() {
        // Данные для отправки
        String requestBody = "This is expected to be sent back as part of response body.";

        // Генерация случайных значений для заголовков
        String xRequestStart = String.valueOf(System.currentTimeMillis());
        String xTraceId = "Root=1-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000000000);
        String postmanToken = "token-" + System.currentTimeMillis();

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "text/plain")
                .header("Host", "postman-echo.com")
                .header("X-Request-Start", xRequestStart)
                .header("X-Amzn-Trace-Id", xTraceId)
                .header("Postman-Token", postmanToken)
                .body(requestBody)
                .log().all() // Логируем запрос
                .when()
                .post("/post")
                .then()
                .log().all() // Логируем ответ
                .extract().response(); // Извлекаем ответ

        // Проверка кода ответа
        response.then().statusCode(200);

        // Проверка содержимого ответа
        response.then().body("data", containsString(requestBody));
    }

    @Test
    public void testPostRequestData() {
        // Данные для отправки
        String requestBody = "foo1=bar1&foo2=bar2";

        // Генерация динамических заголовков
        String xRequestStart = "t" + System.currentTimeMillis();
        String xTraceId = "Root=" + UUID.randomUUID().toString();
        String postmanToken = UUID.randomUUID().toString();

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8") // Изменено на UTF-8
                .header("X-Request-Start", xRequestStart)
                .header("X-Amzn-Trace-Id", xTraceId)
                .header("Postman-Token", postmanToken)
                .body(requestBody)
                .log().all() // Логируем запрос
                .when()
                .post("/post")
                .then()
                .log().all() // Логируем ответ
                .extract().response(); // Извлекаем ответ

        // Проверка кода ответа
        int statusCode = response.getStatusCode();
        if (statusCode != 200) {
            throw new AssertionError("Expected status code <200> but was <" + statusCode + ">. Response body: " + response.getBody().asString());
        }

        // Проверка содержимого ответа
        response.then()
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("json.foo1", equalTo("bar1"))
                .body("json.foo2", equalTo("bar2"));
    }

    @Test
    public void testPutRequest() {
        // Данные для отправки
        String requestBody = "This is expected to be sent back as part of response body.";

        // Отправка PUT-запроса
        Response response = given()
                .header("Content-Type", "text/plain") // Указываем тип контента
                .body(requestBody) // Устанавливаем тело запроса
                .when()
                .put("/put") // Выполняем PUT-запрос
                .then()
                .log().all() // Логируем ответ
                .statusCode(200) // Проверка кода ответа
                .body("data", equalTo(requestBody)) // Проверка содержимого ответа
                .extract().response(); // Извлечение ответа
    }

    @Test
    public void testPathRequest() {
        // Данные для отправки
        String requestBody = "This is expected to be sent back as part of response body.";

        // Отправка PATCH-запроса
        Response response = given()
                .header("Content-Type", "text/plain") // Указываем тип контента
                .body(requestBody) // Устанавливаем тело запроса
                .when()
                .patch("/patch") // Выполняем PATCH-запрос
                .then()
                .log().all() // Логируем ответ
                .statusCode(200) // Проверка кода ответа
                .body("data", equalTo(requestBody)) // Проверка содержимого ответа
                .extract().response(); // Извлечение ответа
    }

    @Test
    public void testDeleteRequest() {
        // Данные для отправки (в данном случае это просто текст)
        String requestBody = "This is expected to be sent back as part of response body.";

        // Отправка DELETE-запроса
        Response response = given()
                .header("Content-Type", "text/plain") // Указываем тип контента
                .body(requestBody) // Устанавливаем тело запроса
                .when()
                .delete("/delete") // Выполняем DELETE-запрос
                .then()
                .log().all() // Логируем ответ
                .statusCode(200) // Проверка кода ответа
                .body("data", equalTo(requestBody)) // Проверка содержимого ответа
                .extract().response(); // Извлечение ответа
    }
}