package com.aranvihnclark.project1;

public class MissingNumber {
    public static void main(String[] args) {
        // Missing "5".
        int[] array = { 2, 3, 8, 4, 1, 7, 6 };

        // Missing "12".
        int[] array2 = { 9, 10, 1, 2, 7, 6, 11, 13, 5, 3, 8, 4 };

        System.out.print("\nLocate via sorting: ");
        System.out.print(findMissingNumberOne(array, array.length + 1));
        System.out.println(findMissingNumberOne(array2, array2.length + 1));

        System.out.print("\nLocate via adding: ");
        System.out.print(findMissingNumberTwo(array, array.length + 1));
        System.out.println(findMissingNumberTwo(array2, array2.length + 1));
    }

    //*****************************************************
    //************** Step1 - Reduce Runtime ***************
    //*****************************************************

    public static int findMissingNumberOne(int[] arr, int maxNum) {

        // Added this after Project 4's test method creation - realized I needed this.
        if (maxNum == arr.length) {
            return 0;
        }

        // Variable to hold the total that the array parameter should have if it was all added.
        // Since our for loop later won't add this for us, we can just have our total start out equaling "maxNum".
        int total = maxNum;

        // Variable to hold the actual total of the array parameter when it is all added
        int arrMaxValue = 0;

        // We iterate through the entire array and add up both totals.
        for (int i = 0; i < arr.length; i++) {

            // This is to add up from 1 to whatever the maxNum value is.
            total += i + 1;

            // This is to add up the values in the array to get the total minus the missing number.
            arrMaxValue += arr[i];
        }

        // We return the value of the actual total minus the total of the array to get the missing number.
        return total - arrMaxValue;
    }

    //*****************************************************
    //************** Step2 - Reduce Memory ****************
    //*****************************************************

    public static int findMissingNumberTwo(int[] arr, int maxNum) {
        // The 'maxNum' parameter is honestly not needed as all we needed to do to get it is "arr.length".
        // As such, I will repurpose this variable where it will represent the missing number for this purpose.
        // ** This was coded under the impression I didn't need to worry about an array not missing a value.

        // Added this after Project 4's test method creation - realized I needed this.
        if (maxNum == arr.length) {
            return 0;
        }

        // We iterate through each value in the array again.
        for (int i = 0; i < arr.length; i++) {

            // This is to add up from 1 to whatever the arr.length value is.
            maxNum += i + 1;

            // We subtract the array values from our variable to see what the missing number is at the end of the loop.
            maxNum -= arr[i];
        }

        // We return the missing num.
        return maxNum;
    }

    //*****************************************************
    //************** Step3 - Going Further ****************
    //*****************************************************

    // It seems I already did so when handling steps 1 and 2...

    //*****************************************************
    //**************** Project4 - Testing *****************
    //*****************************************************
    public static int missingNumber(int[] numbers, int maxNum) {

        // I create a variable just because it made it easier to understand what was going on, though I do feel it isn't really necessary.
        int test = maxNum;

        // Because we are trying to account for arrays that aren't missing any numbers, we need to check that first.
        if (maxNum == numbers.length) {
            return 0;
        }

        for (int i = 0; i < numbers.length; i++) {
            test += i + 1;
            test -= numbers[i];
        }

        // Then we return our test result because we know there is an issue.
        return test;
    }
}