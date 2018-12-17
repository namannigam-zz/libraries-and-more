package some;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class Sample {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        try (SeContainer container = initializer.initialize()) {
            A a = container.select(A.class).get();
            a.say();
        }
    }

    private static class A {
        void say() {
        }
    }
}