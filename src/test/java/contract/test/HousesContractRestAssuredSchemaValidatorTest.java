package contract.test;

import contract.config.RestAssuredSpecs;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * Testes de contrato utilizando validator nativo do RestAssured.
 */
public class HousesContractRestAssuredSchemaValidatorTest {

    @Test
    void shouldMatchContract_whenGetHouses_usingRestAssuredValidator() {
        given()
                .spec(RestAssuredSpecs.api())
            .when()
                .get("/Houses")
            .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(
                        "contracts/houses/get-houses.200.schema.json"));
    }

    @Test
    void shouldMatchContract_whenGetHouseById_usingRestAssuredValidator() {
        String id = "805fd37a-65ae-4fe5-b336-d767b8b7c73a";

        given()
                .spec(RestAssuredSpecs.api())
            .when()
                .get("/Houses/{id}", id)
            .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(
                        "contracts/houses/get-house-by-id.200.schema.json"));
    }
}