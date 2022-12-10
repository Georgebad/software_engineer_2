package incometaxcalculator.data.management;

import incometaxcalculator.exceptions.WrongReceiptDateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {
    Company testcompany = new Company("TestC","Greece","Ioannina","Dodoni",1);

    Receipt testReceipt = new Receipt(1,"1/2/3",150,"Other",testcompany);

    ReceiptTest() throws WrongReceiptDateException {
    }

    @Test
    void getId() {
        assertEquals(1,testReceipt.getId());
    }

    @Test
    void getIssueDate() {
        assertEquals("1/2/3",testReceipt.getIssueDate());
    }

    @Test
    void getAmount() {
        assertEquals(150,testReceipt.getAmount());
    }

    @Test
    void getKind() {
        assertEquals("Other",testReceipt.getKind());
    }

    @Test
    void getCompany() {
        assertEquals(testcompany,testReceipt.getCompany());

    }
}