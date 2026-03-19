package contract.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public final class RestAssuredSpecs {

    private static final RequestSpecification API_SPEC = build();

    private RestAssuredSpecs() {}

    private static RequestSpecification build() {
        TestConfig.ApiConfig cfg = TestConfig.load();

        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(cfg.baseUri());

        cfg.defaultHeaders().forEach(builder::addHeader);

        return builder.build();
    }

    public static RequestSpecification api() {
        return API_SPEC;
    }
}