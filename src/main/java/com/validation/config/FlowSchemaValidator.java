package com.validation.config;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Set;

@Slf4j
public class FlowSchemaValidator {
    private final ObjectMapper objectMapper;

    private final JsonSchema schema;

    public FlowSchemaValidator(final String jsonSchema,final ObjectMapper objectMapper, JsonSchemaFactory schemaFactory )
    {
        super();
        Objects.requireNonNull(jsonSchema,"Json Schema is required");
        this.schema=schemaFactory.getSchema(jsonSchema);
        this.objectMapper=objectMapper;
    }
    @SneakyThrows
    public Set<ValidationMessage> validateSchema(final String jsonPayLoad){
        log.info("ValidateSchema()->");
        Objects.requireNonNull(jsonPayLoad,"Input PayLoad Required to Validate");
        JsonNode json = objectMapper.readTree(jsonPayLoad);
        return schema.validate(json);
    }

}
