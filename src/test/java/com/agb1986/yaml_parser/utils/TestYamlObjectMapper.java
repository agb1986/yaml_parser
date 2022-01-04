package com.agb1986.yaml_parser.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import com.agb1986.yaml_parser.data.TestStub;
import org.junit.jupiter.api.Test;

class TestYamlObjectMapper {
    
    @Test
    void test_parseYamlToModel() {
        TestStub testStub = YamlObjectMapper.parseYamlToModel(new File("src/test/resources/data.yaml"), TestStub.class);
        assertEquals("YAML Test", testStub.getTestName());
    }
}
