package hashcode;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by naman.nigam on 24/11/16.
 */
public class SubObject extends OneObject {

    String ex;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SubObject subObject = (SubObject) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(ex, subObject.ex)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(ex)
                .toHashCode();
    }

    int hashCode(String x) {
        return new HashCodeBuilder()
                .append(ex)
                .toHashCode();
    }
}
