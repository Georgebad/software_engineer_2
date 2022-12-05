package incometaxcalculator.data.management;

import incometaxcalculator.data.io.LogWriter;
import incometaxcalculator.data.io.TXTLogWriter;
import incometaxcalculator.data.io.XMLLogWriter;
import incometaxcalculator.exceptions.WrongFileFormatException;

import java.io.IOException;

public class LogFilesFactory {
    public LogWriter createWriter(int taxRegistrationNumber, String fileFormat)
            throws  WrongFileFormatException {
        if (fileFormat.equals("txt")) {
            TXTLogWriter writer = new TXTLogWriter();
            return writer;
        } else if (fileFormat.equals("xml")) {
            XMLLogWriter writer = new XMLLogWriter();
            return writer;
        }
        else {
            throw new WrongFileFormatException();
        }
    }
}
