package com.csv;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Transaction {
  private String date;
  private String detail;
  private Double amount;
  private String category;
  private String subCategory;

  private static final Category[] CATEGORIES;

  static {
    Gson gson = new Gson();
    InputStream inputStream = parser.class.getClassLoader().getResourceAsStream("categories.json");
    JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
    CATEGORIES = gson.fromJson(reader, Category[].class);
  }

  public Transaction(String[] headers, String[] values) throws ParseException {
    for (int i = 0; i < values.length; i++) {
      String header = headers[i];
      String value = values[i];
      if (!value.isEmpty()) {
        if (header.matches("Date")) {
          date = parseDate(value);
        } else if (header.matches("Details|Description|Memo")) {
          detail = value;
        } else if (header.matches("In")) {
          amount = Double.parseDouble(value) * -1;
        } else if (header.matches("Out|Amount")) {
          amount = Double.parseDouble(value);
          if (headers[0].equals("Number")){
            amount = amount * -1;
          }
        }
      }
    }
    for (Category c : CATEGORIES) {
      for (SubCategory sc : c.getSubcategories()) {
        for (String pattern : sc.getPatterns()) {
          if (detail.toLowerCase().contains(pattern.toLowerCase())) {
            category = c.getLabel();
            subCategory = sc.getLabel();
            return;
          }
        }
      }
    }
  }

  public static String parseDate(String data) throws ParseException {
    Date parse;
    try {
      parse = new SimpleDateFormat("d MMM y").parse(data);
    } catch (ParseException e) {
      parse = new SimpleDateFormat("dd/MM/yyyy").parse(data);
    }
    return new SimpleDateFormat("y-MM-dd").format(parse);
  }

  @Override
  public String toString() {
    String c = category != null ? category : "unknown";
    String sc = subCategory != null ? subCategory : "unknown";
    return String.format("%s,%s,%s,%s,%s", date, detail, amount, c, sc);
  }
}
