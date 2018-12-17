package junit5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Created by naman.nigam on 02/12/16.
 */
//disable entire class
@Disabled
public class DisabledClassDemo {

    @Disabled
    @Test
    void testWillBeSkipped() {
    }

    @Test
    void testWillBeExecuted() {
    }
}