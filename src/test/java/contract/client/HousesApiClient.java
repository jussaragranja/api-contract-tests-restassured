package contract.client;

import contract.config.RestAssuredSpecs;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Client responsável por chamadas HTTP da API.
 */
public class HousesApiClient {

    public Response getHouses() {
        return given()
                .spec(RestAssuredSpecs.api())
            .when()
                .get("/Houses");
    }

    public Response getHouseById(String id) {
        return given()
                .spec(RestAssuredSpecs.api())
            .when()
                .get("/Houses/{id}", id);
    }
}