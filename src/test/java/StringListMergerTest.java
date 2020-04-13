import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    String fragmented = "ABC;DEF;CG;BCD;DEF;CDEFCG;EFCG;EF";

    // when
    String result = listMerger.mergeFragmented(fragmented);

    // then
    assertEquals("ABCDEFCG", result);
  }

  @Test
  void mergeFragmentedCase1() {
    // given
    String fragmented = "O draconia;conian devil! Oh la;h lame sa;saint!";
    String expected = "O draconian devil! Oh lame saint!";

    // when
    String result = listMerger.mergeFragmented(fragmented);

    // then
    assertEquals(expected, result);
  }

  @Test
  void mergeFragmentedCase2() {
    // given
    String fragmented = "m quaerat voluptatem.;pora incidunt ut labore et d;, consectetur, adipisci velit;olore magnam aliqua;idunt ut labore et dolore magn;uptatem.;i dolorem ipsum qu;iquam quaerat vol;psum quia dolor sit amet, consectetur, a;ia dolor sit amet, conse;squam est, qui do;Neque porro quisquam est, qu;aerat voluptatem.;m eius modi tem;Neque porro qui;, sed quia non numquam ei;lorem ipsum quia dolor sit amet;ctetur, adipisci velit, sed quia non numq;unt ut labore et dolore magnam aliquam qu;dipisci velit, sed quia non numqua;us modi tempora incid;Neque porro quisquam est, qui dolorem i;uam eius modi tem;pora inc;am al";
    String expected = "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.";

    // when
    String result = listMerger.mergeFragmented(fragmented);

    // then
    assertEquals(expected, result);
  }
}