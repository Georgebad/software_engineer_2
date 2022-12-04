package incometaxcalculator.data.io;

import incometaxcalculator.data.management.TaxpayerManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public  class TXTLogWriter extends LogWriter {
  public List<String> textFormat() {
    String name="Name: %s";
    String afm="AFM: %s";
    String income="Income: %s";
    String basicTax="Basic Tax: %s";
    String taxIncrease="Tax Increase: %s";
    String taxDecrease="Tax Decrease: %s";
    String totalTax="Total Tax: %s";
    String receipts="TotalReceiptsGathered: %s";
    String entertainment="Entertainment: ";
    String basic="Basic: %s";
    String Travel="Travel: %s";
    String Health="Health: %s";
    String other="Other: %s";

    return Arrays.asList(name,afm,income,basicTax,taxIncrease,taxDecrease,totalTax,receipts,entertainment,
            basic,Travel,Health,other);
  }
}
