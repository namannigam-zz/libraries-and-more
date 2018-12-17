package mock;

import org.junit.Test;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * Created by naman.nigam on 06/03/17.
 */
public class CartEntryTest {

    @Test
    public void testOne() {
        CartEntry cartEntry = mock(CartEntry.class);
        given(cartEntry.getData()).willReturn(Arrays.asList(" ", ""));
    }
}