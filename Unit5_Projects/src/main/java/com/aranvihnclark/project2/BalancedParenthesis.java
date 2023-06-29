package com.aranvihnclark.project2;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BalancedParenthesis {
    public static void main(String[] args) {
        // Step1 Testing
        System.out.println("Parenthesis Only Testing:");
        System.out.println(isBalancedParenthesis("()")); // true
        System.out.println(isBalancedParenthesis("()())(")); // false
        System.out.println(isBalancedParenthesis("I just thought, hey, what's up? (At least I wanted to)")); // true
        System.out.println(isBalancedParenthesis("There are (so I think) a lot of ways to fail this... ? ((This should fail)")); // false

        // Step 1, 2, and 3 Testing
        System.out.println("\nAll Brackets Testing:");
        System.out.println(isBalancedBracket("There are <so I think> a lot of ways to fail this... ? ((This should fail)")); // false
        System.out.println(isBalancedBracket("[][]()()<>{}{{}}")); // true
        System.out.println(isBalancedBracket("{LikeThis} = [90] / (30 * 10) + <11 % (110 / 10)>")); // true
        System.out.println(isBalancedBracket("{LikeThis} = [90] / (30 * 10) + [<11 % (110 / 10)>} - (0 + 15)")); // false
        System.out.println(isBalancedBracket("()()(")); // false

        // Multi-nested Testing
        System.out.println("\nMulti-nested Testing:");
        System.out.println(isBalancedData("<()[()]>")); // true
        System.out.println(isBalancedData("{{[<(<>{})>]()<[]>}}")); // true
        System.out.println(isBalancedData("{{[(<(<>())]()<[]>}}")); // false
    }

    public static boolean isBalancedParenthesis(String string) {

//**********************************************
//******* Step 1 - Balanced Parenthesis ********
//**********************************************
// The runtime complexity for this is O(n) because we iterate through our string (of unknown size n) once no matter what.

        // For simplicity's sake, I was going to use 2 variables, but we can just use one.
        int count = 0;

        // We iterate through our string to look for parenthesis.
        for (int i = 0; i < string.length(); i++) {

            // We need to pull out each character in our string parameter to analyze it.
            // As such, we just declare the char variable inside the loop.
            char c = string.charAt(i);

            // Then we check if the character at index i is an open or closed parenthesis.
            // Note, the order or add or subtracting our count does not matter for this.
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }

            // We need to check if a closing parenthesis was used without an opening parenthesis
            if (count < 0) {
                return false;
            }
        }

        // Then we can return an argument check
        return count == 0;
    }


//**********************************************
//********* Step2 - Balanced Brackets **********
//**********************************************
// The runtime complexity of this is O(n), since we iterate through our string (with an unknown size n) once no matter what.

    public static boolean isBalancedBracket(String string) {

        int roundCount = 0;
        int squareCount = 0;
        int curlyCount = 0;
        int pointedCount = 0;

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (c == '(') {
                roundCount++;
            } else if (c == ')') {
                roundCount--;
            }

            if (c == '[') {
                squareCount++;
            } else if (c == ']') {
                squareCount--;
            }

            if (c == '{') {
                curlyCount++;
            } else if (c == '}') {
                curlyCount--;
            }

            if (c == '<') {
                pointedCount++;
            } else if (c == '>') {
                pointedCount--;
            }

            if (roundCount < 0 || squareCount < 0 || curlyCount < 0 || pointedCount < 0) {
                return false;
            }
        }

        return roundCount == 0 && squareCount == 0 && curlyCount == 0 && pointedCount == 0;
    }


    public static boolean isBalancedData(String string) {
//**********************************************
//**** Step3 - Implement the Data Structure*****
//**********************************************
// Runtime complexity is O(n)Log(n) I think.

        // Using a stack as our data structure since that was the point of the videos.
        Stack<Character> fullStack = new Stack<>();

        // Created a hashmap after looking up what I could potentially use.
        // For the sake of me understanding hashmaps better, I figured I would use it in this exercise.
        Map<Character, Character> bracketMap = new HashMap<>();

        //            KEY   VALUE
        bracketMap.put(')', '(');
        bracketMap.put(']', '[');
        bracketMap.put('}', '{');
        bracketMap.put('>', '<');

        // Iterating through our string to locate our brackets (our KEY and VALUE characters).
        // We use our string parameter's length for the for loop because the max amount of loops needed is the string's length.
        // ** Coming back after finishing and looking at the handout's instructions, it seems I definitely could have done this in one loop.
        // ** It hadn't occurred to me to evaluate key and value pairs in real time or to use the parameter as is instead of creating a Stack data structure.
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (c == '(' || c == '[' || c == '{' || c == '<' || c == ')' || c == ']' || c == '}' || c == '>') {
                fullStack.push(c);
            }
        }

        // I think we need start a second for loop now to check our fullStack stack 'list'.
        for (int i = 0; i < string.length(); i++) {

            // This variable will be to check if the top of the stack is a KEY or a VALUE.
            Character key = fullStack.pop();

            // We check if the supposed 'key' is actually a value.
            // If it not a key, we know it the fullStack stack started with an open bracket.
            if (!bracketMap.containsKey(key)) {
                return false;
            }

            // This variable will be used to confirm if the KEY's VALUE is the same as the NEXT VALUE that occurs in our stack.
            Character value = bracketMap.get(key);

            // Now we create an index to search our stack for testing.
            int index = fullStack.size() - 1;

            // This variable is the 'next' value in our stack that I will use to evaluate against the actual value if the top stack is another key.
            // We initialize it here now so that we can use it below in our large if statement.
            // We need to also need check if the next stack is a key or a value via the peek method, so it makes sense to do it now.
            Character testValue = fullStack.peek();

            // We will check if it is another key first (if true).
            if (bracketMap.containsKey(testValue)) {

                // Since the current top of the stack is another key, we need to jump through our stack to find the next VALUE of our hashmap, hence we start at 2.
                // I use the variable below to identify that we need to keep looping until we find a VALUE to evaluate.
                int keyCount = 2;
                int valueCount = 0;

                while (index > 0) {
                    // Because the keyCount increases, we need to search lower from the top of our stack.
                    index--;

                    // We then need to change our testValue that is being evaluated.
                    testValue = fullStack.elementAt(index);

                    //IF the next stack element is a value we need to add up a valueCount.
                    if (bracketMap.containsValue(testValue)) {
                        valueCount++;

                        // Now we need to check if the valueCount equals the keyCount to determine if we are at the right open bracket.
                        if (valueCount == keyCount) {

                            // If the two counts match, we can then check if the testValue at the index equals our KEY's VALUE.
                            if (!testValue.equals(bracketMap.get(key))) {

                                // If not, we return false and end the loop.
                                return false;
                            } else {

                                // If the two values are a match, we can remove the element at the specified index, and we break out of the while loop.
                                fullStack.remove(index);
                                break;
                            }
                        }
                    } else {
                        // If the testValue was actually a KEY, we increase the keyCount and start the while loop over again.
                        keyCount++;
                    }
                }

            } else {
                // If the next stack is just a value, we can evaluate normally.
                // We assign our testValue the stack element at our specified index.
                testValue = fullStack.elementAt(index);

                if (!testValue.equals(value)) {

                    // We return false because the element in our stack at the specified index does not equal our KEY's VALUE pair.
                    return false;
                } else {

                    // Because our testValue does equal our KEY's VALUE pair, we can remove the element at our specified index.
                    // We then change our loop boolean to false.
                    fullStack.remove(index);
                }
            }

            // Then, of course, we need to break out of the for loop if there is no more elements in our stack to evaluate.
            if (fullStack.isEmpty()) {
                break;
            }
        }
        return true;
    }

