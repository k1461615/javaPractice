package com.mainPackage;

import java.util.Arrays;

public class Sort {

  private Sort() {
  }

  // O(n^2) comparisons, swaps
  static void insertionSort(int[] array) {
    for (int j = 1; j < array.length; j++) {
      int key = array[j];
      int i = j - 1;

      while (i >= 0 && array[i] > key) {
        array[i + 1] = array[i];
        i = i - 1;
      }
      array[i + 1] = key;
    }
  }

  // O(n log n) performance
  static void mergeSort(int[] array) {
    if (array.length == 1) {
      return;
    }
    int p = array.length / 2;
    int[] left = new int[p];
    int[] right = new int[array.length - p];

    System.arraycopy(array, 0, left, 0, left.length);
    System.arraycopy(array, p, right, 0, right.length);

    mergeSort(left);
    mergeSort(right);

    int i = 0;
    int j = 0;
    for (int k = 0; k < array.length; k++) {
      if (j == right.length || (i < left.length && left[i] < right[j])) {
        array[k] = left[i];
        i++;
      } else {
        array[k] = right[j];
        j++;
      }
    }
  }

  // O(n^2) performance
  static void quickSort(int[] array, int low, int high) {
    if (low < high) {
      int pivot = array[high];
      int i = low - 1;
      for (int j = low; j <= high; j++) {
        if (array[j] <= pivot) {
          i++;
          if (i != j) {
            // swap A[i} with A[j]
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
          }
        }
      }
      quickSort(array, low, i - 1);
      quickSort(array, i + 1, high);
    }
  }

  // O(n+k) performance with O(n+k) space. requires non-negative values
  static int[] countingSort(int[] keys, int k) {
    int[] count = new int[k];
    int[] output = new int[keys.length];
    for (int i : keys) {
      count[i] += 1;
    }
    for (int i = 1; i < k; i++) {
      count[i] += count[i - 1];
    }
    for (int i = keys.length - 1; i >= 0; i--) {
      output[count[keys[i]] - 1] = keys[i];
      count[keys[i]]--;
    }
    return output;
  }

  // O(n lg n) performance
  static int[] heapSort(int[] ar) {
    return ar;
  }


  public static void main(String[] args) {
    int[] intTest = new int[]{0, 9, 8, 7, 5, 2, 3, 6, 1, 8, 5, 4, 9, 6, 3, 7};
    String[] strTest = new String[]{"1 1000000000", "17 35"};
    Character[] charTest = new Character[]{'d', 'b', 'a', 'c'};
    Boolean[] boolTest = new Boolean[]{true, false, false, true, false};

    System.out.println(Arrays.toString(countingSort(intTest, 10)));
  }
}
