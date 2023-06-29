package com.aranvihnclark.project3;

import java.util.LinkedList;

public class LinkedListReverseDigit {
    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();

        list1.add(3);
        list1.add(2);
        list1.add(1);

        list2.add(6);
        list2.add(5);
        list2.add(4);

        System.out.println(sumOfLinkedList(list1, list2));
    }

    public static LinkedList<Integer> sumOfLinkedList(LinkedList<Integer> list1, LinkedList<Integer> list2) {

        LinkedList<Integer> summedList = new LinkedList<>();

        for (int i = list1.size(); i > 0; i--) {
            summedList.add(list1.pop() + list2.pop());
        }

        return summedList;
    }
}

// The runtime of this function should just be O(n) as we have to iterate through all the list once, always, and the size of the parameter is unknown.