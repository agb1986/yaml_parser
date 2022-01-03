package com.agb1986.yaml_parser;

import java.io.File;
import com.agb1986.yaml_parser.models.Test;
import com.agb1986.yaml_parser.utils.YamlObjectMapper;

public class YamlParserApplication {

	public static void main(String[] args) {
		YamlObjectMapper.parseYamlToModel(new File("src/main/resources/data.yaml"), Test.class);
	}
}
