package com.mainPackage;

public class Search {

  private Search() {
  }

  // O(n) performance
  static int linearSearch(int[] array, int value) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == value) {
        return i;
      }
    }
    // value not found. aka return null
    return -1;
  }

  // O(lg n) performance. requires sorted array
  static int binarySearch(int[] array, int val) {
    int left = 0;
    int right = array.length - 1;

    while (left <= right) {
      int mid = (left + right) / 2;

      if (array[mid] < val) {
        left = mid + 1;
      } else if (array[mid] > val) {
        right = mid - 1;
      } else {
        return mid;
      }
    }

    return -1;
  }

  // O(n log n) performance (mergeSort + binarySearch)
  static boolean sumSearch(int[] array, int value) {
    Sort.mergeSort(array);

    for (int i : array) {
      if (binarySearch(array, value - array[i]) >= 0) {
        return true;
      }
    }
    return false;
  }
}
