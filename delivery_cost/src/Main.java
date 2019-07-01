

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws IOException{

        ValueFromKeyboard valueFromKeyboard = new ValueFromKeyboard();
        ValueFromFile valueFromFile = new ValueFromFile();
        Scanner decide = new Scanner(System.in);
        String value1 = "kg";
        String value2 = "km";
        String answer = "y";

        while (answer.equals("y")) {

            BigDecimal numberKg = new BigDecimal(valueFromKeyboard.getValue(value1));
            System.out.println(numberKg);
            BigDecimal numberKm = new BigDecimal(valueFromKeyboard.getValue(value2));
            System.out.println(numberKm);
            BigDecimal pricePerKg = valueFromFile.getValue(value1, numberKg);
            BigDecimal pricePerKm = valueFromFile.getValue(value2, numberKm);

            System.out.println("kg"+pricePerKg);
            System.out.println("km"+pricePerKm);
            BigDecimal m = sum(numberKg,numberKm, pricePerKg, pricePerKm);
            System.out.println(m);


            do {
                System.out.println("Do you want to recount? --- y/n?");
                answer = decide.nextLine();
            } while  (!answer.equals("n") && !answer.equals("y"));

            }
        }

    private static BigDecimal sum(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKm, BigDecimal pricePerKg) throws IOException{

        return weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
    }


}
