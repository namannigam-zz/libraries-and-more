package guice;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;

public class TypeLtrl<T> {

    private Class<T> clazz;

    @Inject
    public TypeLtrl(TypeLiteral<T> literal) {
        this.clazz = (Class<T>) literal.getRawType();
    }
}