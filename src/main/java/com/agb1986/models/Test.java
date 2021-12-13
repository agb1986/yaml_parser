package com.agb1986.models;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class Test {
    private String testName;
    private String testDescription;
    private Map<String, String> testDataFile;
    private List<String> testEnviroments;
}
