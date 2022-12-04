package incometaxcalculator.data.io;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class InfoWriter implements FileWriter {
    public void generateFile(int taxRegistrationNumber) throws IOException {

        PrintWriter outputStream = new PrintWriter(
                new java.io.FileWriter(taxRegistrationNumber + "_INFO.txt"));
        TaxpayerManager manager = new TaxpayerManager();
        List<String> mylist = textFormatFile();
        outputStream.println(String.format(mylist.get(0),manager.getTaxpayerName(taxRegistrationNumber)));
        outputStream.println(String.format(mylist.get(1),taxRegistrationNumber));
        outputStream.println(String.format(mylist.get(2),manager.getTaxpayerStatus(taxRegistrationNumber)));
        outputStream.println(String.format(mylist.get(3),manager.getTaxpayerIncome(taxRegistrationNumber)));
        outputStream.println();
        outputStream.println(mylist.get(4));
        outputStream.println();
        generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
        outputStream.close();

    }

    public void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream) {
        TaxpayerManager manager = new TaxpayerManager();
        HashMap<Integer, Receipt> receiptsHashMap = manager.getReceiptHashMap(taxRegistrationNumber);
        Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            HashMap.Entry<Integer, Receipt> entry = iterator.next();
            Receipt receipt = entry.getValue();
            List<String> mylist = textFormatReceipt();
            outputStream.println(String.format(mylist.get(0),getReceiptId(receipt)));
            outputStream.println(String.format(mylist.get(1),getReceiptIssueDate(receipt)));
            outputStream.println(String.format(mylist.get(2),getReceiptKind(receipt)));
            outputStream.println(String.format(mylist.get(3),getReceiptAmount(receipt)));
            outputStream.println(String.format(mylist.get(4),getCompanyName(receipt)));
            outputStream.println(String.format(mylist.get(5),getCompanyCountry(receipt)));
            outputStream.println(String.format(mylist.get(6),getCompanyCity(receipt)));
            outputStream.println(String.format(mylist.get(7),getCompanyStreet(receipt)));
            outputStream.println(String.format(mylist.get(8),getCompanyNumber(receipt)));
            outputStream.println();
        }
    }
    public abstract List<String> textFormatFile();
    public abstract List<String> textFormatReceipt();

    public int getReceiptId(Receipt receipt) {
        return receipt.getId();
    }

    public String getReceiptIssueDate(Receipt receipt) {
        return receipt.getIssueDate();
    }

    public String getReceiptKind(Receipt receipt) {
        return receipt.getKind();
    }

    public float getReceiptAmount(Receipt receipt) {
        return receipt.getAmount();
    }

    public String getCompanyName(Receipt receipt) {
        return receipt.getCompany().getName();
    }

    public String getCompanyCountry(Receipt receipt) {
        return receipt.getCompany().getCountry();
    }

    public String getCompanyCity(Receipt receipt) {
        return receipt.getCompany().getCity();
    }

    public String getCompanyStreet(Receipt receipt) {
        return receipt.getCompany().getStreet();
    }

    public int getCompanyNumber(Receipt receipt) {
        return receipt.getCompany().getNumber();
    }
}
