package com.euler;

import java.math.BigInteger;

public class Euler25 {

    private int fib() {
        BigInteger limit = (new BigInteger("10")).pow(999);
        BigInteger f1 = BigInteger.valueOf(1);
        BigInteger f2 = BigInteger.valueOf(1);
        int counter = 2;

        while (f2.compareTo(limit) < 0) {
            BigInteger temp = f2;
            f2 = f1.add(f2);
            f1 = temp;
            counter++;
        }

        return counter;
    }

    public static void main(String[] args) {
        long timeS = System.nanoTime();

        System.out.println(new Euler25().fib());

        long timeE = System.nanoTime();
        System.out.println("Finished in " + ((timeE - timeS) / 1e+6) + " ms");
    }
}
