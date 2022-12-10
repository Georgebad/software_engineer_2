package incometaxcalculator.data.management;

import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TaxpayerManagerTest {

    @Test
    void TestPayerAndReceiptActions() throws WrongTaxpayerStatusException {
        TaxpayerManager manager = new TaxpayerManager();
        manager.createTaxpayer("tester",12345,"Married Filing Jointly",1000);
        assertTrue(manager.containsTaxpayer(12345));
        assertEquals("tester",manager.getTaxpayerName(12345));
        assertEquals("Married Filing Jointly",manager.getTaxpayerStatus(12345));
        assertEquals("1000.0",manager.getTaxpayerIncome(12345));
        assertEquals(53.5,manager.getTaxpayerBasicTax(12345));
        assertEquals(57.78,manager.getTaxpayerTotalTax(12345));

        manager.removeTaxpayer(12345);
        assertFalse(manager.containsTaxpayer());
    }

    @Test
    void ReceiptActions() throws WrongReceiptKindException, WrongReceiptDateException, WrongTaxpayerStatusException, IOException {
        TaxpayerManager manager = new TaxpayerManager();
        manager.createTaxpayer("tester",12345,"Married Filing Jointly",1000);
        manager.createReceipt(1,"1/2/3",150,"Other","IOA","Greece","Ioannina","Dodoni",1,12345);
        assertTrue(manager.containsReceipt(1));
        assertEquals(4.28,manager.getTaxpayerVariationTaxOnReceipts(12345));
        assertEquals(1,manager.getTaxpayerTotalReceiptsGathered(12345));
        assertEquals(150.0,manager.getTaxpayerAmountOfReceiptKind(12345, (short) 4));
        manager.createReceipt(2,"1/2/3",150,"Other","IOA","Greece","Ioannina","Dodoni",1,12345);
        assertEquals(2,manager.getTaxpayerTotalReceiptsGathered(12345));
        manager.removeReceipt(1);
        manager.removeReceipt(2);
        manager.removeTaxpayer(12345);
        assertFalse(manager.containsReceipt(1));
    }
}