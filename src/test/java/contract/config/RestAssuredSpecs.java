package contract.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * Factory de RequestSpecification.
 */
public final class RestAssuredSpecs {

    private RestAssuredSpecs() {}

    public static RequestSpecification api() {
        TestConfig.ApiConfig cfg = TestConfig.load();

        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(cfg.baseUri());

        cfg.defaultHeaders().forEach(builder::addHeader);

        return builder.build();
    }
}