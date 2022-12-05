package incometaxcalculator.data.management;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.io.XMLFileReader;
import incometaxcalculator.exceptions.WrongFileEndingException;

public class LoadPayerFactory {
    public  FileReader createReader(String ending[])
            throws WrongFileEndingException{
        if (ending[1].equals("txt")) {
            FileReader reader = new TXTFileReader();
            return reader;
        } else if (ending[1].equals("xml")) {
            FileReader reader = new XMLFileReader();
            return reader;
        } else {
            throw new WrongFileEndingException();
        }

    }
}
