package hashcode;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by naman.nigam on 08/11/16.
 */
public class HashCodeUtil extends HashCodeBuilder {

    @Override
    public HashCodeUtil append(Object value) {
        if (value instanceof Enum) {
            return append(value.toString());
        } else {
            return (HashCodeUtil) super.append(value);
        }
    }
}