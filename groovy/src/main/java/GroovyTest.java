import io.vertx.groovy.ext.auth.VertxPropertiesModuleFactory;

public class GroovyTest {
    public static void main(String[] args) {
        VertxPropertiesModuleFactory factory = new VertxPropertiesModuleFactory();
        System.out.println(factory);
    }
}