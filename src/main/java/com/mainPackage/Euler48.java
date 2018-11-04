package com.mainPackage;

import java.math.BigInteger;

public class Euler48 {
    public static void main(String[] args) {
        long timeS = System.currentTimeMillis();
        BigInteger sum = new BigInteger("0");

        for (int i = 1; i <= 1000; i++) {
            sum = sum.add(BigInteger.valueOf(i).pow(i));
        }

        System.out.println(sum.mod(new BigInteger("10000000000")));
        System.out.println("Finished in " + (System.currentTimeMillis() - timeS) + " ms");
    }
}
