package com.mainPackage;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sackman on 6/23/2017
 */
public class BracketCheckerTest {
    @Test
    public void bracketMatcher() throws Exception {
        String[] match = new String[]{"()((()()))","()[]{}","()()","({[]})","({})"};

        for(String s : match){
            assertEquals(true, BracketChecker.isMatchingBrackets(s));
        }

        String[] mismatch = new String[]{"()(((","())))","((((()","({}()[]","({)}","({[)}]","({a)","(g","g)("};

        for(String s : mismatch){
            assertEquals(false, BracketChecker.isMatchingBrackets(s));
        }
    }

}