package com.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class parser {

  public static void main(String[] args) throws Exception {
    for (String arg : args) {
      File file = new File(arg);
      BufferedReader reader = new BufferedReader(new FileReader(arg));
      String[] headers = reader.readLine().split(",");
      String l = reader.readLine();
      while (l != null) {
        String[] line = l.split(",", -1);
        Transaction t = new Transaction();
        for (int i = 0; i < 6; i++) {
          String header = headers[i];
          String data = line[i];
          if (!data.isEmpty() && header.matches("Date")) {
            Date parse;
            try {
              parse = new SimpleDateFormat("d MMM y").parse(data);
            } catch (ParseException e) {
              parse = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            }
            String format = new SimpleDateFormat("y-M-d").format(parse);
            t.setDate(format);
          } else if (!data.isEmpty() && header.matches("Details|Description")) {
            t.setDetail(data);
          } else if (!data.isEmpty() && header.matches("In")) {
            t.setAmount(Double.parseDouble(data) * -1);
          } else if (!data.isEmpty() && header.matches("Out|Amount")) {
            t.setAmount(Double.parseDouble(data));
          }
        }
        l = reader.readLine();
        System.out.println(t);
      }
      System.out.println("done");
    }
  }

}
