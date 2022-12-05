package incometaxcalculator.data.management;

import incometaxcalculator.data.io.TXTInfoWriter;
import incometaxcalculator.data.io.XMLInfoWriter;

import java.io.File;
import java.io.IOException;

public  class UpdateFilesFactory {
    public void updateFile(int taxRegistrationNumber) throws IOException {
        if (new File(taxRegistrationNumber + "_INFO.xml").exists()) {
            new XMLInfoWriter().generateFile(taxRegistrationNumber);
        } else {
            new TXTInfoWriter().generateFile(taxRegistrationNumber);
            return;
        }
        if (new File(taxRegistrationNumber + "_INFO.txt").exists()) {
            new TXTInfoWriter().generateFile(taxRegistrationNumber);
        }
    }
}

