package com.validation.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.*;

public class GroovyKeyword extends AbstractKeyword {
    public GroovyKeyword() {
        super("groovy");
    }

    @Override
    public void setCustomMessage(String message) {
        super.setCustomMessage(message);
    }

    @Override
    public JsonValidator newValidator(String s, JsonNode jsonNode, JsonSchema jsonSchema, ValidationContext validationContext) throws JsonSchemaException, Exception {
        return null;
    }
}
