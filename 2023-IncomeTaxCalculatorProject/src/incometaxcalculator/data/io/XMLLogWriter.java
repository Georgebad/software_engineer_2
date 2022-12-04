package incometaxcalculator.data.io;

import incometaxcalculator.data.management.TaxpayerManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class XMLLogWriter extends LogWriter {
  public List<String> textFormat() {
    String name="<Name> %s </Name>";
    String afm="<AFM> %s </AFM>";
    String income="<Income> %s </Income>";
    String basicTax="<BasicTax> %s </BasicTax>";
    String taxIncrease="<TaxIncrease> %s </TaxIncrease>";
    String taxDecrease="<TaxDecrease> %s </TaxDecrease>";
    String totalTax="<TotalTax> %s </TotalTax>";
    String receipts="<Receipts> %s </Receipts>";
    String entertainment="<Entertainment> %s </Entertainment>";
    String basic="<Basic> %s </Basic>";
    String Travel="<Travel> %s </Travel>";
    String Health="<Health> %s </Health>";
    String other="<Other> %s </Other>";

    return Arrays.asList(name,afm,income,basicTax,taxIncrease,taxDecrease,totalTax,receipts,entertainment,
            basic,Travel,Health,other);
  }
}
