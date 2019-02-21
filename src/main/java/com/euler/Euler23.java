package com.euler;

public class Euler23 {
    public static void main(String[] args) {

        final int SIZE = 28124;
        boolean[] abundants = new boolean[SIZE];
        int sum = 0;

        //keep the sum total
        for (int i = 1; i < abundants.length; i++) {
            int abundantsum = 0;
            sum += i; //calculate total sum
            //calculate the sum of proper divisors
            for (int c = 1; c < i; c++) {
                if (i % c == 0) {
                    abundantsum += c;
                }
            }
            //if abundant, set i in array to true
            if (abundantsum > i) {
                abundants[i] = true;
            }
        }

        //from 1 till max, take number
        for (int i = 1; i < abundants.length; i++) {
            //from 1 till number take 2ndnum
            for (int c = 1; c <= i; c++) {
                //if 2ndnum is abundant && number-2ndnum is abundant
                if (abundants[c] && abundants[i - c]) {
                    //minus it from the total sum
                    sum -= i;
                    break; //break out of loop to stop repetition
                }
            }
        }

        System.out.println(sum);

    }
}
