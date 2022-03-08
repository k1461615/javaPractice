package com.csv;

public enum SubCategory {
  CLOTHES("Clothes"),
  DEPOSIT("Deposit"),
  ELECTRIC("Electric"),
  EXPERIENCE("Experience"),
  FURNISHING("Furnishing"),
  GAMES("Games"),
  GENERAL("General"),
  GROCERIES("Groceries"),
  GYM("Gym"),
  HEAT("Heat"),
  INTERNET("Internet"),
  MOVING("Moving"),
  OTHER_TRAVEL("Other Travel"),
  PHONE("Phone"),
  PRESENT("Present"),
  RAIL_SERVICES("Rail Services"),
  RENT("Rent"),
  RESTAURANTS("Restaurants"),
  TAX("Tax"),
  THEATRE("Theatre"),
  THEATRICAL_EVENTS("Theatrical Events"),
  WATER("Water");

  private final String value;

  @Override
  public String toString() {
    return value;
  }

  SubCategory(String value) {
    this.value = value;
  }
}