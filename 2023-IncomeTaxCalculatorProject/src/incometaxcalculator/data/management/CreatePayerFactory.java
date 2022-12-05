package incometaxcalculator.data.management;

import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

import java.util.Arrays;
import java.util.List;

public class CreatePayerFactory {
    private static List<Integer> calculateInput1= Arrays.asList(36080,90000,143350,254240);
    private static List<Double> calculateOutput1=Arrays.asList(0.0535, 1930.28, 0.0705, 5731.64, 0.0705, 9492.82, 0.0785, 18197.69, 0.0985);
    private static List<Integer> calculateInput2= Arrays.asList(18040,71680,90000,127120);
    private static List<Double> calculateOutput2=Arrays.asList(0.0535, 965.14, 0.0705, 4746.76, 0.0785, 6184.88, 0.0785, 9098.80, 0.0985);
    private static List<Integer> calculateInput3= Arrays.asList(24680,81080,90000,152540);
    private static List<Double> calculateOutput3=Arrays.asList(0.0535, 1320.38, 0.0705, 5296.58, 0.0785, 5996.80, 0.0785, 10906.19, 0.0985);
    private static List<Integer> calculateInput4= Arrays.asList(30390,90000,122110,203390);
    private static List<Double> calculateOutput4=Arrays.asList(0.0535, 1625.87, 0.0705, 5828.38, 0.0705, 8092.13, 0.0785, 14472.61, 0.0985);

    public Taxpayer createPayer(String fullname, int taxRegistrationNumber, float income,
                                String status) throws WrongTaxpayerStatusException {
        if (status.equals("Married Filing Jointly")) {
            return new MarriedFilingJointlyTaxpayer(fullname, taxRegistrationNumber, income,calculateInput1, calculateOutput1);
        } else if (status.equals("Married Filing Separately")) {
            return new MarriedFilingSeparatelyTaxpayer(fullname, taxRegistrationNumber, income,calculateInput2, calculateOutput2);
        } else if (status.equals("Single")) {
            return new SingleTaxpayer(fullname, taxRegistrationNumber, income,calculateInput3, calculateOutput3);
        } else if (status.equals("Head of Household")) {
            return new HeadOfHouseholdTaxpayer(fullname, taxRegistrationNumber, income,calculateInput4, calculateOutput4);
        } else {
            throw new WrongTaxpayerStatusException();
        }
    }


}
