package incometaxcalculator.data.management;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import incometaxcalculator.exceptions.WrongReceiptKindException;

public abstract class Taxpayer {

  protected final String fullname;
  protected final int taxRegistrationNumber;
  protected final float income;
  private float amountPerReceiptsKind[] = new float[5];
  private int totalReceiptsGathered = 0;
  private HashMap<Integer, Receipt> receiptHashMap = new HashMap<Integer, Receipt>(0);

  private final List<String> types = Arrays.asList("Entertainment","Basic","Travel","Health","Other");
  private final List<Double> variation1 = Arrays.asList(0.2,0.4,0.6);
  private final List<Double> variation2 = Arrays.asList(0.08,0.04,0.15,0.3);
  public abstract double calculateBasicTax();

  protected Taxpayer(String fullname, int taxRegistrationNumber, float income) {
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.income = income;
  }

  public void addReceipt(Receipt receipt) throws WrongReceiptKindException {
    int check=0;
    for (int i = 0; i < types.size(); i++){
      if (types.get(i).equals((receipt.getKind()))){
        amountPerReceiptsKind[i] += receipt.getAmount();
        check=1;
      }
    }
    if(check!=0) {
      throw new WrongReceiptKindException();
    }
    receiptHashMap.put(receipt.getId(), receipt);
    totalReceiptsGathered++;
  }

  public void removeReceipt(int receiptId) throws WrongReceiptKindException {
    Receipt receipt = receiptHashMap.get(receiptId);
    int check=0;
    for (int i = 0; i < types.size(); i++){
      if (types.get(i).equals((receipt.getKind()))){
        amountPerReceiptsKind[i] -= receipt.getAmount();
        check=1;
      }
    }
    if(check!=0) {
      throw new WrongReceiptKindException();
    }
    totalReceiptsGathered--;
    receiptHashMap.remove(receiptId);
  }

  public String getFullname() {
    return fullname;
  }

  public int getTaxRegistrationNumber() {
    return taxRegistrationNumber;
  }

  public float getIncome() {
    return income;
  }

  public HashMap<Integer, Receipt> getReceiptHashMap() {
    return receiptHashMap;
  }

  public double getVariationTaxOnReceipts() {
    float totalAmountOfReceipts = getTotalAmountOfReceipts();
    for(int i=0; i < variation1.size(); i++){
      if (totalAmountOfReceipts < variation1.get(i) * income) {
        return calculateBasicTax() * variation2.get(i);
      }
    }
    return -calculateBasicTax() * variation2.get(-1);
  }

  private float getTotalAmountOfReceipts() {
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      sum += amountPerReceiptsKind[i];
    }
    return sum;
  }

  public int getTotalReceiptsGathered() {
    return totalReceiptsGathered;
  }

  public float getAmountOfReceiptKind(short kind) {
    return amountPerReceiptsKind[kind];
  }

  public double getTotalTax() {
    return calculateBasicTax() + getVariationTaxOnReceipts();
  }

  public double getBasicTax() {
    return calculateBasicTax();
  }

}