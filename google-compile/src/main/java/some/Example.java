package some;

import com.google.common.collect.ImmutableList;
import com.google.testing.compile.JavaFileObjects;

import javax.tools.JavaFileObject;

public class Example {

    public static void main(String[] args) {
        JavaFileObject example = JavaFileObjects.forResource("some.Interf.java");
        ImmutableList<JavaFileObject> generated = com.google.testing.compile.Compiler.javac()
                .compile(example).generatedSourceFiles();

    }
}
