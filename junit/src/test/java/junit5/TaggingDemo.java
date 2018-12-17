package junit5;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
/**
 * Created by naman.nigam on 02/12/16.
 */

@Tag("fast")
@Tag("model")
public class TaggingDemo {

    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
    }

}