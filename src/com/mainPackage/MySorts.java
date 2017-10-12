package com.mainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MySorts {
    //SEARCHING
    // O(n) performance
    static int linearSearch(int[] array, int value){
        for (int i = 0; i < array.length; i++){
            if (array[i] == value){
                return i;
            }
        }
        // value not found. aka return null
        return -1;
    }

    // O(lg n) performance - requires sorted array
    static int binarySearch(int[] array, int val){
        int left = 0;
        int right = array.length-1;

        while (left <= right){
            int mid = (left+right)/2;

            if (array[mid] < val){
                left = mid+1;
            }
            else if (array[mid] > val){
                right = mid-1;
            }
            else return mid;
        }

        return -1;
    }

    // O(n log n) performance (mergeSort + binary)
    static boolean sumSearch(int[] array, int value){
        mergeSort(array);

        for (int i : array){
            if (binarySearch(array, value-array[i]) >= 0){
                return true;
            }
        }
        return false;
    }


    //SORTING ARRAY
    // O(n^2) comparisons, swaps
    static void insertionSort(int[] array){
        for (int j = 1; j < array.length; j++){
            int key = array[j];
            int i = j - 1;

            while (i >= 0 && array[i] > key){
                array[i + 1] = array[i];
                i = i - 1;
            }
            array[i + 1] = key;
        }
    }

    // O(n log n) performance
    static void mergeSort(int[] array){
        if (array.length == 1) return;
        int p = array.length/2;
        int[] left = new int[p];
        int[] right = new int[array.length - p];

        System.arraycopy(array,0,left,0,left.length);
        System.arraycopy(array,p,right,0,right.length);

        mergeSort(left);
        mergeSort(right);

        int i = 0;
        int j = 0;
        for (int k =0; k < array.length; k++){
            if (j == right.length || (i < left.length && left[i] < right[j])){
                array[k] = left[i];
                i++;
            }
            else{
                array[k] = right[j];
                j++;
            }
        }
    }

    // O(n^2) performance
    static void quickSort(int[] array, int low, int high) {
        if (low < high){
            int pivot = array[high];
            int i = low -1;
            for (int j = low; j <= high; j++){
                if (array[j] <= pivot){
                    i++;
                    if (i != j){
                        // swap A[i} with A[j]
                        int temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                }
            }
            quickSort(array,low,i-1);
            quickSort(array,i+1,high);
        }
    }

    // O(n+k) performance with O(n+k) space. required values > 0
    static int[] countingSort(int[] keys, int k){
        int[] count = new int[k];
        int[] output = new int[keys.length];
        for (int i : keys) {
            count[i] += 1;
        }
        for (int i = 1; i < k; i++) {
            count[i] += count[i-1];
        }
        for (int i = keys.length - 1; i >= 0; i--) {
            output[count[keys[i]]-1] = keys[i];
            count[keys[i]]--;
        }
        return output;
    }

    // O(n lg n) performance
    static int[] heapSort(int[] ar){
        return ar;
    }


    //MAX SUB ARRAY
    // O(n^2)
    static class Tuple{
        int low, high, sum;
        Tuple(int lo, int hi, int s){
            low = lo;
            high = hi;
            sum = s;
        }

        @Override
        public String toString(){
            return low + " " + high + " " + sum;
        }
    }
    static Tuple bruteMaxSubarray(int[] arr, int low, int high){
        int lowb = 0, highb = 0, sumb = 0, sum = 0;
        for (int i = low; i <= high; i++) {
            sum = arr[i];
            if (sum > sumb) {
                sumb = sum;
                lowb = i;
                highb = i;
            }
            for (int j = i+1; j <= high; j++){
                sum += arr[j];
                if (sum > sumb){
                    sumb = sum;
                    lowb = i;
                    highb = j;
                }
            }
        }
        return new Tuple(lowb, highb, sumb);
    }

    // O(n lg n)
    static Tuple findMaxSubarray(int[] arr, int low, int mid, int high){
        int sum = 0, leftSum = 0, maxLeft = mid, rightSum =0, maxRight = mid;
        for (int i = mid; i >= low; i--) {
            sum += arr[i];
            if (sum > leftSum){
                leftSum = sum;
                maxLeft = i;
            }
        }
        sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum += arr[i];
            if (sum > rightSum){
                rightSum = sum;
                maxRight = i;
            }
        }
        return new Tuple(maxLeft,maxRight,leftSum+rightSum);
    }
    static Tuple findMaxSubarray(int[] arr, int low, int high){
        if (low == high){
            return new Tuple(low, high, arr[low]);
        }

        else if (arr.length <= 160){
            return bruteMaxSubarray(arr,low,high);
        }
        else {
            int mid = (low + high)/2;
            Tuple left = findMaxSubarray(arr,low,mid);
            Tuple right = findMaxSubarray(arr,mid+1,high);
            Tuple cross = findMaxSubarray(arr,low,mid,high);
            if (left.sum >= right.sum && left.sum >= cross.sum){
                return left;
            }
            else if(right.sum >= left.sum && right.sum >= cross.sum){
                return right;
            }
            else return cross;
        }
    }

    // O(n)
    static Tuple linearMaxSubarray(int[] arr, int low, int high){
        int sum = 0, left = 0, maxSum = 0, maxL = 0, maxR  = 0;
        for (int i = 0; i < high; i++) {
            sum += arr[i];
            if (sum <= 0){
                sum = 0;
                left = i + 1;
            }
            else if (sum > maxSum){
                maxL = left;
                maxR = i;
                maxSum = sum;
            }
        }
        return new Tuple(maxL,maxR,maxSum);
    }


    //MATRIX MULTIPLICATION
    // O(n^3)
    static int[][] squareMatrixMultiply(int[][] a, int[][] b){
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    // O(n^2.81) n needs to be a power of 2
    static int[][] matrixMultStrassen(int[][] a, int ay, int ax, int[][] b, int by, int bx, int n){
        int[][] c = new int[n][n];
        /* base case*/
        if(n == 1){
            c[0][0] = a[ay][ax] * b[by][bx];
        }
        else{
            /* 1 Divide the input matrices A and B and output matrix C into n/2 x n/2 submatrices,
         as in equation (4.9). This step takes ‚O(1)/ time by index calculation,
         just as in SQUARE-MATRIX-MULTIPLY-RECURSIVE.*/
            int n2 = n/2;
            int[] a11 = {ay,ax};
            int[] a12 = {ay,ax+n2};
            int[] a21 = {ay+n2,ax};
            int[] a22 = {ay+n2,ax+n2};
            int[] b11 = {by,bx};
            int[] b12 = {by,bx+n2};
            int[] b21 = {by+n2,bx};
            int[] b22 = {by+n2,bx+n2};
        /* 2 Create 10 matrices S1; S2; : : : ; S10,
        each of which is n/2 n/2 and is the sum or difference of two matrices created in step 1.
        We can create all 10 matrices in ‚On2/ time*/
            int[][][] s = new int[10][n2][n2];
            s[0] = matrixSub(b,b12,b,b22,n2);
            s[1] = matrixAdd(a,a11,a,a12,n2);
            s[2] = matrixAdd(a,a21,a,a22,n2);
            s[3] = matrixSub(b,b21,b,b11,n2);
            s[4] = matrixAdd(a,a11,a,a22,n2);
            s[5] = matrixAdd(b,b11,b,b22,n2);
            s[6] = matrixSub(a,a12,a,a22,n2);
            s[7] = matrixAdd(b,b21,b,b22,n2);
            s[8] = matrixSub(a,a11,a,a21,n2);
            s[9] = matrixAdd(b,b11,b,b12,n2);
        /* 3 Using the submatrices created in step 1 and the 10 matrices created in step 2,
        recursively compute seven matrix products P1; P2; : : : ; P7.
        Each matrix Pi is n/2 x n/2*/
            int[][][] p = new int[7][n2][n2];
            p[0] = matrixMultStrassen(a,a11[0],a11[1],s[0],0,0,n2);
            p[1] = matrixMultStrassen(s[1],0,0,b,b22[0],b22[1],n2);
            p[2] = matrixMultStrassen(s[2],0,0,b,b11[0],b11[1],n2);
            p[3] = matrixMultStrassen(a,a22[0],a22[1],s[3],0,0,n2);
            p[4] = matrixMultStrassen(s[4],0,0,s[5],0,0,n2);
            p[5] = matrixMultStrassen(s[6],0,0,s[7],0,0,n2);
            p[6] = matrixMultStrassen(s[8],0,0,s[9],0,0,n2);
        /* 4 Compute the desired submatrices C11; C12; C21; C22 of the result matrix C
        by adding and subtracting various combinations of the Pi matrices.
        We can compute all four submatrices in ‚On*2 time*/
            int[][] c11 = matrixAdd(
                        matrixSub(
                                matrixAdd(p[4],p[3]),
                                p[1]),
                        p[5]);
            int[][] c12 = matrixAdd(p[0],p[1]);
            int[][] c21 = matrixAdd(p[2],p[3]);
            int[][] c22 = matrixSub(
                        matrixSub(
                                matrixAdd(p[4],p[0]),
                                p[2]),
                        p[6]);
        /* combine c11 ... c22*/
            for (int i = 0; i <n2; i++) {
                for (int j = 0; j < n2; j++) {
                    c[i][j] = c11[i][j];
                }
            }
            for (int i = 0; i <n2; i++) {
                for (int j = 0; j < n2; j++) {
                    c[i][j+n2] = c12[i][j];
                }
            }
            for (int i = 0; i <n2; i++) {
                for (int j = 0; j < n2; j++) {
                    c[i+n2][j] = c21[i][j];
                }
            }
            for (int i = 0; i <n2; i++) {
                for (int j = 0; j < n2; j++) {
                    c[i+n2][j+n2] = c22[i][j];
                }
            }
        }
        return c;
    }

    // O(n^2)
    static int[][] matrixAdd(int[][] a, int[][] b){
        return matrixAdd(a,new int[]{0,0},b,new int[]{0,0},a.length);
    }
    static int[][] matrixAdd(int[][] a, int[] ac, int[][] b, int[] bc, int n){
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i+ac[0]][j+ac[1]] + b[i+bc[0]][j+bc[1]];
            }
        }
        return c;
    }
    static int[][] matrixSub(int[][] a, int[][] b){
        return matrixSub(a,new int[]{0,0},b,new int[]{0,0},a.length);
    }
    static int[][] matrixSub(int[][] a, int[] ac, int[][] b, int[] bc, int n){
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i+ac[0]][j+ac[1]] - b[i+bc[0]][j+bc[1]];
            }
        }
        return c;
    }



    public static void main(String[] args) throws IOException {
        int[] intTest = new int[]{0,9,8,7,5,2,3,6,1,8,5,4,9,6,3,7};
        String[] strTest = new String[]{"1 1000000000","17 35"};
        Character[] charTest = new Character[]{'d','b','a','c'};
        Boolean[] boolTest = new Boolean[]{true,false,false,true,false};

        System.out.println();
    }


    // MISC
    private static String[] getInputLine() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        return in.readLine().split(" ");
    }

    private static int[] getInputArr() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] keys = new int[n];
        for(int i=0;i<n;i++){
            String[] tmp = in.readLine().split(" ");
            keys[i] = Integer.parseInt(tmp[0]);
        }
        return keys;
    }

    static <T> void print(T[] a){
        StringBuffer sb = new StringBuffer();
        for (T t : a){
            sb.append(t + " ");
        }
        System.out.println(sb);
    }

    static void print(int[] ar) {
        StringBuffer sb = new StringBuffer();
        for (int n : ar){
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }
}
