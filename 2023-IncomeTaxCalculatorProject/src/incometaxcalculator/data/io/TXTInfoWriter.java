package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public class TXTInfoWriter extends InfoWriter {
    public void generateFIlePrints(TaxpayerManager manager,int taxRegistrationNumber,PrintWriter outputStream){
        outputStream.println("Name: " + manager.getTaxpayerName(taxRegistrationNumber));
        outputStream.println("AFM: " + taxRegistrationNumber);
        outputStream.println("Status: " + manager.getTaxpayerStatus(taxRegistrationNumber));
        outputStream.println("Income: " + manager.getTaxpayerIncome(taxRegistrationNumber));
        outputStream.println();
        outputStream.println("Receipts:");
    }
    public void generateTaxpayerPrints(Receipt receipt,PrintWriter outputStream){
        outputStream.println("Receipt ID: " + getReceiptId(receipt));
        outputStream.println("Date: " + getReceiptIssueDate(receipt));
        outputStream.println("Kind: " + getReceiptKind(receipt));
        outputStream.println("Amount: " + getReceiptAmount(receipt));
        outputStream.println("Company: " + getCompanyName(receipt));
        outputStream.println("Country: " + getCompanyCountry(receipt));
        outputStream.println("City: " + getCompanyCity(receipt));
        outputStream.println("Street: " + getCompanyStreet(receipt));
        outputStream.println("Number: " + getCompanyNumber(receipt));
        outputStream.println();
    }
}