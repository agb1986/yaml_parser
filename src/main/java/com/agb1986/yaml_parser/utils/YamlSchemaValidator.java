package com.agb1986.yaml_parser.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.collect.Lists;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.SpecVersion.VersionFlag;

public class YamlSchemaValidator {

    private YamlSchemaValidator() {
        throw new AssertionError("YamlSchemaValidator is a utility class cannot be instantiated!");
    }

    public static void validateYamlSchema(String jsonSchemaFilePath, String yamlFilePath)
            throws IOException {
        ApplicationLogger.info("Validating schema of YAML @ ", yamlFilePath);

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        JsonSchemaFactory factory =
                JsonSchemaFactory.builder(JsonSchemaFactory.getInstance(VersionFlag.V7))
                        .objectMapper(objectMapper).build();

        Map<?, ?> jsonMap = readJsonFile(jsonSchemaFilePath);
        String jsonString = jsonMapToString(jsonMap);

        ApplicationLogger.info("JSON Schema to be used for validation: ", jsonString);

        JsonSchema schema = factory.getSchema(jsonString);
        JsonNode jsonNode = objectMapper.readTree(Path.of(yamlFilePath).toFile());

        ApplicationLogger.info("YAML Converted to JSON for schema validation: ", jsonNode.toString());

        Set<ValidationMessage> validationMessages = schema.validate(jsonNode);

        if (!validationMessages.isEmpty()) {
            ApplicationLogger.warn("Schema Violations: ", Integer.toString(validationMessages.size()));

            List<ValidationMessage> messages = Lists.newArrayList(validationMessages);

            for (ValidationMessage message : messages) {
                ApplicationLogger.warn(message.getMessage());
            }

        } else {
            ApplicationLogger.info("Schema Validation complete");
        }
    }

    private static Map<?, ?> readJsonFile(String filePath) throws IOException {
        ApplicationLogger.info("Reading JSON from file @ ", filePath);

        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        try {
            return objectMapper.readValue(Paths.get(filePath).toFile(), Map.class);
        } catch (IOException ioException) {
            ApplicationLogger.error(ioException, "IO EXCEPTION");
            throw ioException;
        }
    }

    private static String jsonMapToString(Map<?, ?> jsonMap) throws JsonProcessingException {
        try {
            return new ObjectMapper().writeValueAsString(jsonMap);
        } catch (JsonProcessingException jsonProcessingException) {
            ApplicationLogger.error(jsonProcessingException, "JSON PROCESSING EXCEPTION");
            throw jsonProcessingException;
        }
    }
}
