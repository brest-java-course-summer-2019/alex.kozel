

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;


public class Main {


    public static void main(String[] args) throws IOException{

        Main main = new Main();
        Count count = new Count();
        CountResult countResult = new CountResult();

        BigDecimal numberKg = new BigDecimal(count.getValue("kg"));
        System.out.println(numberKg);
        BigDecimal numberKm = new BigDecimal(count.getValue("km"));
        System.out.println(numberKm);
        BigDecimal pricePerKm = countResult.countPeriod(numberKm);
        BigDecimal pricePerKg = countResult.countPeriod(numberKg);


        System.out.println(main.sum(numberKg, numberKm, pricePerKm, pricePerKg));





    }
    private String sum(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKm, BigDecimal pricePerKg) throws IOException{

        return weight.multiply(pricePerKg).add(distance.multiply(pricePerKm)).toString();
    }


}
