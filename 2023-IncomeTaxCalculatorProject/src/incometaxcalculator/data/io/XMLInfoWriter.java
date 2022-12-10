package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public class XMLInfoWriter extends InfoWriter {

    public PrintWriter createWriter(int taxRegistrationNumber) throws IOException{
        PrintWriter outputStream = new PrintWriter(
                new java.io.FileWriter(taxRegistrationNumber + "_INFO.xml"));
        return outputStream;
    }
    public List<String> textFormatFile(){
        String name="<Name> %s </Name>";
        String afm="<AFM> %s </AFM>";
        String status="<Status> %s </Status>";
        String income="<Income> %s </Income>";
        String receipts="<Receipts>";

        return Arrays.asList(name,afm,status,income,receipts);
    }
    public List<String> textFormatReceipt(){
        String id="<ReceiptID> %s </ReceiptID>";
        String date="<Date> %s </Date>";
        String kind="<Kind> %s </Kind>";
        String amount="<Amount> %s </Amount>";
        String company="<Company> %s </Company>";
        String country="<Country> %s </Country>";
        String city="<City> %s </City>";
        String street="<Street> %s </Street>";
        String number="<Number> %s </Number>";
        return Arrays.asList(id,date,kind,amount,company,country,city,street,number);
    }
}