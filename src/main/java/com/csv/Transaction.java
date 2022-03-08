package com.csv;

import lombok.Data;

@Data
public class Transaction {
  private String date;
  private String detail;
  private Double amount;
  private Category category;
  private SubCategory subCategory;

  @Override
  public String toString() {
    String c = category != null ? category.toString() : "unknown";
    String sc = subCategory != null ? subCategory.toString() : "unknown";
    return String.format("%s,%s,%s,%s,%s", date, detail, amount, c, sc);
  }
}
