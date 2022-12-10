package incometaxcalculator.data.io;

import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReadWriteFiles {

    @Test
    void ReadWriteFiles() throws WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException, IOException, ReceiptAlreadyExistsException, WrongFileFormatException, WrongFileEndingException {
        TaxpayerManager manager = new TaxpayerManager();
        manager.createTaxpayer("tester",111111111,"Married Filing Jointly",1000);
        manager.addReceipt(1,"1/2/3",150,"Other","IOA","Greece","Ioannina","Dodoni",1,111111111);
        manager.addReceipt(2,"2/2/3",150,"Other","IOA","Greece","Ioannina","Dodoni",1,111111111);
        //Remove the Taxpayer
        manager.removeTaxpayer(111111111);

        //Load Again from xml and txt file
        manager.loadTaxpayer("111111111_INFO.xml");
        manager.loadTaxpayer("111111111_INFO.txt");

        assertEquals("tester",manager.getTaxpayerName(111111111));
        assertEquals("tester",manager.getTaxpayerName(111111111));

        manager.saveLogFile(111111111,"txt");
        manager.saveLogFile(111111111,"xml");

        File file1 = new File("111111111_LOG.txt");
        File file2 = new File("111111111_LOG.xml");
        assertTrue(file1.exists());
        assertTrue(file2.exists());


    }
}