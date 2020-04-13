package com.mainPackage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BracketCheckerTest {

  @Test
  public void bracketMatcher() {
    String[] match = new String[]{"()((()()))", "()[]{}", "()()", "({[]})", "({})"};

    for (String s : match) {
      assertTrue(BracketChecker.isMatchingBrackets(s));
    }

    String[] mismatch = new String[]{
        "()(((",
        "())))",
        "((((()",
        "({}()[]",
        "({)}",
        "({[)}]",
        "({a)",
        "(g", "g)("
    };

    for (String s : mismatch) {
      assertFalse(BracketChecker.isMatchingBrackets(s));
    }
  }

}