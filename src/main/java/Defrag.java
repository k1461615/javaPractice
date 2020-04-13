import java.io.BufferedReader;
import java.io.FileReader;

public class Defrag {

  public static void main(String[] args) {
    StringListMerger merger = new StringListMerger();

    try (BufferedReader in = new BufferedReader(new FileReader(args[0]))) {
      String fragmentProblem;
      while ((fragmentProblem = in.readLine()) != null) {
        System.out.println(merger.mergeFragmented(fragmentProblem));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
