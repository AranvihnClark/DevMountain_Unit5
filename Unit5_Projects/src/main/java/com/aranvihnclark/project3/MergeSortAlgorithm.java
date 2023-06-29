package com.aranvihnclark.project3;

import java.util.Random;

/*
The best runtime efficiency, when comparing the three sort methods, depends on the length of the data being sorted.
The slowest will be the quick sort, due to its worst runtime complexity of O(n^2).
It's average and best runtime complexity is O(n)log(n)

Insertion sort is O(n^2) for worst and O(n) for best, so it is much better to use when dealing with smaller data due to its linear runtime.
This is because if the items are already sorted in the right index, it runs even faster as there is nothing to sort.
The average runtime complexity is O(n^2).

Merge Sort is O(n)log(n) for worst and best runtime complexity, and much better to use when dealing with a larger amount of data.
An added bonus is that it is a stable type of sort (or it at least has the potential to be a stable sort because you could make an unstable one).
The average runtime complexity if O(n)log(n).
*/

public class MergeSortAlgorithm {
    public static void main(String[] args) {

        Random random = new Random();

        int[] array = new int[random.nextInt(5) + 5];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(-100, 100);
        }

        // Just practicing converting arrays into lists.

//        List<Integer> list = new ArrayList<>();
//        for (int i : array) {
//           list.add(i);
//        }

        // *********************************************

        System.out.print("\nUnsorted Array: [ ");
        for (int i : array) {
            System.out.print(i + ", ");
        }
        System.out.println("]");
//        System.out.println("\nOr: " + list);

        mergeSort(array, 0, array.length);

//        list.clear();
//        for (int i : array) {
//            list.add(i);
//        }

        System.out.print("Merge Sorted Array: [ ");
        for (int i : array) {
            System.out.print(i + ", ");
        }
        System.out.println("]");
//        System.out.println("\nOr: " + list);
    }

    public static void mergeSort (int[] array, int start, int end) {

        // We need to first check if the list sent in is a single indexed list.
        if (end - start < 2) {
            return;
        }

        // Then we need to figure out where the middle of this array is.
        // Thankfully, the division operator automatically rounds properly for us.
        // This is useful for us to separate our lists as we go down our recursion.
        int middle = (start + end) / 2;

        // First merge sort recursion continues to split the left side of our list in half until we reach a left side with only 1 value.
        mergeSort(array, start, middle);

        // Second merge sort recursion continues to split the right side of our list in half until we reach a right side with only 1 value.
        // This is, of course, only really reached after all left recursions are done - including after splitting 1 right side.
        mergeSort(array, middle, end);

        // Then we merge the separated lists together.
        merge(array, start, middle, end);
    }

    public static void merge(int[] array, int start, int middle, int end) {

        // Checks to see if the left array are all smaller than the right array.
        // This evaluates the last of the left and the first of the right.
        // If the last on the left is 25 (meaning it is the largest on the left side),
        // and the first on the right is 28 (meaning it is the smallest on the right side),
        // this means they can be combined as is.
        // ** To sort in ascending or descending order, change the greater or less than symbol.
        if (array[middle - 1] <= array[middle]) {
            return;
        }

        // Temporary indexes to track our merging of our sorted arrays
        int i = start;
        int j = middle;
        int tempIndex = 0;

        // Creates a new temporary array with index length of our total number of indexes.
        int[] tempArray = new int[end - start];

        while (i < middle && j < end) {

            // Checks to see which of the two sides of the array are smaller and enters it into our temp array.
            // The "=" in our "<=" helps keep our sorting 'stable'.
            // ** To sort in ascending or descending order, change the greater or less than symbol.
            tempArray[tempIndex++] = array[i] <= array[j] ? array[i++] : array[j++];
        }

        // Handles the remaining elements in our array if there are any in our left array (before the middle).
        // The right side we don't need to worry about because the right side 'bigger' than the left side.
        System.arraycopy(array, i, array, start + tempIndex, middle - i);
        System.arraycopy(tempArray, 0, array, start, tempIndex);
    }
}
