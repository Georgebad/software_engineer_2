package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public class TXTInfoWriter extends InfoWriter {
    public PrintWriter createWriter(int taxRegistrationNumber) throws IOException{
        PrintWriter outputStream = new PrintWriter(
                new java.io.FileWriter(taxRegistrationNumber + "_INFO.txt"));
        return outputStream;
    }
    public List<String> textFormatFile(){
        String name="Name: %s";
        String afm="AFM: %s";
        String status="Status: %s";
        String income="Income: %s";
        String receipts="Receipts: %s";

        return Arrays.asList(name,afm,status,income,receipts);
    }
    public List<String> textFormatReceipt(){
        String id="Receipt ID: %s";
        String date="Date: %s";
        String kind="Kind: %s";
        String amount="Amount: %s";
        String company="Company: %s";
        String country="Country: %s";
        String city="City: %s";
        String street="Street: %s";
        String number="Number: %s";
        return Arrays.asList(id,date,kind,amount,company,country,city,street,number);

    }
}