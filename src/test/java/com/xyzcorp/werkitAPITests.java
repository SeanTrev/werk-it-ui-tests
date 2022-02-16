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
    
    //This test adds a user to the database
    @Test
    public void setUpUser(){
        JSONObject userTest = new JSONObject()
        .put("username", "testUser100")
        .put("email", "testUser100@g.com")
        .put("firstName", "Test")
        .put("lastName", "User")
        .put("password", "password");

    given()
            .header("Content-Type", "application/json")
            .header("Accept-Type", "application/json")
            .log()
            .all()
            .relaxedHTTPSValidation()
            .accept(ContentType.JSON)
            .body(userTest.toString())
            .when()
            .post("https://staging.tiered-planet.net/werk-it-back-end/register")
            .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUser() {
            given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get("https://staging.tiered-planet.net/werk-it-back-end/weights/user/663")
                .then()
                .assertThat()
                .body("[0].id", equalTo(612));
    }

    @Test
    public void deleteUser() {
            given()
                .log()
                .all()
                .relaxedHTTPSValidation()
                .baseUri("https://staging.tiered-planet.net/werk-it-back-end")
                .basePath("/weights/user")
                .queryParam("id", "0")
                .when()
                //DELETE https://<endpoint-url>/storage/<collection>?ids=<ids>
                .delete("/26")
                .then()
                .assertThat()
                .statusCode(405); 
    }

    @Test
	public void testDelete(){
		given()
		.when()
		.delete("/1")
		.then()
		.header("Expires", equalTo("-1"));		
	}

    @Test
    public void addExerciseWeight() {
        JSONObject userExerciseTest = new JSONObject()
        .put("pounds", "140")
        .put("reps", "40")
        .put("sets", "2")
        .put("name", "Squat");
        System.out.println(userExerciseTest);
        given()
            .header("Content-Type", "application/json")
            .header("Accept-Type", "application/json")
            .relaxedHTTPSValidation()
            .accept(ContentType.JSON)
            .body(userExerciseTest.toString())
            .when()
            .post("https://staging.tiered-planet.net/werk-it-back-end/weights/user/663")
            .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void addExerciseAerobics() {
        JSONObject userExerciseTest = new JSONObject()
        .put("name", "Yoga")
        .put("seconds", "500");
        System.out.println(userExerciseTest);
        given()
            .header("Content-Type", "application/json")
            .header("Accept-Type", "application/json")
            .relaxedHTTPSValidation()
            .accept(ContentType.JSON)
            .body(userExerciseTest.toString())
            .when()
            .post("https://staging.tiered-planet.net/werk-it-back-end/aerobics/user/663")
            .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    void testGetVerifyUserId(){
        given()
            .header("Content-Type", "application/json")
            .header("Accept-Type", "application/json")
            .relaxedHTTPSValidation()
            .accept(ContentType.JSON)
            .when()
            .get("https://staging.tiered-planet.net/werk-it-back-end/aerobics/user/{id}", 663)
            .then()
            .statusCode(200)
            .assertThat()
            .body("[0].userId", equalTo(663));

    }
    @Test
    void testGetExercise(){
        given()
            .header("Content-Type", "application/json")
            .header("Accept-Type", "application/json")
            .relaxedHTTPSValidation()
            .accept(ContentType.JSON)
            .when()
            .get("https://staging.tiered-planet.net/werk-it-back-end/aerobics/user/{id}", 663)
            .then()
            .statusCode(200)
            .assertThat()
            .body("[0].name", equalTo("Yoga"));

}
@Test
    void testGetExerciseWeight(){
        given()
            .header("Content-Type", "application/json")
            .header("Accept-Type", "application/json")
            .relaxedHTTPSValidation()
            .accept(ContentType.JSON)
            .when()
            .get("https://staging.tiered-planet.net/werk-it-back-end/weights/user/{id}", 663)
            .then()
            .statusCode(200)
            .assertThat()
            .body("[0].name", equalTo("Squat"));

}
}



