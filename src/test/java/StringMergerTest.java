import static org.junit.jupiter.api.Assertions.*;

class StringMergerTest {

	@org.junit.jupiter.api.Test
	void mergeString() {
		StringMerger merger = new StringMerger();

		// intersect is at the end of a and the start of b with 1 char of overlap
		assertEquals("abcde", merger.mergeStrings("abc", "cde"));
		// intersect is at the end of a and the start of b with more than 1 char of overlap
		assertEquals("abcde", merger.mergeStrings("abcd", "cde"));
		// b is a proper subset of a
		assertEquals("abcde", merger.mergeStrings("abcde", "bcd"));
		// intersect is at the end of a and a contains duplication
		assertEquals("abcdcde", merger.mergeStrings("abcdcd", "cde"));
		// intersect is at the end of b and b contains duplication
		assertEquals("abcde", merger.mergeStrings("cde", "abc"));
	}
}