package incometaxcalculator.data.management;

import incometaxcalculator.exceptions.WrongTaxpayerStatusException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreatePayerFactoryTest {
    @Test
    void createPayer() throws WrongTaxpayerStatusException {
        Taxpayer test1 = (new CreatePayerFactory()).createPayer("tester",12345,1000,"Married Filing Jointly");
        Taxpayer test2 = (new CreatePayerFactory()).createPayer("tester",12345,1000,"Married Filing Separately");
        Taxpayer test3 = (new CreatePayerFactory()).createPayer("tester",12345,1000,"Single");
        Taxpayer test4 = (new CreatePayerFactory()).createPayer("tester",12345,1000,"Head of Household");

        assertEquals("tester",test1.getFullname());
        assertEquals(12345,test1.getTaxRegistrationNumber());
        assertEquals(1000,test1.getIncome());
        assertTrue(test1 instanceof MarriedFilingJointlyTaxpayer);

        assertEquals("tester",test2.getFullname());
        assertEquals(12345,test2.getTaxRegistrationNumber());
        assertEquals(1000,test2.getIncome());
        assertTrue(test2 instanceof MarriedFilingSeparatelyTaxpayer);

        assertEquals("tester",test3.getFullname());
        assertEquals(12345,test3.getTaxRegistrationNumber());
        assertEquals(1000,test3.getIncome());
        assertTrue(test3 instanceof SingleTaxpayer);


        assertEquals("tester",test4.getFullname());
        assertEquals(12345,test4.getTaxRegistrationNumber());
        assertEquals(1000,test4.getIncome());
        assertTrue(test4 instanceof HeadOfHouseholdTaxpayer);

    }
}