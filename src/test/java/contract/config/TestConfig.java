package contract.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Carrega configurações por ambiente (dev, qa, prod).
 */
public final class TestConfig {

    public record ApiConfig(String baseUri, Map<String, String> defaultHeaders) {}

    private static ApiConfig cached;

    private TestConfig() {}

    public static ApiConfig load() {
        if (cached == null) {
            cached = loadInternal();
        }
        return cached;
    }

    @SuppressWarnings("unchecked")
    private static ApiConfig loadInternal() {
        String env = System.getProperty("env", "dev");
        String fileName = "application-" + env + ".yml";

        InputStream is = TestConfig.class.getClassLoader().getResourceAsStream(fileName);

        if (is == null) {
            throw new IllegalStateException("Config file not found: " + fileName);
        }

        Map<String, Object> root;
        try (is) {
            root = new Yaml().load(is);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to read config file: " + fileName, e);
        }

        if (root == null) {
            throw new IllegalStateException("YAML is empty: " + fileName);
        }

        Object apiObj = root.get("api");
        if (!(apiObj instanceof Map<?, ?> apiMap)) {
            throw new IllegalStateException("Missing or invalid 'api' section");
        }

        String baseUri = (String) apiMap.get("baseUri");
        if (baseUri == null || baseUri.isBlank()) {
            throw new IllegalStateException("Missing api.baseUri");
        }

        Object headersObj = apiMap.get("defaultHeaders");

        Map<String, String> headers =
                headersObj instanceof Map<?, ?> raw
                        ? raw.entrySet().stream()
                        .collect(Collectors.toMap(
                                e -> e.getKey().toString(),
                                e -> e.getValue().toString()
                        ))
                        : Map.of();

        return new ApiConfig(baseUri, Map.copyOf(headers));
    }
}