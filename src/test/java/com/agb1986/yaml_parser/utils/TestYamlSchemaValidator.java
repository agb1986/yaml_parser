package com.agb1986.yaml_parser.utils;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class TestYamlSchemaValidator {

    @Test
    void test_validateYamlSchema() throws IOException {
        YamlSchemaValidator.validateYamlSchema("src/test/resources/schema/data.json",
                "src/test/resources/yaml/data.yaml");
    }

    @Test
    void test_validateYamlSchema_Negative() throws IOException {
        YamlSchemaValidator.validateYamlSchema("src/test/resources/schema/data.json",
                "src/test/resources/yaml/data_incorrect_schema.yaml");
    }
}
