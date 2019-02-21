package com.euler;

public class Euler2 {
    public static void main(String[] args) {
        int fib1 = 1;
        int fib2 = 2;
        int sum = 0;

        while (fib2 < 4000000) {
            if (fib2 % 2 == 0) {
                sum += fib2;
            }

            int temp = fib2;
            fib2 += fib1;
            fib1 = temp;
        }

        System.out.println(sum);
    }
}
