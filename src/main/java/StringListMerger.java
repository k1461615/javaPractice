import java.util.ArrayList;
import java.util.Arrays;

public class StringListMerger {
	String mergeFragmented(String input) {
		String[] fragments = input.split(";");
		return mergeStrings(fragments);
	}

	String mergeStrings(String[] input) {
		ArrayList<String> fragments = new ArrayList<>(Arrays.asList(input));
		StringMerger merger = new StringMerger.StringMergerImpl();
		String answer = fragments.remove(0);

		while (fragments.size() > 0) {
			int maxPos = 0;
			String maxResult = "";
			for (int i = 0; i < fragments.size(); i++) {
				String merged = merger.mergeStrings(answer, fragments.get(i));

				if (merged.length() > 0) {
					if (maxResult.length() == 0 || merged.length() < maxResult.length()) {
						maxPos = i;
						maxResult = merged;
					}

					// break early if it's a proper subset
					if (answer.length() == maxResult.length()) {
						break;
					}
				}
			}
			answer = maxResult;
			fragments.remove(maxPos);
		}

		return answer;
	}
}
