package jsonschema2pojo;

import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by naman.nigam on 13/12/16.
 *
 * Uses jsonschema2pojo (https://github.com/joelittlejohn/jsonschema2pojo)
 * to generate pojos from json schema files
 */
public class JsonSchemaCoverter {

    private static final JsonSchemaCoverter JSON_SCHEMA_COVERTER = new JsonSchemaCoverter();
    private GenerationConfig generationConfig = new DefaultGenerationConfig() {
        @Override
        public boolean isIncludeHashcodeAndEquals() {
            return false;
        }

        @Override
        public boolean isIncludeToString() {
            return false;
        }
    };
    private RuleFactory ruleFactory = new RuleFactory(generationConfig, new Jackson2Annotator(), new SchemaStore());
    private SchemaMapper mapper = new SchemaMapper(ruleFactory, new SchemaGenerator());

    private JsonSchemaCoverter() {
    }

    public static JsonSchemaCoverter getInstance() {
        return JSON_SCHEMA_COVERTER;
    }

    public void generate(String pojoJson, JCodeModel jCodeModel, String destinationFolder, String className,
                         String packageName) throws
            IOException {
        mapper.generate(jCodeModel, className, packageName, pojoJson);
        jCodeModel.build(new File(destinationFolder), (PrintStream) null);
    }
}