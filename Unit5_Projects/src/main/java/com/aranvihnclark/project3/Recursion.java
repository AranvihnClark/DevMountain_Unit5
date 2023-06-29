package com.aranvihnclark.project3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Looking at the code, I want to say the worst runtime complexity is O(n).
// This is because, at worst, the code will go through the entire list once before providing us with an answer.
// Since no loop was used (other than the recursive loop) this should have a linear runtime.
public class Recursion {
    public static void main(String[] args) {

        List<Object> stringList = new ArrayList<>();
        List<Object> integerList = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 7; i++) {
            integerList.add(random.nextInt(10));
        }

        stringList.add("Hi");
        stringList.add("my");
        stringList.add("name");
        stringList.add("is");
        stringList.add("Aranvihn");
        stringList.add("Clark");

        System.out.println("Our object list: " + stringList);
        System.out.println("Attempting object search: ('Aranvhin'): " + recursiveSearch("Aranvihn", stringList));
        System.out.println("Attempting object search: ('names'): " + recursiveSearch("names", stringList));

        System.out.println("Our integer list: " + integerList);
        System.out.println("Attempting integer search: ('2'): " + recursiveSearch(2, integerList));
        System.out.println("Attempting integer search: ('4'): " + recursiveSearch(4, integerList));
        System.out.println("Attempting integer search: ('8'): " + recursiveSearch(8, integerList));
    }

    public static int recursiveSearch(Object o, List<Object> list) {

        List<Object> tempList = new ArrayList<>(list);

        if (tempList.size() == 0) {
            return -1;
        }

        if (o == tempList.remove(tempList.size() - 1)) {
            return 0;
        }

        return recursiveSearch(o, tempList);
    }
}
