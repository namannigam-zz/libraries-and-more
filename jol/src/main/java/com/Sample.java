package com;

import org.openjdk.jol.vm.VM;

public class Sample {
    public static void main(String[] args) {
        System.out.println(VM.current().details());
    }
}