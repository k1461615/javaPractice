package com.csv;

public enum Category {
  ENTERTAINMENT("Entertainment"),
  HOUSING("Housing"),
  TRAVEL("Travel"),
  GENERAL_PURCHASES("General Purchases"),
  TAX_RETURN("Tax Return"),
  UTILITIES("Utilities");

  private final String value;

  @Override
  public String toString() {
    return value;
  }

  Category(String value) {
    this.value = value;
  }
}
