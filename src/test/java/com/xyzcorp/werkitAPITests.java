package com.xyzcorp;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;

public class werkitAPITests {
    

    @Test
    public void setUpUser(){
        JSONObject userTest = new JSONObject()
        .put("username", "testUser111")
        .put("email", "testUser111@g.com")
        .put("firstName", "Test")
        .put("lastName", "User")
        .put("password", "password");

    given()
            .relaxedHTTPSValidation()
            .accept(ContentType.JSON)
            .body(userTest.toString())
            .when()
            .post("https://staging.tiered-planet.net/werk-it/register")
            .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testGetFruits() {
            given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get("https://staging.tiered-planet.net/werk-it")
                .then()
                .assertThat()
                .body("[0].u", equalTo("Winter fruit"));
    }


}
