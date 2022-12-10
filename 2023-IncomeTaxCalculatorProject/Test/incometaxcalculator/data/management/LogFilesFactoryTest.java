package incometaxcalculator.data.management;

import incometaxcalculator.data.io.*;
import incometaxcalculator.exceptions.WrongFileFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogFilesFactoryTest {
    String formatTxt="txt";
    String formatXml="xml";
    int taxRegistrationNumber=12345;
    @Test
    void createWriter() throws WrongFileFormatException {

        LogWriter testTxt = new LogFilesFactory().createWriter(taxRegistrationNumber,formatTxt);
        assertTrue(testTxt instanceof TXTLogWriter);

        LogWriter testXml = new LogFilesFactory().createWriter(taxRegistrationNumber,formatXml);
        assertTrue(testXml instanceof XMLLogWriter);
    }
}