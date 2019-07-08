import java.util.ArrayList;
import java.util.Arrays;

class StringListMerger {
	/**
	 * Reassembles a given set of text fragments into their original sequence.
	 *
	 * @param input a semicolon-separated set of text fragments. Assumes every fragment has length at least 2.
	 * @return the reassembled sequence. Cannot be null.
	 */
	String mergeFragmented(String input) {
		String[] fragments = input.split(";");
		return mergeStrings(fragments);
	}

	String mergeStrings(String[] input) {
		ArrayList<String> fragments = new ArrayList<>(Arrays.asList(input));
		StringMerger merger = new StringMerger();
		String result = fragments.remove(0);

		while (fragments.size() > 0) {
			int maxIndex = 0;
			int maxOverlap = 0;
			String maxResult = "";

			// merge all fragments in the collection to locate the one with maximal overlap
			for (int i = 0; i < fragments.size(); i++) {
				String merged = merger.mergeStrings(result, fragments.get(i));
				int overlap = (result.length() + fragments.get(i).length()) - merged.length();

				if (merged.length() > 0) {
					if (overlap > maxOverlap) {
						maxIndex = i;
						maxOverlap = overlap;
						maxResult = merged;
					}

					// break early if it's a proper subset
					if (result.length() == maxResult.length()) {
						break;
					}
				}
			}
			// merge the fragment which had the maximal overlap
			result = maxResult;
			fragments.remove(maxIndex);
		}

		return result;
	}
}
