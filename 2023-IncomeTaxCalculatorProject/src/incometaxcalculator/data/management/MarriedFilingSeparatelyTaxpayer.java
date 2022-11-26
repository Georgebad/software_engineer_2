package incometaxcalculator.data.management;

import java.util.Arrays;
import java.util.List;

public class MarriedFilingSeparatelyTaxpayer extends Taxpayer {

  public MarriedFilingSeparatelyTaxpayer(String fullname, int taxRegistrationNumber, float income, List<Integer> calculateInput, List<Double> calculateOutput) {
    super(fullname, taxRegistrationNumber, income, calculateInput, calculateOutput);
  }
}
