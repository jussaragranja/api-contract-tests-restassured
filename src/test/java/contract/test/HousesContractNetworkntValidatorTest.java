package contract.test;

import contract.client.HousesApiClient;
import contract.validator.ContractValidator;
import com.networknt.schema.ValidationMessage;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de contrato utilizando NetworkNT validator.
 */
public class HousesContractNetworkntValidatorTest {

    private final HousesApiClient client = new HousesApiClient();

    @Test
    void shouldReturn200AndMatchContract_whenGetHouses() {
        Response response = client.getHouses();

        response.then()
                .statusCode(200)
            .contentType("application/json")
                .time(lessThan(3000L));

        String responseBody = response.getBody().asString();

        Set<ValidationMessage> errors =
                ContractValidator.validateAndReturnErrors(
                        responseBody,
                        "contracts/houses/get-houses.200.schema.json"
                );

        assertTrue(errors.isEmpty(),
                () -> "Schema validation errors:\n" + ContractValidator.formatErrors(errors));

        assertFalse(responseBody.isBlank());
    }

    @Test
    void shouldReturn200AndMatchContract_whenGetHouseById() {
        String id = "805fd37a-65ae-4fe5-b336-d767b8b7c73a";

        Response response = client.getHouseById(id);

        response.then()
                .statusCode(200)
            .contentType("application/json")
                .time(lessThan(3000L));

        String responseBody = response.getBody().asString();

        Set<ValidationMessage> errors =
                ContractValidator.validateAndReturnErrors(
                        responseBody,
                        "contracts/houses/get-house-by-id.200.schema.json"
                );

        assertTrue(errors.isEmpty(),
                () -> "Schema validation errors:\n" + ContractValidator.formatErrors(errors));

        assertTrue(responseBody.contains(id));
    }
}