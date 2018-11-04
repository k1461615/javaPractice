package com.mainPackage;

import java.util.Stack;

public class BracketChecker {
    static boolean isMatchingBrackets(String input) {
        String brackets = "(){}[]<>";
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            int pos = brackets.indexOf(c);
            if (pos >= 0) {
                if (pos % 2 == 0) {
                    stack.push(brackets.charAt(pos + 1));
                } else if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String[] match = new String[]{"()(((", "())))", "((((()", "{}()[]a", "({)}", "({[)}]", "({a)", "(g", "g)("};

        for (String s : match) {
            System.out.printf("%b\n", isMatchingBrackets(s));
        }
    }
}
