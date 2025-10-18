package com.ucsc.restex;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestApiTest {

    @BeforeClass


    @Test
    public void verifyThatListAllTheUsers(){

        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void verifyThatUserIsCreated(){
        String body = """
                {
                    "name": "morpheus",
                    "job": "leader"
                }
                """;
       given()
               .header("X-api-key", "regres-free-v1")
               .contentType(ContentType.JSON)
               .body(body)
               .when()
               .post("https://reqres.in/api/users")
               .then()
               .assertThat()
               .statusCode(201)
               .body("name", equalTo("morpheus"))
               .log().all();
    }
}
