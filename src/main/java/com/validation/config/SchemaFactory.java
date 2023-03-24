package com.validation.config;



import com.networknt.schema.*;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class SchemaFactory {

    String URI = "https://json-schema.org/draft/2019-09/schema";
    String ID = "$id";

    List<Format> BUILT_IN_FORMATS = new ArrayList<>(JsonMetaSchema.COMMON_BUILTIN_FORMATS);

    public JsonSchemaFactory SchemaFactory(){
        JsonMetaSchema jsonMetaSchema = new JsonMetaSchema.Builder(URI)
                .idKeyword(ID)
                .addFormats(BUILT_IN_FORMATS)
                .addKeywords(ValidatorTypeCode.getNonFormatKeywords(SpecVersion.VersionFlag.V201909))
                .addKeywords(Lists.newArrayList(new NonValidationKeyword("$schema"),
                        new NonValidationKeyword("$id"),
                        new NonValidationKeyword("titel"),
                        new NonValidationKeyword("description"),
                        new NonValidationKeyword("default"),
                        new NonValidationKeyword("definitions"),
                        new NonValidationKeyword("$defs"))).addKeyword(new GroovyKeyword()).build();
      return new JsonSchemaFactory.Builder().defaultMetaSchemaURI(jsonMetaSchema.getUri()).addMetaSchema(jsonMetaSchema).build();

    }
}
