package com.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.ValidationMessage;
import com.validation.config.FlowSchemaValidator;
import com.validation.config.SchemaFactory;
import com.validation.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.StringSubstitutor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GenericSchemaValidator {

    public Person person;
    public ObjectMapper objectMapper = new ObjectMapper();
    public SchemaFactory schemaFactory = new SchemaFactory();
    private String schemaUrl = "src/test/resources/jason-schema.json";//Defined JsonSchema
    private String payLoadToValidate = "src/test/resources/payload-to-validate.json"; // PayLoad To Validate
    private FlowSchemaValidator flowSchemaValidator;

    @BeforeAll
    public void setup() throws IOException {
        String sourceValidationSchema = FileUtils.readFileToString(new File(schemaUrl), StandardCharsets.UTF_8);
        String payLoadRequest = FileUtils.readFileToString(new File(payLoadToValidate), StandardCharsets.UTF_8);
        flowSchemaValidator = new FlowSchemaValidator(sourceValidationSchema, objectMapper, schemaFactory.SchemaFactory());
        person = objectMapper.readValue(payLoadRequest, Person.class);
    }

    @Test
    @DisplayName("This Method Throws error for invalid json payload")
    public void testJsonSchema() throws JsonProcessingException {
        String validationPayLoad = objectMapper.writeValueAsString(person);
        Map<String, String> dataMap = objectMapper.readValue(validationPayLoad, Map.class);
        System.out.println(dataMap);
        StringSubstitutor stringSubstitutor = new StringSubstitutor(dataMap);
        Set<ValidationMessage> validationMessages = flowSchemaValidator.validateSchema(validationPayLoad);
        assertNotNull(validationMessages);
        log.info("validation count -> {}", validationMessages.size());
        validationMessages.stream().forEach(validationMessage -> {
            log.info("validation message ->{}", stringSubstitutor.replace(validationMessage.getMessage()));
        });
    }
}
