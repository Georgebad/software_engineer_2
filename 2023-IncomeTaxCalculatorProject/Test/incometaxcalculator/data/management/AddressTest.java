package incometaxcalculator.data.management;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    Address testAdress=new Address("Greece","Ioannina","Dodoni",1);
    @Test
    void getCountry() {
        assertEquals("Greece",testAdress.getCountry());
    }

    @Test
    void getCity() {
        assertEquals("Ioannina",testAdress.getCity());
    }

    @Test
    void getStreet() {
        assertEquals("Dodoni",testAdress.getStreet());
    }

    @Test
    void getNumber() {
        assertEquals(1,testAdress.getNumber());
    }

    @Test
    void testToString() {
        String exp = "Greece Ioannina Dodoni 1";
        assertEquals(exp,testAdress.toString());
    }
}