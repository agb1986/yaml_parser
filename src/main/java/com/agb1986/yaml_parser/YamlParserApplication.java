package com.agb1986.yaml_parser;

import java.io.File;
import java.io.IOException;
import com.agb1986.models.Test;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlParserApplication {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		objectMapper.findAndRegisterModules();

		Test test = objectMapper.readValue(new File("src/main/resources/data.yaml"), Test.class);

		System.out.println(test.getTestName());
		System.out.println(test.getTestDescription());
		System.out.println(test.getTestDataFile().get("type"));
		System.out.println(test.getTestDataFile().get("path"));
		System.out.println(test.getTestEnviroments());
	}
}
