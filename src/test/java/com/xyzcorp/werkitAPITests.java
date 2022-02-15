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
    

    // @Test
    // public void setUpUser(){
    //     JSONObject userTest = new JSONObject()
    //     .put("username", "testUser111")
    //     .put("email", "testUser111@g.com")
    //     .put("firstName", "Test")
    //     .put("lastName", "User")
    //     .put("password", "password");

    // given()
    //         .relaxedHTTPSValidation()
    //         .accept(ContentType.JSON)
    //         .body(userTest.toString())
    //         .when()
    //         .post("https://staging.tiered-planet.net/werk-it-back-end/user/")
    //         .then()
    //         .assertThat();
    //         //.statusCode(200);
    // }

    @Test
    public void testUser() {
            given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get("https://staging.tiered-planet.net/werk-it-back-end/weights/user/26")
                .then()
                .assertThat()
                .body("[0].id", equalTo(612));
    }

    @Test
    public void addExercise() {
        JSONObject userExerciseTest = new JSONObject()
        .put("pounds", "40")
        .put("reps", "20")
        .put("sets", "2")
        .put("name", "bench press");
        System.out.println(userExerciseTest);
        given()
            .header("Content-Type", "application/json")
            .header("Accept-Type", "application/json")
            .relaxedHTTPSValidation()
            .accept(ContentType.JSON)
            .body(userExerciseTest.toString())
            .when()
            .post("https://staging.tiered-planet.net/werk-it-back-end/weights/user/26")
            .then()
            .assertThat()
            .statusCode(200);
    }


}
