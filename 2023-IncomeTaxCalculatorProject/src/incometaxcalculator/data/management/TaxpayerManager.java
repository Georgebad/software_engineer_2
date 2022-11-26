package incometaxcalculator.data.management;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.io.TXTInfoWriter;
import incometaxcalculator.data.io.TXTLogWriter;
import incometaxcalculator.data.io.XMLFileReader;
import incometaxcalculator.data.io.XMLInfoWriter;
import incometaxcalculator.data.io.XMLLogWriter;
import incometaxcalculator.exceptions.ReceiptAlreadyExistsException;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TaxpayerManager {

  private static HashMap<Integer, Taxpayer> taxpayerHashMap = new HashMap<Integer, Taxpayer>(0);
  private static HashMap<Integer, Integer> receiptOwnerTRN = new HashMap<Integer, Integer>(0);
  List<Integer> calculateInput1= Arrays.asList(36080,90000,143350,254240);
  List<Double> calculateOutput1=Arrays.asList(0.0535, 1930.28, 0.0705, 5731.64, 0.0705, 9492.82, 0.0785, 18197.69, 0.0985);
  List<Integer> calculateInput2= Arrays.asList(18040,71680,90000,127120);
  List<Double> calculateOutput2=Arrays.asList(0.0535, 965.14, 0.0705, 4746.76, 0.0785, 6184.88, 0.0785, 9098.80, 0.0985);
  List<Integer> calculateInput3= Arrays.asList(24680,81080,90000,152540);
  List<Double> calculateOutput3=Arrays.asList(0.0535, 1320.38, 0.0705, 5296.58, 0.0785, 5996.80, 0.0785, 10906.19, 0.0985);
  List<Integer> calculateInput4= Arrays.asList(30390,90000,122110,203390);
  List<Double> calculateOutput4=Arrays.asList(0.0535, 1625.87, 0.0705, 5828.38, 0.0705, 8092.13, 0.0785, 14472.61, 0.0985);


  public void createTaxpayer(String fullname, int taxRegistrationNumber, String status,
      float income) throws WrongTaxpayerStatusException {

    if (status.equals("Married Filing Jointly")) {
      taxpayerHashMap.put(taxRegistrationNumber,
          new MarriedFilingJointlyTaxpayer(fullname, taxRegistrationNumber, income,calculateInput1, calculateOutput1));
    } else if (status.equals("Married Filing Separately")) {
      taxpayerHashMap.put(taxRegistrationNumber,
          new MarriedFilingSeparatelyTaxpayer(fullname, taxRegistrationNumber, income,calculateInput2, calculateOutput2));
    } else if (status.equals("Single")) {
      taxpayerHashMap.put(taxRegistrationNumber,
          new SingleTaxpayer(fullname, taxRegistrationNumber, income,calculateInput3, calculateOutput3));
    } else if (status.equals("Head of Household")) {
      taxpayerHashMap.put(taxRegistrationNumber,
          new HeadOfHouseholdTaxpayer(fullname, taxRegistrationNumber, income,calculateInput4, calculateOutput4));
    } else {
      throw new WrongTaxpayerStatusException();
    }
  }

  public void createReceipt(int receiptId, String issueDate, float amount, String kind,
      String companyName, String country, String city, String street, int number,
      int taxRegistrationNumber) throws WrongReceiptKindException, WrongReceiptDateException {

    Receipt receipt = new Receipt(receiptId, issueDate, amount, kind,
        new Company(companyName, country, city, street, number));
    taxpayerHashMap.get(taxRegistrationNumber).addReceipt(receipt);
    receiptOwnerTRN.put(receiptId, taxRegistrationNumber);
  }

  public void removeTaxpayer(int taxRegistrationNumber) {
    Taxpayer taxpayer = taxpayerHashMap.get(taxRegistrationNumber);
    taxpayerHashMap.remove(taxRegistrationNumber);
    HashMap<Integer, Receipt> receiptsHashMap = taxpayer.getReceiptHashMap();
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      receiptOwnerTRN.remove(receipt.getId());
    }
  }

  public void addReceipt(int receiptId, String issueDate, float amount, String kind,
      String companyName, String country, String city, String street, int number,
      int taxRegistrationNumber) throws IOException, WrongReceiptKindException,
      WrongReceiptDateException, ReceiptAlreadyExistsException {

    if (containsReceipt(receiptId)) {
      throw new ReceiptAlreadyExistsException();
    }
    createReceipt(receiptId, issueDate, amount, kind, companyName, country, city, street, number,
        taxRegistrationNumber);
    updateFiles(taxRegistrationNumber);
  }

  public void removeReceipt(int receiptId) throws IOException, WrongReceiptKindException {
    taxpayerHashMap.get(receiptOwnerTRN.get(receiptId)).removeReceipt(receiptId);
    updateFiles(receiptOwnerTRN.get(receiptId));
    receiptOwnerTRN.remove(receiptId);
  }

  private void updateFiles(int taxRegistrationNumber) throws IOException {
    if (new File(taxRegistrationNumber + "_INFO.xml").exists()) {
      new XMLInfoWriter().generateFile(taxRegistrationNumber);
    } else {
      new TXTInfoWriter().generateFile(taxRegistrationNumber);
      return;
    }
    if (new File(taxRegistrationNumber + "_INFO.txt").exists()) {
      new TXTInfoWriter().generateFile(taxRegistrationNumber);
    }
  }

  public void saveLogFile(int taxRegistrationNumber, String fileFormat)
      throws IOException, WrongFileFormatException {
    if (fileFormat.equals("txt")) {
      TXTLogWriter writer = new TXTLogWriter();
      writer.generateFile(taxRegistrationNumber);
    } else if (fileFormat.equals("xml")) {
      XMLLogWriter writer = new XMLLogWriter();
      writer.generateFile(taxRegistrationNumber);
    } else {
      throw new WrongFileFormatException();
    }
  }

  public boolean containsTaxpayer(int taxRegistrationNumber) {
    if (taxpayerHashMap.containsKey(taxRegistrationNumber)) {
      return true;
    }
    return false;
  }

  public boolean containsTaxpayer() {
    if (taxpayerHashMap.isEmpty()) {
      return false;
    }
    return true;
  }

  public boolean containsReceipt(int id) {
    if (receiptOwnerTRN.containsKey(id)) {
      return true;
    }
    return false;

  }

  public Taxpayer getTaxpayer(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber);
  }

  public void loadTaxpayer(String fileName)
      throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
      WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {

    String ending[] = fileName.split("\\.");
    if (ending[1].equals("txt")) {
      FileReader reader = new TXTFileReader();
      reader.readFile(fileName);
    } else if (ending[1].equals("xml")) {
      FileReader reader = new XMLFileReader();
      reader.readFile(fileName);
    } else {
      throw new WrongFileEndingException();
    }
  }

  public String getTaxpayerName(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getFullname();
  }

  public String getTaxpayerStatus(int taxRegistrationNumber) {
    if (taxpayerHashMap.get(taxRegistrationNumber) instanceof MarriedFilingJointlyTaxpayer) {
      return "Married Filing Jointly";
    } else if (taxpayerHashMap
        .get(taxRegistrationNumber) instanceof MarriedFilingSeparatelyTaxpayer) {
      return "Married Filing Separately";
    } else if (taxpayerHashMap.get(taxRegistrationNumber) instanceof SingleTaxpayer) {
      return "Single";
    } else {
      return "Head of Household";
    }
  }

  public String getTaxpayerIncome(int taxRegistrationNumber) {
    return "" + taxpayerHashMap.get(taxRegistrationNumber).getIncome();
  }

  public double getTaxpayerVariationTaxOnReceipts(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getVariationTaxOnReceipts();
  }

  public int getTaxpayerTotalReceiptsGathered(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getTotalReceiptsGathered();
  }

  public float getTaxpayerAmountOfReceiptKind(int taxRegistrationNumber, short kind) {
    return taxpayerHashMap.get(taxRegistrationNumber).getAmountOfReceiptKind(kind);
  }

  public double getTaxpayerTotalTax(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getTotalTax();
  }

  public double getTaxpayerBasicTax(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getBasicTax();
  }

  public HashMap<Integer, Receipt> getReceiptHashMap(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getReceiptHashMap();
  }

}