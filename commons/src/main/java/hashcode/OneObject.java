package hashcode;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;

/**
 * Created by naman.nigam on 06/10/16.
 */
public class OneObject {

    Object field;
    Currency currency;
    double[] xyz;
    ArrayList arrayList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OneObject oneObject = (OneObject) o;

        return new EqualsBuilder()
                .append(field, oneObject.field)
                .append(currency, oneObject.currency)
                .append(xyz, oneObject.xyz)
                .append(arrayList, oneObject.arrayList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(field)
                .append(currency)
                .append(xyz)
                .append(arrayList)
                .toHashCode();
    }
}