package com.mainPackage;

public class MaxSubarray {

  private MaxSubarray() {
  }

  // O(n^2)
  static class Tuple {

    int low, high, sum;

    Tuple(int lo, int hi, int s) {
      low = lo;
      high = hi;
      sum = s;
    }

    @Override
    public String toString() {
      return low + " " + high + " " + sum;
    }
  }

  static Tuple bruteMaxSubarray(int[] arr, int low, int high) {
    int lowb = 0, highb = 0, sumb = 0, sum = 0;
    for (int i = low; i <= high; i++) {
      sum = arr[i];
      if (sum > sumb) {
        sumb = sum;
        lowb = i;
        highb = i;
      }
      for (int j = i + 1; j <= high; j++) {
        sum += arr[j];
        if (sum > sumb) {
          sumb = sum;
          lowb = i;
          highb = j;
        }
      }
    }
    return new Tuple(lowb, highb, sumb);
  }

  // O(n lg n)
  static Tuple findMaxSubarray(int[] arr, int low, int mid, int high) {
    int sum = 0, leftSum = 0, maxLeft = mid, rightSum = 0, maxRight = mid;
    for (int i = mid; i >= low; i--) {
      sum += arr[i];
      if (sum > leftSum) {
        leftSum = sum;
        maxLeft = i;
      }
    }
    sum = 0;
    for (int i = mid + 1; i <= high; i++) {
      sum += arr[i];
      if (sum > rightSum) {
        rightSum = sum;
        maxRight = i;
      }
    }
    return new Tuple(maxLeft, maxRight, leftSum + rightSum);
  }

  static Tuple findMaxSubarray(int[] arr, int low, int high) {
    if (low == high) {
      return new Tuple(low, high, arr[low]);
    } else if (arr.length <= 160) {
      return bruteMaxSubarray(arr, low, high);
    } else {
      int mid = (low + high) / 2;
      Tuple left = findMaxSubarray(arr, low, mid);
      Tuple right = findMaxSubarray(arr, mid + 1, high);
      Tuple cross = findMaxSubarray(arr, low, mid, high);
      if (left.sum >= right.sum && left.sum >= cross.sum) {
        return left;
      } else if (right.sum >= left.sum && right.sum >= cross.sum) {
        return right;
      } else {
        return cross;
      }
    }
  }

  // O(n)
  static Tuple linearMaxSubarray(int[] arr, int low, int high) {
    int sum = 0, left = 0, maxSum = 0, maxL = 0, maxR = 0;
    for (int i = 0; i < high; i++) {
      sum += arr[i];
      if (sum <= 0) {
        sum = 0;
        left = i + 1;
      } else if (sum > maxSum) {
        maxL = left;
        maxR = i;
        maxSum = sum;
      }
    }
    return new Tuple(maxL, maxR, maxSum);
  }
}
