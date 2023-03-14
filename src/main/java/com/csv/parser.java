package com.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class parser {


  public static void main(String[] fileLocations) throws Exception {
    List<Transaction> transactions = getTransactions(fileLocations);
    transactions.sort(Comparator.comparing(Transaction::getDetail));
    writeTransactionsToCsv(transactions);
  }

  private static void writeTransactionsToCsv(List<Transaction> transactions) throws IOException {
    File file = new File("transactions.csv");
    log.info("Created new file? " + file.createNewFile());
    FileWriter writer = new FileWriter(file);
    for (Transaction transaction : transactions) {
      writer.write(transaction + System.lineSeparator());
    }
    writer.close();
  }

  private static List<Transaction> getTransactions(String[] fileLocations) throws IOException, ParseException {
    List<Transaction> transactions = new LinkedList<>();
    log.info("Loading transactions from {} file(s)", fileLocations.length);
    for (String fileLocation : fileLocations) {
      BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
      String[] headers = reader.readLine().split(",");
      for (String l = reader.readLine(); l != null && !l.isBlank(); l = reader.readLine()) {
        String[] values = l.split(",", -1);
        transactions.add(new Transaction(headers, values));
      }
    }
    log.info("Loaded {} transactions", transactions.size());
    return transactions;
  }
}
