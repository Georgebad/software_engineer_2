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

  protected final List<Integer> calculateInput;
  protected final List<Double> calculateOutput;
  private float amountPerReceiptsKind[] = new float[5];
  private int totalReceiptsGathered = 0;
  private HashMap<Integer, Receipt> receiptHashMap = new HashMap<Integer, Receipt>(0);

  private final List<String> types = Arrays.asList("Entertainment","Basic","Travel","Health","Other");
  private final List<Double> variation1 = Arrays.asList(0.2,0.4,0.6);
  private final List<Double> variation2 = Arrays.asList(0.08,0.04,0.15,0.3);


  protected Taxpayer(String fullname, int taxRegistrationNumber, float income,List<Integer> calculateInput, List<Double> calculateOutput) {
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.income = income;
    this.calculateInput = calculateInput;
    this.calculateOutput = calculateOutput;
  }
  public double calculateBasicTax() {
    if (income < calculateInput.get(0)) {
      return calculateOutput.get(0) * income;
    } else if (income < calculateInput.get(1)) {
      return calculateOutput.get(1) + calculateOutput.get(2) * (income - calculateInput.get(0));
    } else if (income < calculateInput.get(2)) {
      return calculateOutput.get(3) + calculateOutput.get(4) * (income - calculateInput.get(1));
    } else if (income < calculateInput.get(3)) {
      return calculateOutput.get(5) + calculateOutput.get(6) * (income - calculateInput.get(2));
    } else {
      return calculateOutput.get(7) + calculateOutput.get(8) * (income - calculateInput.get(3));
    }
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