package com;

import com.google.common.reflect.ClassPath;
import java.lang.String;
import java.util.Collections;
import java.util.List;

import java.io.IOException;

public class Q50064791 {

    public static void main(String[] args) {
        List<String> dummyList = Collections.emptyList();
        printClassCount("com.so.npe", Q50064791.class);
        printClassCount("com.so.another", Q50064791.class);
        printClassCount("com.so", Q50064791.class);
        printClassCount("com.google", Q50064791.class);
        printClassCount("java.lang", Q50064791.class);
        printClassCount("java.util", Q50064791.class);
        printClassCount("java", Q50064791.class);
    }

    private static void printClassCount(String packageName, Class classForClassLoader) {
        System.out.println("Number of toplevel classes in " + packageName + ": " +
                countTopleveClassesInPackage(packageName, classForClassLoader));
    }

    private static int countTopleveClassesInPackage(String packageName, Class clazz) {
        try {
            ClassPath classPath = ClassPath.from(clazz.getClassLoader());
            return classPath.getTopLevelClassesRecursive(packageName).size();
        } catch (IOException e) {
            return 0;
        }
    }
}