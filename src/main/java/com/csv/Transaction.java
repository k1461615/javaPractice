package com.csv;

import lombok.Data;

@Data
public class Transaction {
  private String date;
  private String detail;
  private Double amount;
  private String category;
  private String categoryType;

  @Override
  public String toString() {
    return String.format("%s,%s,%s,%s,%s", date, detail, amount, category, categoryType);
  }
}
