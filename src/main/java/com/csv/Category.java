package com.csv;

import lombok.Data;

@Data
public class Category {
  private String label;
  private SubCategory[] subcategories;
}
