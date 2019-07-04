import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringListMergerTest {

	private StringListMerger listMerger;

	@BeforeEach
	void setUp() {
		listMerger = new StringListMerger();
	}

	@Test
	void mergeStrings() {
		// given
		String[] fragments = new String[]{
				"ABC",
				"BCD",
				"DEF",
				"EFG",
				"EF"
		};

		// when
		String result = listMerger.mergeStrings(fragments);

		// then
		assertEquals("ABCDEFG", result);
	}

	@Test
	void mergeFragmented() {
		// given
		String fragmented = "ABC;DEF;ABC;BCD;DEF;ABCDEF;EFG;EF";

		// when
		String result = listMerger.mergeFragmented(fragmented);

		// then
		assertEquals("ABCDEFG", result);
	}
}