//**********************************************
//************* Step4 - Reflection *************
//**********************************************

/*
I have added notes below the block for the steps about their runtime complexity.
I wasn't sure why question 1 and 2 asked basically the same question, so I ignored question 2.

In terms of how mine compares to the solutions... I first, step 2 isn't really in the solution as it seems to be just step 3.
As for step 1, mine and the solution is very, very similar.
If anything, mine uses just smidgen more memory due to the fact that I created a variable to hold each character in the string to evaluate.
I could have done as the solution did and just did string.charAt(i) but I just thought it would be a little easier to look at if I put it as a variable.

My step 2 is easy to read but looks horrendous.
I wasn't sure how to best complete the answer without, so I created multiple variables for each bracket type.

Step 3, I was quite at a loss on what to use at first to try and evaluate open and closed brackets using a stack (since that was what this week was about).
After looking around, I went and used a hashmap since they dealt with 'Keys' and 'Values' and I figured it could work.

It took me longer than intended as I wasn't used to working with stacks and hashmaps, and, looking at my code, I am quite upset at how choppy it looks lol.
But, comparing with the solution's code, I do see that used more loops than necessary.
If anything, my code's runtime complexity is probably O(n)Log(n) compared to the solution's O(n) runtime complexity.
I may be wrong with the writing of that or in general (on my runtime for step 3), but I think this is right.
Being unfamiliar with how to deal with stacks and hashmaps, I at least wanted to avoid nested for loops to avoid O(n^2).

As for how the implementation of the stack would change if I used a queue, I would sya

 */

    public static boolean balancedParenthesis(String string) {
        Stack<Character> fullStack = new Stack<>();
        Map<Character, Character> bracketMap = new HashMap<>();

        bracketMap.put(')', '(');
        bracketMap.put(']', '[');
        bracketMap.put('}', '{');
        bracketMap.put('>', '<');

        // Added a check to see if the string is empty. Returns true as the brackets are technically balanced.
        if (string.equals("")) {
            return true;
        }

        // Added a check for the first char to see if it is a closed bracket.
        if (bracketMap.containsKey(string.charAt(0))) {
            return false;
        }

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (c == '(' || c == '[' || c == '{' || c == '<' || c == ')' || c == ']' || c == '}' || c == '>') {
                fullStack.push(c);
            }
        }

        for (int i = 0; i < string.length(); i++) {
            Character key = fullStack.pop();

            if (!bracketMap.containsKey(key)) {
                return false;
            }

            Character value = bracketMap.get(key);
            int index = fullStack.size() - 1;
            Character testValue = fullStack.peek();

            if (bracketMap.containsKey(testValue)) {
                int keyCount = 2;
                int valueCount = 0;

                while (index > 0) {
                    index--;
                    testValue = fullStack.elementAt(index);

                    if (bracketMap.containsValue(testValue)) {
                        valueCount++;

                        if (valueCount == keyCount) {

                            if (!testValue.equals(bracketMap.get(key))) {
                                return false;

                            } else {
                                fullStack.remove(index);
                                break;
                            }
                        }
                    } else {
                        keyCount++;
                    }
                }

            } else {
                testValue = fullStack.elementAt(index);

                if (!testValue.equals(value)) {
                    return false;
                } else {
                    fullStack.remove(index);
                }
            }

            if (fullStack.isEmpty()) {
                break;
            }
        }
        return true;
    }
}