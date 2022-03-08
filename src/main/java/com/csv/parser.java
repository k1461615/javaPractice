package com.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class parser {

  private static final List<Transaction> transactions = new LinkedList<>();
  private static final String[] GROCERIES = {
      "WAITROSE",
      "SAINSBURY",
      "TESCO",
      "CO-OP",
      "MYPROTEIN",
      "FOOD SHOP",
      "SUPERMARK"
  };
  private static final String[] RAIL_SERVICES = {
      "SWRAILWAY"
  };
  private static final String[] OTHER_TRAVEL = {
      "UBER TRIP",
      "TFL TRAVEL CHARGE"
  };
  private static final String[] RESTAURANTS = {
      "DOMINOS",
      "COSTA",
      "NANDOS",
      "TAVERN",
      "BAR",
      "Spoons",
      "FIVE GUYS"
  };
  private static final String[] RENT = {
      "RENT",
      "Hastings London"
  };

  public static void main(String[] args) throws Exception {
    for (String arg : args) {
      BufferedReader reader = new BufferedReader(new FileReader(arg));
      String[] headers = reader.readLine().split(",");
      for (String l = reader.readLine(); l != null; l = reader.readLine()) {
        String[] line = l.split(",", -1);
        Transaction t = new Transaction();
        for (int i = 0; i < line.length; i++) {
          String header = headers[i];
          String data = line[i];
          if (!data.isEmpty()) {
            if (header.matches("Date")) {
              String format = parseDate(data);
              t.setDate(format);
            } else if (header.matches("Details|Description")) {
              t.setDetail(data);
            } else if (header.matches("In")) {
              t.setAmount(Double.parseDouble(data) * -1);
            } else if (header.matches("Out|Amount")) {
              t.setAmount(Double.parseDouble(data));
            }
          }
        }
        transactions.add(t);
      }
    }
    transactions.sort(Comparator.comparing(Transaction::getDetail));
    File file = new File("transactions.csv");
    System.out.println("Created new file? " + file.createNewFile());
    FileWriter writer = new FileWriter(file);
    for (Transaction transaction : transactions) {
      fillCategory(transaction);
      writer.write(transaction + System.lineSeparator());
    }
    writer.close();
  }

  private static void fillCategory(Transaction transaction) {
    String detail = transaction.getDetail().toLowerCase();
    for (String pattern : GROCERIES) {
      if (detail.contains(pattern.toLowerCase())) {
        transaction.setCategory(Category.GENERAL_PURCHASES);
        transaction.setSubCategory(SubCategory.GROCERIES);
        return;
      }
    }
    for (String pattern : RAIL_SERVICES) {
      if (detail.contains(pattern.toLowerCase())) {
        transaction.setCategory(Category.TRAVEL);
        transaction.setSubCategory(SubCategory.RAIL_SERVICES);
        return;
      }
    }
    for (String pattern : OTHER_TRAVEL) {
      if (detail.contains(pattern.toLowerCase())) {
        transaction.setCategory(Category.TRAVEL);
        transaction.setSubCategory(SubCategory.OTHER_TRAVEL);
        return;
      }
    }
    for (String pattern : RESTAURANTS) {
      if (detail.contains(pattern.toLowerCase())) {
        transaction.setCategory(Category.ENTERTAINMENT);
        transaction.setSubCategory(SubCategory.RESTAURANTS);
        return;
      }
    }
    for (String pattern : RENT) {
      if (detail.contains(pattern.toLowerCase())) {
        transaction.setCategory(Category.HOUSING);
        transaction.setSubCategory(SubCategory.RENT);
        return;
      }
    }
    if (detail.contains("electric bill")) {
      transaction.setCategory(Category.UTILITIES);
      transaction.setSubCategory(SubCategory.ELECTRIC);
      return;
    }
    if (detail.contains("internet")) {
      transaction.setCategory(Category.UTILITIES);
      transaction.setSubCategory(SubCategory.INTERNET);
      return;
    }
    if (detail.contains("water bill")) {
      transaction.setCategory(Category.UTILITIES);
      transaction.setSubCategory(SubCategory.WATER);
      return;
    }
    if (detail.contains("pure gym")) {
      transaction.setCategory(Category.ENTERTAINMENT);
      transaction.setSubCategory(SubCategory.GYM);
      return;
    }
    if (detail.contains("h3g")) {
      transaction.setCategory(Category.UTILITIES);
      transaction.setSubCategory(SubCategory.PHONE);
      return;
    }
    if (detail.contains("tax bill")) {
      transaction.setCategory(Category.UTILITIES);
      transaction.setSubCategory(SubCategory.TAX);
    }
  }

  private static String parseDate(String data) throws ParseException {
    Date parse;
    try {
      parse = new SimpleDateFormat("d MMM y").parse(data);
    } catch (ParseException e) {
      parse = new SimpleDateFormat("dd/MM/yyyy").parse(data);
    }
    return new SimpleDateFormat("y-MM-dd").format(parse);
  }

}
