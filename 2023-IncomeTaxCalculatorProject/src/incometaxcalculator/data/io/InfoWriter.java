package incometaxcalculator.data.io;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class InfoWriter implements FileWriter {
    public void generateFile(int taxRegistrationNumber) throws IOException {

        PrintWriter outputStream = new PrintWriter(
                new java.io.FileWriter(taxRegistrationNumber + "_INFO.txt"));
        TaxpayerManager manager = new TaxpayerManager();
        generateFIlePrints(manager,taxRegistrationNumber,outputStream);
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
            generateTaxpayerPrints(receipt,outputStream);
        }
    }
    public abstract void generateFIlePrints(TaxpayerManager manager,int taxRegistrationNumber,PrintWriter outputStream);
    public abstract void generateTaxpayerPrints(Receipt receipt,PrintWriter outputStream);

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
