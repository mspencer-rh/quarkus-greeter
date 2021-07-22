package org.acme.getting.started;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @TestHTTPResource("/hello")
    String testUrl;

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get(testUrl)
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

    @Test
    public void testGreetingEndpoint() {
        String uuid = UUID.randomUUID().toString();
        given()
                .pathParam("name", uuid)
                .when().get(testUrl + "/greeting/{name}")
                .then()
                .statusCode(200)
                .body(is("hello " + uuid));
    }

}