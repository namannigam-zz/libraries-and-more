package com;

import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        System.out.println("\u010d-\u0161-\u0107-\u0111-\u017e");
        System.out.println(Ints.class);

        ModuleLayer.boot().modules().stream()
                .map(Module::getName)
                .forEach(System.out::println);

        List<Person> personList = new ArrayList<>();
        personList.stream().filter(t -> t.getStatus() != null && t.getStatus().equalsIgnoreCase("In Progress"))
                .collect(Collectors.toList());

        String[] array = {"test1", "test2", "test3"};
        Arrays.stream(array).forEach(x -> System.out.println(x + "stack"));


        Long globalVal = 10l;
        List<Long> queryLongs = Arrays.asList(600L, 700L, 800L);
        Map<Long, Long> map = queryLongs.stream().collect(Collectors.toMap(i -> i, i -> globalVal));

        System.out.println(map);


        ArrayList<Integer> myarray = new ArrayList<>();
        myarray.add(2);
        myarray.add(6);
        myarray.add(5);
        myarray.add(1);
        myarray.add(2);
        myarray.add(3);
        myarray.add(1);
        myarray.add(0);
        IntStream.range(0, 3).boxed().forEach(i -> myarray.remove(0));
        System.out.println(myarray);

    }

    static class Person {
        String name;
        String status;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}