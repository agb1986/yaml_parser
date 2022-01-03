package com.agb1986.yaml_parser.utils;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlObjectMapper {
    
    public static Class<?> parseYamlToModel(File file, Class<?> model) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		objectMapper.findAndRegisterModules();

        ProjectLogger.info("Mapping YAML to class: ", model.getSimpleName());

        try {
            return objectMapper.readValue(file, model.getClass());
        } catch (StreamReadException | DatabindException processingException) {
            ProjectLogger.error(processingException, "PROCESSING EXCEPTION");
        } catch (IOException ioException) {
            ProjectLogger.error(ioException, "IO EXCEPTION");
        }

        return null;
    }
}