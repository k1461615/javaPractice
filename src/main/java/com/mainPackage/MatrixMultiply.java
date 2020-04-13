package com.mainPackage;

public class MatrixMultiply {

  private MatrixMultiply() {
  }

  // O(n^3)
  static int[][] squareMatrixMultiply(int[][] a, int[][] b) {
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
  static int[][] strassenMatrixMultiply(int[][] a, int ay, int ax, int[][] b, int by, int bx,
      int n) {
    int[][] c = new int[n][n];
    /* base case*/
    if (n == 1) {
      c[0][0] = a[ay][ax] * b[by][bx];
    } else {
            /* 1 Divide the input matrices A and B and output matrix C into n/2 x n/2 submatrices,
         as in equation (4.9). This step takes ‚O(1)/ time by index calculation,
         just as in SQUARE-MATRIX-MULTIPLY-RECURSIVE.*/
      int n2 = n / 2;
      int[] a11 = {ay, ax};
      int[] a12 = {ay, ax + n2};
      int[] a21 = {ay + n2, ax};
      int[] a22 = {ay + n2, ax + n2};
      int[] b11 = {by, bx};
      int[] b12 = {by, bx + n2};
      int[] b21 = {by + n2, bx};
      int[] b22 = {by + n2, bx + n2};
        /* 2 Create 10 matrices S1; S2; : : : ; S10,
        each of which is n/2 n/2 and is the sum or difference of two matrices created in step 1.
        We can create all 10 matrices in ‚On2/ time*/
      int[][][] s = new int[10][n2][n2];
      s[0] = matrixSub(b, b12, b, b22, n2);
      s[1] = matrixAdd(a, a11, a, a12, n2);
      s[2] = matrixAdd(a, a21, a, a22, n2);
      s[3] = matrixSub(b, b21, b, b11, n2);
      s[4] = matrixAdd(a, a11, a, a22, n2);
      s[5] = matrixAdd(b, b11, b, b22, n2);
      s[6] = matrixSub(a, a12, a, a22, n2);
      s[7] = matrixAdd(b, b21, b, b22, n2);
      s[8] = matrixSub(a, a11, a, a21, n2);
      s[9] = matrixAdd(b, b11, b, b12, n2);
        /* 3 Using the submatrices created in step 1 and the 10 matrices created in step 2,
        recursively compute seven matrix products P1; P2; : : : ; P7.
        Each matrix Pi is n/2 x n/2*/
      int[][][] p = new int[7][n2][n2];
      p[0] = strassenMatrixMultiply(a, a11[0], a11[1], s[0], 0, 0, n2);
      p[1] = strassenMatrixMultiply(s[1], 0, 0, b, b22[0], b22[1], n2);
      p[2] = strassenMatrixMultiply(s[2], 0, 0, b, b11[0], b11[1], n2);
      p[3] = strassenMatrixMultiply(a, a22[0], a22[1], s[3], 0, 0, n2);
      p[4] = strassenMatrixMultiply(s[4], 0, 0, s[5], 0, 0, n2);
      p[5] = strassenMatrixMultiply(s[6], 0, 0, s[7], 0, 0, n2);
      p[6] = strassenMatrixMultiply(s[8], 0, 0, s[9], 0, 0, n2);
        /* 4 Compute the desired submatrices C11; C12; C21; C22 of the result matrix C
        by adding and subtracting various combinations of the Pi matrices.
        We can compute all four submatrices in ‚On*2 time*/
      int[][] c11 = matrixAdd(
          matrixSub(
              matrixAdd(p[4], p[3]),
              p[1]),
          p[5]);
      int[][] c12 = matrixAdd(p[0], p[1]);
      int[][] c21 = matrixAdd(p[2], p[3]);
      int[][] c22 = matrixSub(
          matrixSub(
              matrixAdd(p[4], p[0]),
              p[2]),
          p[6]);
      /* combine c11 ... c22*/
      for (int i = 0; i < n2; i++) {
        for (int j = 0; j < n2; j++) {
          c[i][j] = c11[i][j];
        }
      }
      for (int i = 0; i < n2; i++) {
        for (int j = 0; j < n2; j++) {
          c[i][j + n2] = c12[i][j];
        }
      }
      for (int i = 0; i < n2; i++) {
        for (int j = 0; j < n2; j++) {
          c[i + n2][j] = c21[i][j];
        }
      }
      for (int i = 0; i < n2; i++) {
        for (int j = 0; j < n2; j++) {
          c[i + n2][j + n2] = c22[i][j];
        }
      }
    }
    return c;
  }

  // O(n^2)
  static int[][] matrixAdd(int[][] a, int[][] b) {
    return matrixAdd(a, new int[]{0, 0}, b, new int[]{0, 0}, a.length);
  }

  static int[][] matrixAdd(int[][] a, int[] ac, int[][] b, int[] bc, int n) {
    int[][] c = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        c[i][j] = a[i + ac[0]][j + ac[1]] + b[i + bc[0]][j + bc[1]];
      }
    }
    return c;
  }

  static int[][] matrixSub(int[][] a, int[][] b) {
    return matrixSub(a, new int[]{0, 0}, b, new int[]{0, 0}, a.length);
  }

  static int[][] matrixSub(int[][] a, int[] ac, int[][] b, int[] bc, int n) {
    int[][] c = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        c[i][j] = a[i + ac[0]][j + ac[1]] - b[i + bc[0]][j + bc[1]];
      }
    }
    return c;
  }
}
