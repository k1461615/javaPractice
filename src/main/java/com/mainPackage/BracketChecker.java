package com.mainPackage;

import java.util.Stack;

public class BracketChecker {

    private static final Bracket[] BRACKETS = new Bracket[]{
            new Bracket('{', '}'),
            new Bracket('[', ']'),
            new Bracket('<', '>'),
            new Bracket('(', ')')};

    static boolean isMatchingBrackets(String input) {
        Stack<Character> requiredClosing = new Stack<>();
        for (char c : input.toCharArray()) {
            for (Bracket bracket : BRACKETS) {
                if (bracket.open == c) {
                    requiredClosing.push(bracket.close);
                    break;
                } else if (bracket.close == c && (requiredClosing.isEmpty() || requiredClosing.pop() != c)) {
                    return false;
                }
            }
        }
        return requiredClosing.isEmpty();
    }

    public static void main(String[] args) {
        String[] match = new String[]{"()(((", "())))", "((((()", "{}()[]a", "({)}", "({[)}]", "({a)", "(g", "g)("};

        for (String s : match) {
            System.out.printf("%b\n", isMatchingBrackets(s));
        }
    }

    private static class Bracket {
        final char open;
        final char close;

        Bracket(char open, char close) {
            this.open = open;
            this.close = close;
        }
    }
}
