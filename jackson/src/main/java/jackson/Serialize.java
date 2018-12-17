package jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import java.io.IOException;
import java.time.Instant;

public class Serialize {

    public static void main(String[] args) throws IOException {

        ObjectMapper om = new ObjectMapper()
                .registerModules(new JSR310Module())
                .configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);

//        om.reader().forType(Instant.class).readValue("2017-02-01T00:00:00Z");
        om.readValue("\"2017-02-01T00:00:00Z\"", String.class);
        om.convertValue("2017-02-01T00:00:00Z", Instant.class);


    }
}