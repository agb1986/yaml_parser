package com.agb1986.yaml_parser.utils;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlObjectMapper {

    private YamlObjectMapper() {
        throw new AssertionError("YamlObjectMapper is a utility class cannot be instantiated!");
    }

    @SuppressWarnings("unchecked")
    public static <T> T parseYamlToModel(File file, Class<?> model) throws IOException {
        ApplicationLogger.info("Mapping YAML to class: ", model.getSimpleName());

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		objectMapper.findAndRegisterModules();

        try {
            return (T) objectMapper.readValue(file, model);
        } catch (DatabindException databindException) {
            ApplicationLogger.error(databindException, "PROCESSING EXCEPTION");
            throw databindException;
        } catch (IOException ioException) {
            ApplicationLogger.error(ioException, "IO EXCEPTION");
            throw ioException;
        }
    }
}