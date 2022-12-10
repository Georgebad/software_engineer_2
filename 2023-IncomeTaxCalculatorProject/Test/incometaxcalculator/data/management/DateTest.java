package incometaxcalculator.data.management;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    Date testDate = new Date(1,2,3);
    @Test
    void getDay() {
        assertEquals(1,testDate.getDay());
    }

    @Test
    void getMonth() {
        assertEquals(2,testDate.getMonth());

    }

    @Test
    void getYear() {
        assertEquals(3,testDate.getYear());

    }

    @Test
    void testToString() {
        String exp = "1/2/3";
        assertEquals(exp,testDate.toString());

    }
}