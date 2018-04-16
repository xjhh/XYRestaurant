package com.example.xy_restaurant.generator;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        for (Integer item : list) {
            System.out.print("----"+item);
        }
        System.out.println("---");
        List<Integer> list1 = list;
        for (Integer item : list1) {
            System.out.print("----"+item);
        }
        System.out.println("---");
        list1.remove(0);

        for (Integer item : list1) {
            System.out.print("----"+item);
        }

    }
}
