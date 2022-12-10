package incometaxcalculator.data.management;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    Company testcompany = new Company("TestC","Greece","Ioannina","Dodoni",1);
    @Test
    void getName() {
        assertEquals("TestC",testcompany.getName());
    }

    @Test
    void getCountry() {
        assertEquals("Greece",testcompany.getCountry());
    }

    @Test
    void getCity() {
        assertEquals("Ioannina",testcompany.getCity());
    }

    @Test
    void getStreet() {
        assertEquals("Dodoni",testcompany.getStreet());
    }

    @Test
    void getNumber() {
        assertEquals(1,testcompany.getNumber());
    }
}