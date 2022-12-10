package incometaxcalculator.data.management;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.io.XMLFileReader;
import incometaxcalculator.exceptions.WrongFileEndingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoadPayerFactoryTest {

    String endingTxt[] = {"","txt"};
    String endingXml[] = {"","xml"};

    @Test
    void createReader() throws WrongFileEndingException {
        FileReader testTxt = new LoadPayerFactory().createReader(endingTxt);
        assertTrue(testTxt instanceof TXTFileReader);

        FileReader testXml = new LoadPayerFactory().createReader(endingXml);
        assertTrue(testXml instanceof XMLFileReader);
    }
}