package com.agb1986.yaml_parser.data;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class TestStub {
    private String testName;
    private String testDescription;
    private Map<String, String> testDataFile;
    private List<String> testEnviroments;
}
