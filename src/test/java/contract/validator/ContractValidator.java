package contract.validator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.*;

import java.io.InputStream;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Responsável apenas por validação de contrato (JSON Schema).
 */
public final class ContractValidator {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private ContractValidator() {}

    public static Set<ValidationMessage> validateAndReturnErrors(String body, String schemaPath) {
        JsonNode json = toJson(body);
        JsonSchema schema = loadSchema(schemaPath);
        return schema.validate(json);
    }

    public static String formatErrors(Set<ValidationMessage> errors) {
        return errors.stream()
                .map(ValidationMessage::getMessage)
                .collect(Collectors.joining("\n"));
    }

    private static JsonNode toJson(String body) {
        try {
            return MAPPER.readTree(body);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON response", e);
        }
    }

    private static JsonSchema loadSchema(String path) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);

        try (InputStream is = ContractValidator.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new IllegalStateException("Schema not found: " + path);
            }
            return factory.getSchema(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load schema: " + path, e);
        }
    }
}