class StringMerger {

  /**
   * Merges two sequences removing characters where the sequences intersect.
   *
   * @param inputA the first sequence to merge
   * @param inputB the second sequence to merge
   * @return the union of the input sequences or the empty string if an intersection is not found.
   * Not null.
   */
  String mergeStrings(String inputA, String inputB) {
    char[] a = inputA.toCharArray();
    char[] b = inputB.toCharArray();

    String merged = mergeCharArrays(a, b);
    if (merged.length() == 0) {
      merged = mergeCharArrays(b, a);
    }
    return merged;
  }

  private String mergeCharArrays(char[] a, char[] b) {
    char[] result = new char[a.length + b.length];

    for (int i = 0; i < a.length; i++) {
      // check if first char of b matches current a char
      if (a[i] == b[0]) {
        // check if the rest of a matches b
        for (int j = i; j < a.length; j++) {
          int bi = j - i;

          if (a[j] != b[bi]) {
            break;
          } else if (j == a.length - 1) {
            // a is a partial overlap of b
            System.arraycopy(a, 0, result, 0, j);
            System.arraycopy(b, bi, result, j, b.length - bi);
            break;
          } else if (bi == b.length - 1) {
            // b is a proper subset of a
            System.arraycopy(a, 0, result, 0, a.length);
            break;
          }
        }
      }
    }
    // trim to get rid of default null chars in array
    return new String(result).trim();
  }
}
