package incometaxcalculator.data.io;

import incometaxcalculator.data.management.TaxpayerManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public abstract class LogWriter implements FileWriter{
    private static final short ENTERTAINMENT = 0;
    private static final short BASIC = 1;
    private static final short TRAVEL = 2;
    private static final short HEALTH = 3;
    private static final short OTHER = 4;

    public void generateFile(int taxRegistrationNumber) throws IOException {

        TaxpayerManager manager = new TaxpayerManager();
        List<String> mylist = textFormat();
        PrintWriter outputStream = createLogWriter(taxRegistrationNumber);
        outputStream.println(String.format(mylist.get(0),manager.getTaxpayerName(taxRegistrationNumber)));
        outputStream.println(String.format(mylist.get(1),taxRegistrationNumber));
        outputStream.println(String.format(mylist.get(2),manager.getTaxpayerIncome(taxRegistrationNumber)));
        outputStream.println(String.format(mylist.get(3),manager.getTaxpayerBasicTax(taxRegistrationNumber)));
        if (manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) > 0) {
            outputStream
                    .println(String.format(mylist.get(4),manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber)));
        } else {
            outputStream
                    .println(String.format(mylist.get(5),manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber)));
        }
        outputStream.println(String.format(mylist.get(6),manager.getTaxpayerTotalTax(taxRegistrationNumber)));
        outputStream.println(
                String.format(mylist.get(7),manager.getTaxpayerTotalReceiptsGathered(taxRegistrationNumber)));
        outputStream.println(
                String.format(mylist.get(8),manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT)));
        outputStream.println(String.format(mylist.get(9),manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC)));
        outputStream
                .println(String.format(mylist.get(10),manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL)));
        outputStream
                .println(String.format(mylist.get(11),manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH)));
        outputStream.println(String.format(mylist.get(12),manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER)));
        outputStream.close();
    }
    public abstract List<String> textFormat();
    public abstract PrintWriter createLogWriter(int taxRegistrationNumber)throws IOException;
}
