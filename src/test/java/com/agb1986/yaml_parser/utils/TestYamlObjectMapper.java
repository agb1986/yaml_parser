package com.agb1986.yaml_parser.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.agb1986.yaml_parser.data.DataStub;
import com.fasterxml.jackson.databind.DatabindException;
import org.junit.jupiter.api.Test;

class TestYamlObjectMapper {

    @Test
    void test_parseYamlToModel() throws IOException {
        ApplicationLogger.info("Postitive Test - parseYamlToModel - Starting");

        DataStub testStub = YamlObjectMapper
                .parseYamlToModel(new File("src/test/resources/data.yaml"), DataStub.class);

        assertEquals("YAML Test", testStub.getTestName());
        assertThat(testStub.getTestName(), isA(String.class));

        assertEquals("This is the description of YAML Test", testStub.getTestDescription());
        assertThat(testStub.getTestDescription(), isA(String.class));

        assertEquals(Map.of("type", "json", "path", "src/test/resources/data/testData"),
                testStub.getTestDataFile());
        assertThat(testStub.getTestDataFile(), isA(Map.class));

        assertEquals(List.of("https://localhost:8080/hello", "https://localhost:8080/world"),
                testStub.getTestEnviroments());
        assertThat(testStub.getTestEnviroments(), isA(List.class));

        ApplicationLogger.info("Postitive Test - parseYamlToModel - Ending");

    }

    @Test
    void test_parseYamlToModel_DatabindException() {
        ApplicationLogger.info("Exception Test - parseYamlToModel.IOException - Starting");
        assertThrows(DatabindException.class, () -> YamlObjectMapper
                .parseYamlToModel(new File("src/test/resources/data_negative.yaml"), DataStub.class));
        ApplicationLogger.info("Exception Test - parseYamlToModel.IOException - Ending");
    }

    @Test
    void test_parseYamlToModel_IOException() {
        ApplicationLogger.info("Exception Test - parseYamlToModel.IOException - Starting");
        assertThrows(IOException.class, () -> YamlObjectMapper
                .parseYamlToModel(new File("src/test/resources/null"), DataStub.class));
        ApplicationLogger.info("Exception Test - parseYamlToModel.IOException - Ending");
    }
}
